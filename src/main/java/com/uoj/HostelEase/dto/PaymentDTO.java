package com.uoj.HostelEase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDTO {
    private int payment_id;
    private double amount;
    private String description;
    private String date;
    private String status;
    private String student_id;
    private String warden_id;
}
