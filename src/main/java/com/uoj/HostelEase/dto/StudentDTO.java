package com.uoj.HostelEase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentDTO {

    private String student_id;
    private String duration;
    private String admin_id;
    private String warden_id;
    private int room_id;
}
