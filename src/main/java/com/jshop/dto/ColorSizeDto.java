package com.jshop.dto;

import com.jshop.model.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorSizeDto {
    private int colorSizeId;
    private int quantity;
    SizeDto size;
    ProductColorDto productColorDto;
}
