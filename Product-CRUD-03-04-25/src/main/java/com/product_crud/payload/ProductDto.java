package com.product_crud.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @Min(value = 1, message = "Price must be greater than 0")
    private double price;

    @NotBlank(message = "Description is required")
    private String description;
}
