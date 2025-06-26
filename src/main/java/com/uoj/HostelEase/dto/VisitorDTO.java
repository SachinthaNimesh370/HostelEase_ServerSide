package com.uoj.HostelEase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VisitorDTO {
    private int visitor_id;
    private String nic;
    private String name;
    private String date;
    private String entryTime;
    private String exitTime;
    private String state;
    private String student_id;
    private String warden_id;
    private String security_id;
}
