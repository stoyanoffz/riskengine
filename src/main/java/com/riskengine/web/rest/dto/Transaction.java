package com.riskengine.web.rest.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(
        @NotNull
        @NotBlank
        String transactionId,
        @NotNull
        @NotBlank
        String userId,
        @Digits(integer = 8, fraction = 2)
        BigDecimal amount,
        LocalDateTime timestamp,
        @NotNull
        @NotBlank
        String country,
        @NotNull
        @Digits(integer = 4, fraction = 6)
        Double latitude,
        @NotNull
        @Digits(integer = 4, fraction = 6)
        Double longitude) {
}
