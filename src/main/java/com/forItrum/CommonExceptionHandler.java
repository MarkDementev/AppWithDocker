package com.forItrum;

import com.forItrum.exception.BadOperationTypeException;
import com.forItrum.exception.NotEnoughMoneyException;
import com.forItrum.exception.WalletNotExistException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(WalletNotExistException.class)
    public ResponseEntity<WalletNotExistException> walletNotExistExceptionHandler(WalletNotExistException e) {
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValidHandler(MethodArgumentNotValidException e) {
        Map<String,String> errorMap = e.getAllErrors()
                .stream()
                .collect(Collectors.toMap(x -> ((FieldError)x).getField(),
                        b -> b.getDefaultMessage(),
                        (p,q) -> p,
                        LinkedHashMap::new));
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadOperationTypeException.class)
    public ResponseEntity<BadOperationTypeException> badOperationTypeExceptionHandler(BadOperationTypeException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<NotEnoughMoneyException> notEnoughMoneyExceptionHandler(NotEnoughMoneyException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
