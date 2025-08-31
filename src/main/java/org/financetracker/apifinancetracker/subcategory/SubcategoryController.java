package org.financetracker.apifinancetracker.subcategory;

import org.financetracker.apifinancetracker.subcategory.dto.CreateSubcategoryRequest;
import org.financetracker.apifinancetracker.subcategory.dto.SubcategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {
    private SubcategoryService subcategoryService;

    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryResponse> getSubcategory(@PathVariable Long id) {
        Subcategory subcategory = subcategoryService.getSubcategoryById(id);

        SubcategoryResponse response = new SubcategoryResponse(
                subcategory.getId(),
                subcategory.getName()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SubcategoryResponse> createSubcategory(CreateSubcategoryRequest request) {
        Subcategory subcategory = subcategoryService.createSubcategory(request);

        SubcategoryResponse response = new SubcategoryResponse(
                subcategory.getId(),
                subcategory.getName()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}")
    public List<SubcategoryResponse> getSubcategoriesByCategoryId(@PathVariable Long categoryId) {
        List<Subcategory> subcategories = subcategoryService.getSubcategoriesByCategoryId(categoryId);

        List<SubcategoryResponse> response = subcategories.stream()
                .map(subcategory -> new SubcategoryResponse(
                        subcategory.getId(),
                        subcategory.getName()
                )).collect(Collectors.toList());

        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubcategoryResponse> updateSubcategory(@PathVariable Long id, @RequestBody CreateSubcategoryRequest request) {
        Subcategory subcategoryToUpdate = subcategoryService.updateSubcategory(id, request);

        SubcategoryResponse response = new SubcategoryResponse(
                subcategoryToUpdate.getId(),
                subcategoryToUpdate.getName()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable Long id) {
        subcategoryService.deleteSubcategory(id);
        return ResponseEntity.noContent().build();
    }

}
