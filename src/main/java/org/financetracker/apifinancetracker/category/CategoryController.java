package org.financetracker.apifinancetracker.category;

import org.apache.coyote.Response;
import org.financetracker.apifinancetracker.category.dto.CategoryResponse;
import org.financetracker.apifinancetracker.category.dto.CreateCategoryRequest;
import org.financetracker.apifinancetracker.user.User;
import org.financetracker.apifinancetracker.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;
    private UserService userService;

    public CategoryController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);

        CategoryResponse response = new CategoryResponse(
                category.getId(),
                category.getName()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}")
    public List<CategoryResponse> getCategoriesByUserId(@PathVariable Long id) {
        User user = userService.findUserById(id);

        List<CategoryResponse> categoryResponses = user.getCategories().stream().map(category -> new CategoryResponse(
                category.getId(),
                category.getName()
        )).collect(Collectors.toList());

        return categoryResponses;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CreateCategoryRequest request) {
        Category newCategory = categoryService.createCategory(request);
        System.out.println("Request userId: " + request.getUserId());
        System.out.println("Request name: " + request.getName());
        CategoryResponse response = new CategoryResponse(
                newCategory.getId(),
                newCategory.getName()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CreateCategoryRequest request) {
        Category updatedCategory = categoryService.updateCategory(id, request);

        CategoryResponse response = new CategoryResponse(
                updatedCategory.getId(),
                updatedCategory.getName()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
