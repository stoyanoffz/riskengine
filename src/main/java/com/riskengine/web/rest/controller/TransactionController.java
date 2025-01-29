package com.riskengine.web.rest.controller;

import com.riskengine.domain.FraudulentTransactionEntity;
import com.riskengine.service.FraudulentTransactionService;
import com.riskengine.web.rest.dto.FraudulentTransaction;
import com.riskengine.web.rest.dto.Transaction;
import com.riskengine.web.rest.mapper.TransactionMapper;
import com.riskengine.web.rest.response.BadRequest;
import com.riskengine.web.rest.response.InternalServerError;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fraudulent-transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final Validator validator;
    private final FraudulentTransactionService fraudulentTransactionService;

    @PostMapping("/status")
    public ResponseEntity<Object> checkFraudulentTransaction(@RequestBody Transaction transaction) {
        List<BadRequest> badRequests = validator.validate(transaction).stream()
                .map(violation -> new BadRequest(violation.getPropertyPath().toString(), violation.getMessage()))
                .toList();
        if (!badRequests.isEmpty()) {
            return ResponseEntity.badRequest().body(badRequests);
        }

        try {
            FraudulentTransactionEntity fraudulentTransactionEntity =
                    TransactionMapper.toFraudulentTransactionEntity(transaction);
            boolean isFraudulentTransaction = fraudulentTransactionService.isFraudulentTransaction(fraudulentTransactionEntity);
            FraudulentTransaction fraudulentTransaction = new FraudulentTransaction(isFraudulentTransaction);

            return ResponseEntity.ok(fraudulentTransaction);
        } catch (Exception e) {
            var internalServerError = new InternalServerError("There was a problem processing the transaction.");

            return ResponseEntity.internalServerError().body(internalServerError);
        }
    }

}
