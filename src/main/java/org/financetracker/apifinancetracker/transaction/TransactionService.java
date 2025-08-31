package org.financetracker.apifinancetracker.transaction;

import org.financetracker.apifinancetracker.common.TransactionType;
import org.financetracker.apifinancetracker.subcategory.Subcategory;
import org.financetracker.apifinancetracker.subcategory.SubcategoryRepository;
import org.financetracker.apifinancetracker.subcategory.SubcategoryService;
import org.financetracker.apifinancetracker.transaction.dto.CreateTransactionRequest;
import org.financetracker.apifinancetracker.transaction.dto.TransactionResponse;
import org.financetracker.apifinancetracker.user.User;
import org.financetracker.apifinancetracker.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    private SubcategoryRepository subcategoryRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, SubcategoryRepository subcategoryRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    public Transaction findTransactionById(Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);

        if (optionalTransaction.isPresent()) {
            return optionalTransaction.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found with ID: " + id);
        }
    }

    public List<Transaction> findTransactionsByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + userId);
        }

        return transactionRepository.findByUser_Id(optionalUser.get().getId());
    }

    public Transaction createTransaction(CreateTransactionRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + request.getUserId()));

        Subcategory subcategory = subcategoryRepository.findById(request.getSubcategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subcategory not foudn with ID: " + request.getSubcategoryId()));

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(request.getAmount());
        newTransaction.setDescription(request.getDescription());
        newTransaction.setDate(request.getDate());
        newTransaction.setType(request.getType());
        newTransaction.setUser(user);
        newTransaction.setSubcategory(subcategory);

        return transactionRepository.save(newTransaction);
    }
}
