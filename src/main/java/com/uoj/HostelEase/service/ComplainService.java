package com.uoj.HostelEase.service;

import com.uoj.HostelEase.dto.ComplainDTO;
import com.uoj.HostelEase.utill.ServiceResponse;

public interface ComplainService {
    ServiceResponse newComplain(ComplainDTO complainDTO);
    ServiceResponse updateComplain(ComplainDTO complainDTO);
    ServiceResponse deleteComplain(ComplainDTO complainDTO);
}
