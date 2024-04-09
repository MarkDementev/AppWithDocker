package com.forItrum.service.impl;

import com.forItrum.dto.WalletDTO;
import com.forItrum.type.OperationType;
import com.forItrum.exception.BadOperationTypeException;
import com.forItrum.exception.NotEnoughMoneyException;
import com.forItrum.exception.WalletNotExistException;
import com.forItrum.model.Wallet;
import com.forItrum.repository.WalletRepository;
import com.forItrum.service.WalletService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    @Override
    public Integer getWalletAmount(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotExistException(walletId))
                .getAmount();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Wallet depositOrWithdrawWalletAmount(WalletDTO walletDTO) {
        UUID walletId = walletDTO.getWalletId();
        Wallet walletToUpdate = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotExistException(walletId));
        AtomicReference<Wallet> atomicWalletToUpdate = new AtomicReference<>(walletToUpdate);
        OperationType inputOperationType = walletDTO.getOperationType();
        Integer walletAmount = atomicWalletToUpdate.get().getAmount();
        Integer walletDTOAmount = walletDTO.getAmount();

        if (inputOperationType == OperationType.DEPOSIT) {
            atomicWalletToUpdate.getAndSet(new Wallet(walletId, walletAmount + walletDTOAmount));
        } else if (inputOperationType == OperationType.WITHDRAW) {
            if (walletDTOAmount > walletAmount) {
                throw new NotEnoughMoneyException(walletAmount);
            }
            atomicWalletToUpdate.getAndSet(new Wallet(walletId, walletAmount - walletDTOAmount));
        } else {
            throw new BadOperationTypeException(inputOperationType);
        }
        return walletRepository.save(atomicWalletToUpdate.get());
    }
}
