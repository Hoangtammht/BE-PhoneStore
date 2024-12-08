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
    private String customerID;
    private String orderID;
    private String orderDetailID;
    private String content;
    private double totalAmount;
    private int status;
    private String productID;
    private String variantID;
    private double priceAtOrder;
    private String orderType;
    private Integer installmentPlanID;

}
