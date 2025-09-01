package org.financetracker.apifinancetracker.transaction;

import org.apache.coyote.Response;
import org.financetracker.apifinancetracker.subcategory.SubcategoryService;
import org.financetracker.apifinancetracker.transaction.dto.CategoryTotalResponse;
import org.financetracker.apifinancetracker.transaction.dto.CreateTransactionRequest;
import org.financetracker.apifinancetracker.transaction.dto.TransactionResponse;
import org.financetracker.apifinancetracker.transaction.dto.TransactionSummaryResponse;
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
                        transaction.getUser().getId(),
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

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable Long id, @RequestBody CreateTransactionRequest request) {
        Transaction updatedTransaction = transactionService.updateTransaction(id, request);

        TransactionResponse response = new TransactionResponse(
                updatedTransaction.getId(),
                updatedTransaction.getAmount(),
                updatedTransaction.getDescription(),
                updatedTransaction.getDate(),
                updatedTransaction.getType(),
                updatedTransaction.getUser().getId(),
                updatedTransaction.getSubcategory().getId()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary/users/{userId}")
    public ResponseEntity<TransactionSummaryResponse> getSummaryByUserId(@PathVariable Long userId) {
        TransactionSummaryResponse summary = transactionService.getSummaryByUserId(userId);

        return ResponseEntity.ok(summary);
    }

    @GetMapping("/summary/users/{userId}/categories/{categoryId}")
    public ResponseEntity<CategoryTotalResponse> getCategoryTotalByUserId(@PathVariable Long userId, @PathVariable long categoryId) {
        CategoryTotalResponse total = transactionService.getCategoryTotalByUserId(userId, categoryId);

        return ResponseEntity.ok(total);
    }

}
