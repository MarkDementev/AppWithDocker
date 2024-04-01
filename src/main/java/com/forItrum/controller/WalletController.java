package com.forItrum.controller;

import com.forItrum.dto.WalletDTO;
import com.forItrum.model.Wallet;
import com.forItrum.service.WalletService;

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
    public ResponseEntity<Integer> getWalletAmount(@PathVariable final UUID walletId) {
        return ResponseEntity.ok().body(walletService.getWalletAmount(walletId));
    }

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> depositOrWithdrawWalletAmount(@RequestBody @Valid WalletDTO walletDTO) {
        return ResponseEntity.ok().body(walletService.depositOrWithdrawWalletAmount(walletDTO));
    }
}
