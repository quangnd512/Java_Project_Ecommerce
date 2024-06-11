package com.project.shopapp.controllers;

import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.models.Category;
import com.project.shopapp.repositories.CategoryRepository;
import com.project.shopapp.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
// @Validated //Sử dụng validate

@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    //    Truyền dữ liệu POST: //http://localhost:8088/api/v1/categories
    @PostMapping("")
    // Nếu tham số truyền vào là một đối tượng => Request Object
    public ResponseEntity<?> createCategories( // ResponseEntity<?>: Nhận list các String hoặc String
                                               @Valid @RequestBody CategoryDTO categoryDTO,
                                               BindingResult result) {
        //@Valid: Xử lý validate trước khi thực hiện việc xử lý
        //@RequestBody: Request trong body
        if (result.hasErrors()) {
            List<String> errorMessages =  result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok("Them category thanh cong");
    }

    //    Hiển thị dữ liệu GET: //http://localhost:8088/api/v1/categories
    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(
            //Query Param: http://localhost:8088/api/v1/categories?page=1&limit=10
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    //    Update dữ liệu PUT: //http://localhost:8088/api/v1/categories/5
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategories(
            @PathVariable Long id,
            @Valid @RequestBody
            CategoryDTO categoryDTO
    ) {
        categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok("Cap nhat category thanh công");
    }

    //    Xoá dữ liệu DELETE: //http://localhost:8088/api/v1/categories/5
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategories(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Đã xoá thành công category có id là "+id);
    }

}
