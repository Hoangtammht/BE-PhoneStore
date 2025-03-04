package com.example.PhoneStore.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProduct {
    private String productID;
    private int categoryID;
    private String productName;
    private String image;
    private String description;
    private double price;
    private int status;

}
