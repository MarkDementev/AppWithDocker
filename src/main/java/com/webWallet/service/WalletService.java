package com.webWallet.service;

import com.webWallet.dto.WalletDTO;
import com.webWallet.model.Wallet;

import java.util.UUID;

public interface WalletService {
    Integer getWalletAmount(UUID walletId);
    Wallet depositOrWithdrawWalletAmount(WalletDTO walletDTO);
}
