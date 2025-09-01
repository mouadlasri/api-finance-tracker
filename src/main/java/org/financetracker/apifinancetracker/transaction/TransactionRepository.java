package org.financetracker.apifinancetracker.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser_Id(Long userId);

    // JPA automatically handles the joins between Transaction -> Subcategory -> Category
    // translates to SQL:
    //    SELECT t.*
    //    FROM transaction t
    //    JOIN subcategory s ON t.subcategory_id = s.id
    //    JOIN category c ON s.category_id = c.id
    //    WHERE t.user_id = 1
    //    AND c.id = 1;
    List<Transaction> findByUser_IdAndSubcategory_Category_Id(Long userId, Long categoryId);
}
