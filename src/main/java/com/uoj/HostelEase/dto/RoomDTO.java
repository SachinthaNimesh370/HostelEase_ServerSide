package com.uoj.HostelEase.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDTO {
    private String roomId;
    private String roomNo;
    private String type;
    private boolean ac;
    private int currentCount;
}
