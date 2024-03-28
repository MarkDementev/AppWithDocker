package com.forItrum.exception;

import java.util.UUID;

public class WalletNotExistException extends IllegalArgumentException {
    public static final String MESSAGE = "Wallet with this UUID doesn't exist! Wrong UUID: ";

    public WalletNotExistException(UUID walletId) {
        super(MESSAGE + walletId);
    }
}
