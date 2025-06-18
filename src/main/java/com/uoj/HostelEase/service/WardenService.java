package com.uoj.HostelEase.service;

import com.uoj.HostelEase.dto.RoomDTO;
import com.uoj.HostelEase.dto.UserLoginRequestDTO;
import com.uoj.HostelEase.utill.ServiceResponse;

public interface WardenService {
    ServiceResponse newRoom(RoomDTO roomDTO);
    boolean isEnable(String id);
    ServiceResponse updateRoom(RoomDTO roomDTO);
    ServiceResponse deleteRoom(String id);

}
