package org.financetracker.apifinancetracker.category;

import org.apache.coyote.Response;
import org.financetracker.apifinancetracker.category.dto.CreateCategoryRequest;
import org.financetracker.apifinancetracker.user.User;
import org.financetracker.apifinancetracker.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public Category getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID: " + id);
        }
    }

    @Transactional
    public Category createCategory(CreateCategoryRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + request.getUserId()));

        Category newCategory = new Category();
        newCategory.setName(request.getName());
        newCategory.setUser(user);

        return categoryRepository.save(newCategory);
    }

    @Transactional
    public Category updateCategory(Long id, CreateCategoryRequest request) {
        Category categoryToUpdate = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID: " + id));

        categoryToUpdate.setName(request.getName());

        return categoryRepository.save(categoryToUpdate);
    }

    public void deleteCategory(Long id) {
        if(!categoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
