package com.example.PhoneStore.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductColor {
    private String productColorID;
    private String productID;
    private String colorName;
    private String imagePath;
}
