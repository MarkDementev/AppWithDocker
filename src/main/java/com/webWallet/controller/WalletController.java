package com.webWallet.controller;

import com.webWallet.dto.WalletDTO;
import com.webWallet.model.Wallet;
import com.webWallet.service.WalletService;

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
    private final WalletService walletService;

    @GetMapping("/wallets/{WALLET_UUID}")
    public ResponseEntity<Integer> getWalletAmount(@PathVariable UUID WALLET_UUID) {
        return ResponseEntity.ok().body(walletService.getWalletAmount(WALLET_UUID));
    }

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> depositOrWithdrawWalletAmount(@RequestBody @Valid WalletDTO walletDTO) {
        return ResponseEntity.ok().body(walletService.depositOrWithdrawWalletAmount(walletDTO));
    }
}
