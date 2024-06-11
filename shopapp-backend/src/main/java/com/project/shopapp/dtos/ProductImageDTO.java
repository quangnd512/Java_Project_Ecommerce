package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageDTO {
    @JsonProperty("product_id")
    @Min(value = 1, message = "Lon hon 1")
    private Long productId;

    @Size(min= 5, max= 200, message = "Kich thuoc file anh tu 5 ky tuy den 200 ky tu")
    @JsonProperty("image_url")
    private String imageUrl;
}
