package org.financetracker.apifinancetracker.transaction;

import org.financetracker.apifinancetracker.subcategory.SubcategoryService;
import org.financetracker.apifinancetracker.transaction.dto.CreateTransactionRequest;
import org.financetracker.apifinancetracker.transaction.dto.TransactionResponse;
import org.financetracker.apifinancetracker.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable Long id) {
        Transaction transaction = transactionService.findTransactionById(id);

        TransactionResponse response = new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getDate(),
                transaction.getType(),
                transaction.getUser().getId(),
                transaction.getSubcategory().getId()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public List<TransactionResponse> getTransactionsByUserId(@PathVariable Long userId) {
        List<Transaction> transactions = transactionService.findTransactionsByUserId(userId);

        List<TransactionResponse> response = transactions.stream()
                .map(transaction -> new TransactionResponse(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getDescription(),
                        transaction.getDate(),
                        transaction.getType(),
                        null,
                        transaction.getSubcategory().getId()
                )).collect(Collectors.toList());

        return response;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody CreateTransactionRequest request) {
        Transaction transaction = transactionService.createTransaction(request);

        TransactionResponse response = new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getDate(),
                transaction.getType(),
                transaction.getUser().getId(),
                transaction.getSubcategory().getId()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
