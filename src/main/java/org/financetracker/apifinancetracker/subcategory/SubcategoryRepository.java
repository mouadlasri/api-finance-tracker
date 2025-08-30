package org.financetracker.apifinancetracker.subcategory;

import org.financetracker.apifinancetracker.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    // find all subcategories that are linked to a specific category ID
    List<Subcategory> findByCategory_Id(Long categoryId);
}
