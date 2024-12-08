package com.example.PhoneStore.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductContent {
    private String productID;
    private String title;
    private String contentText;
    private String contentImage;
    private int displayOrder;

}
