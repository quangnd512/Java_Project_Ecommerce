package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value = 1, message = "Order Id lớn hơn 0")
    private Long orderId;

    @JsonProperty("product_id")
    @Min(value = 1, message = "Product Id lớn hơn 0")
    private Long productId;

    @Min(value = 0, message = "Gia toi thieu phai bang 0, khong duoc am")
    private Long price;

    @JsonProperty("number_of_products")
    @Min(value = 1, message = "number_of_products lớn hơn 0")
    private int numberOfProducts;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Gia toi thieu phai bang 0, khong duoc am")
    private int totalMoney;

    private String color;
}
