package com.example.PhoneStore.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVariantByProductID {

    private String variantID;
    private String productColorID;
    private String colorName;
    private String storageCapacity;
    private String imagePath;
    private double price;

}
