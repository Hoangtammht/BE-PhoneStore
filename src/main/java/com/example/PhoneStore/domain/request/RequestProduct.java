package com.example.PhoneStore.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProduct {
    private String productID;
    private String productName;
    private int categoryID;
    private String image;
    private double price;
}
