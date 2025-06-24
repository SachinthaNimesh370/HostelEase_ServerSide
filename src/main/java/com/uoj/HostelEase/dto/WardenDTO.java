package com.uoj.HostelEase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WardenDTO {
    private String warden_id;
    private String hostel_name;
    private String block;
    private String admin_id;
}
