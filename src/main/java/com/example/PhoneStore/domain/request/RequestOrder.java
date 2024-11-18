package com.example.PhoneStore.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrder {
    private String fullName;
    private String phone;
    private String orderDate;
    private double totalAmount;
    private int status;
    private int productID;
    private double priceAtOrder;


}
