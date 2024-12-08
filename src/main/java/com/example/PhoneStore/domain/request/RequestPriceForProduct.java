package com.example.PhoneStore.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPriceForProduct {
    private String variantID;
    private int productStorageID;
    private String productColorID;
    private String productID;
    private String colorName;
    private String imagePath;
    private double price;
}
