package com.uoj.HostelEase.service;

import com.uoj.HostelEase.dto.WardenDTO;
import com.uoj.HostelEase.utill.ServiceResponse;

public interface WardenService {
    ServiceResponse updateWarden(WardenDTO wardenDTO);

    ServiceResponse getAllWardn();

    ServiceResponse deleteWarden(String wardenId);
}
