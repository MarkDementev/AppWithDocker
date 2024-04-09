package com.forItrum.exception;

import com.forItrum.type.OperationType;

import java.util.Arrays;

public class BadOperationTypeException extends IllegalArgumentException {
    public static final String MESSAGE = "Wallet doesn't do this operation type! Valid types are: "
            + Arrays.toString(OperationType.values())
            .replace("[", "")
            .replace("]", ".")
            + " Your input operation type is: ";

    public BadOperationTypeException(OperationType inputOperationType) {
        super(MESSAGE + inputOperationType);
    }
}
