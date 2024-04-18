package com.webWallet.exception.handler;

import com.webWallet.exception.BadOperationTypeException;
import com.webWallet.exception.NotEnoughMoneyException;
import com.webWallet.exception.WalletNotExistException;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> exceptionHandler(Exception e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
