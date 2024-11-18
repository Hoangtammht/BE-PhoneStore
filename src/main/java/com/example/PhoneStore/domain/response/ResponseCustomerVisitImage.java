package com.example.PhoneStore.domain.response;

import com.example.PhoneStore.utils.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCustomerVisitImage {

    private int customerImageID;
    private String imageURL;
    private String createdAt;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = DateTimeUtil.formatLocalDateTime(createdAt);
    }

}
