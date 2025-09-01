package org.financetracker.apifinancetracker.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByIdAndUserId(Long categoryId, Long userId);
}
