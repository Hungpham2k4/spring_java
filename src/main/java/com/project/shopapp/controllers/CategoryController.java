package com.project.shopapp.controllers;

import com.project.shopapp.dtos.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    @GetMapping()
    public ResponseEntity<String> getAllCategory(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        return ResponseEntity.ok(String.format("Page category, page = %d and limit = %d", page, limit));
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(
            @Validated @RequestBody CategoryDTO categoryDTO,
            BindingResult result
            ){
        if (result.hasErrors()){
            List<String> ErrorMessage = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(ErrorMessage);
        }
        return ResponseEntity.ok("This is insertCategory" + categoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id){
        return ResponseEntity.ok("This is updateCategory" + id );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteCategory(@PathVariable Long id){
        return ResponseEntity.ok("Delete Successfully" + id);
    }
}
