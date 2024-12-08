package com.example.PhoneStore.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductOrder {
    private String orderID;
    private String productName;
    private String colorName;
    private String storageCapacity;
    private String content;
}
