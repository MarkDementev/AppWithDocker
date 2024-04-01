package com.forItrum.dto;

import com.forItrum.enums.OperationType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDTO {
    @NotNull(message = "The walletId is mandatory in JSON!")
    private UUID walletId;

    @NotNull(message = "The operationType is mandatory in JSON!")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @NotNull(message = "The amount is mandatory in JSON!")
    @Positive(message = "The amount is only positive value!")
    private Integer amount;
}
