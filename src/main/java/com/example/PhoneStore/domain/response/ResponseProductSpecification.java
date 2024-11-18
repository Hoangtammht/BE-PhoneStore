package com.example.PhoneStore.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductSpecification {
    private int productID;
    private String specName;
    private String specValue;
}
