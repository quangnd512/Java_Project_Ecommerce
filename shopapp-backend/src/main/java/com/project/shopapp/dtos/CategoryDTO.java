package com.project.shopapp.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.apache.logging.log4j.message.Message;

@Data //To String in lombok
@Getter
@Setter
@AllArgsConstructor //Contructor all param
@NoArgsConstructor //Contructor no param
public class CategoryDTO {
    @NotEmpty(message = "category khong duoc trung")
    private String name;
}
