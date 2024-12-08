package com.example.PhoneStore.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDetail {
    private String orderID;
    private String customerID;
    private String phone;
    private String fullName;
    private String orderDate;
    private String status;
    private String orderType;
    private String variantID;
    private String productName;
    private String content;
    private double priceAtOrder;
    private String planName;
    private int durationMonths;
    private double monthlyPayment;
}
