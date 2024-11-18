package com.example.PhoneStore.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductColor {
    private int productColorID;
    private int productID;
    private String colorName;
    private String colorHex;
    private String imagePath;
}
