package com.example.PhoneStore.domain.response;

import com.example.PhoneStore.utils.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePromotion {

    private int productID;
    private String promotionDescription;
    private double discountValue;
    private String startDate;
    private String endDate;
    private boolean isActive;

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = DateTimeUtil.formatLocalDateTime(startDate);
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = DateTimeUtil.formatLocalDateTime(endDate);
    }

}
