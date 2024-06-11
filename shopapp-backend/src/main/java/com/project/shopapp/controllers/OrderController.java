package com.project.shopapp.controllers;

import com.project.shopapp.dtos.OrderDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    @PostMapping("")
    public ResponseEntity<?> createOrder(
            @Valid @RequestBody
            OrderDTO orderDTO,
            BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages =  result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Create Order sussessfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{user_id}")
    //  GET: http://localhost:8088/api/v1/orders/1
    public ResponseEntity<?> getOrder(
            @Valid @PathVariable("user_id")
            Long userId) {
        try {
            return ResponseEntity.ok("Lấy ra danh sách order từ user_id");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Admin
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(
            @Valid @PathVariable long id,
            @Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok("Cập nhật thông tin một order");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(
            @Valid @PathVariable long id
    ) {
        // Xoá mềm => Cập nhật trường active = false
        return ResponseEntity.ok("Order delete successfully");
    }
}
