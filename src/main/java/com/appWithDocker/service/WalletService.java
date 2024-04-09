package com.appWithDocker.service;

import com.appWithDocker.dto.WalletDTO;
import com.appWithDocker.model.Wallet;

import java.util.UUID;

public interface WalletService {
    Integer getWalletAmount(UUID walletId);
    Wallet depositOrWithdrawWalletAmount(WalletDTO walletDTO);
}
