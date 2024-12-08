package com.example.PhoneStore.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductSpecification {
    private String productID;
    private String specName;
    private String specValue;
}
