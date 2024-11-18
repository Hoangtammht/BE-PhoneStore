package com.example.PhoneStore.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductVariant {

    private int variantID;
    private int productID;
    private int productColorID;
    private int productStorageID;
    private double price;
    private int stock;

}
