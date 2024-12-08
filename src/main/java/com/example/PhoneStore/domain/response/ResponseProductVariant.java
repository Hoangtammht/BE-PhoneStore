package com.example.PhoneStore.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductVariant {

    private String variantID;
    private String productID;
    private String productColorID;
    private int productStorageID;
    private double price;

}
