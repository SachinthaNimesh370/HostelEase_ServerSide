package com.uoj.HostelEase.service;

import com.uoj.HostelEase.dto.VisitorDTO;
import com.uoj.HostelEase.utill.ServiceResponse;

public interface VisitorService {
    ServiceResponse newVisitor(VisitorDTO visitorDTO);
    ServiceResponse updateVisitor(VisitorDTO visitorDTO);
    ServiceResponse deleteVisitor(int visitorId);

    ServiceResponse getAllVisitor();

}
