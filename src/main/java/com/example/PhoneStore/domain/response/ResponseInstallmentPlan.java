package com.example.PhoneStore.domain.response;

import com.example.PhoneStore.utils.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseInstallmentPlan {
    private int installmentPlanID;
    private String productID;
    private String planName;
    private String variantID;
    private double price;
    private int durationMonths; //Kỳ hạn trả góp
    private double interestRate; //Lãi suất áp dụng
    private double downPayment; //Số tiền trả trước
    private double monthlyPayment; //Số tiền góp mỗi tháng.
    private double totalAmount; //totalAmount
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
