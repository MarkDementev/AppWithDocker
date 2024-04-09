package com.forItrum.service;

import com.forItrum.dto.WalletDTO;
import com.forItrum.model.Wallet;

import java.util.UUID;

public interface WalletService {
    Integer getWalletAmount(UUID walletId);
    Wallet depositOrWithdrawWalletAmount(WalletDTO walletDTO);
}
