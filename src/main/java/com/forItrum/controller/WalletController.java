package com.forItrum.controller;

import com.forItrum.dto.WalletDTO;
import com.forItrum.model.Wallet;
import com.forItrum.service.impl.WalletServiceImpl;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class WalletController {
    public static final String WALLET_PATH = "/wallets/{WALLET_UUID}";
    public static final String WALLET_UPDATE_PATH = "/wallet";
    private final WalletServiceImpl walletService;

    @GetMapping(WALLET_PATH)
    public ResponseEntity<Integer> getWalletAmount(@PathVariable UUID walletId) {
        return ResponseEntity.ok().body(walletService.getWalletAmount(walletId));
    }

    @PostMapping(WALLET_UPDATE_PATH)
    public ResponseEntity<Wallet> depositOrWithdrawWalletAmount(@RequestBody @Valid WalletDTO walletDTO) {
        return ResponseEntity.ok().body(walletService.depositOrWithdrawWalletAmount(walletDTO));
    }
}
