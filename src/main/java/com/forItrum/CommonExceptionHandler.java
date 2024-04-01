package com.forItrum;

import com.forItrum.exception.BadOperationTypeException;
import com.forItrum.exception.NotEnoughMoneyException;
import com.forItrum.exception.WalletNotExistException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(WalletNotExistException.class)
    public ResponseEntity<WalletNotExistException> walletNotExistExceptionHandler(WalletNotExistException e) {
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
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
