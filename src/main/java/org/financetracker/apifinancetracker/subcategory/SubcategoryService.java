package org.financetracker.apifinancetracker.subcategory;

import org.financetracker.apifinancetracker.category.Category;
import org.financetracker.apifinancetracker.category.CategoryRepository;
import org.financetracker.apifinancetracker.subcategory.dto.CreateSubcategoryRequest;
import org.financetracker.apifinancetracker.subcategory.dto.SubcategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {
    private SubcategoryRepository subcategoryRepository;
    private CategoryRepository categoryRepository;

    public SubcategoryService(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public Subcategory getSubcategoryById(Long id) {
        Optional<Subcategory> subCategory = subcategoryRepository.findById(id);

        if (subCategory.isPresent()) {
            return subCategory.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subcategory not found with ID: " + id);
        }
    }

    public Subcategory createSubcategory(CreateSubcategoryRequest request) {
        Subcategory subcategory = new Subcategory();
        subcategory.setName(request.getName());

        return subcategoryRepository.save(subcategory);
    }

    public List<Subcategory> getSubcategoriesByCategoryId(Long categoryId) {
        // get the parent category of this subcategory
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID: " + categoryId);
        }

        return subcategoryRepository.findByCategory_Id(categoryId);
    }

    @Transactional
    public Subcategory updateSubcategory(Long id, CreateSubcategoryRequest request) {
        Subcategory subcategory = subcategoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subcategory not found with ID: " + id));

        subcategory.setName(request.getName());

        return subcategoryRepository.save(subcategory);
    }

    @Transactional
    public void deleteSubcategory(Long id) {
        if(!subcategoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subcategory not found with ID: " + id);
        }
        subcategoryRepository.deleteById(id);
    }

}
