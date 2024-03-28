package com.forItrum.exception;

public class NotEnoughMoneyException extends IllegalArgumentException {
    public static final String MESSAGE = "Wallet doesn't have this sum of money! Amount is only: ";

    public NotEnoughMoneyException(Integer inputAmount) {
        super(MESSAGE + inputAmount);
    }
}
