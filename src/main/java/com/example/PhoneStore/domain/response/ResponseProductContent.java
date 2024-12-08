package com.example.PhoneStore.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductContent {
    private int contentID;
    private String productID;
    private String title;
    private String contentText;
    private String contentImage;
    private int displayOrder;
}
