package com.product_crud.payload;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @Positive(message = "Price must be greater than 0")
    private double price;

    @NotBlank(message = "Description is required")
    private String description;

    @NotEmpty(message = "At least one category is required")
    private List<Long> categoryIds;

    private List<CommentDto> comments;
}