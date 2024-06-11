package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    @NotBlank(message = "Titile is required")
    @Size(min = 3, max = 200, message = "Do dai nho nhat la 3 vva lon nhat la 200")
    private String name;

    @Min(value = 0, message = "Gia nho nhat bang 0")
    @Max(value = 10000000, message = "Gia lon nhat la 10,000,000")
    private Float price;
    private String thumbnail;
    private String description;

    @JsonProperty("category_id")
    private Long categoryId;

    private List<MultipartFile> files;
}
