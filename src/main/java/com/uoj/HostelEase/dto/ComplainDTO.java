package com.uoj.HostelEase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComplainDTO {
    private int complain_id;
    private String catagory;
    private String content;
    private String date;
    private String time;
    private String status;
    private String student_id;
    private String warden_id;
}
