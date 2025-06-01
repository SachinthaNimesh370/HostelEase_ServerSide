package com.uoj.HostelEase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserLoginResponceDTO {
    private Object massage;
    private LocalDateTime time;
}