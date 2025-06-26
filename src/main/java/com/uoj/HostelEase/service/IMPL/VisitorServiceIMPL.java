package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.dto.VisitorDTO;
import com.uoj.HostelEase.entity.*;
import com.uoj.HostelEase.repo.*;
import com.uoj.HostelEase.service.VisitorService;
import com.uoj.HostelEase.utill.ServiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitorServiceIMPL implements VisitorService {

    private final VisitorRepository visitorRepository;
    private final StudentRepository studentRepository;
    private final WardenRepository wardenRepository;
    private final SecurityRepository securityRepository;
    private final ModelMapper modelMapper;

    public VisitorServiceIMPL(
            VisitorRepository visitorRepository,
            StudentRepository studentRepository,
            WardenRepository wardenRepository,
            SecurityRepository securityRepository,
            ModelMapper modelMapper) {
        this.visitorRepository = visitorRepository;
        this.studentRepository = studentRepository;
        this.wardenRepository = wardenRepository;
        this.securityRepository = securityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ServiceResponse newVisitor(VisitorDTO visitorDTO) {
        try {
            StudentEntity student = studentRepository.findById(visitorDTO.getStudent_id()).orElse(null);
            WardenEntity warden = wardenRepository.findById(visitorDTO.getWarden_id()).orElse(null);
            SecurityEntity security = securityRepository.findById(visitorDTO.getSecurity_id()).orElse(null);

            VisitorEntity visitorEntity = new VisitorEntity();
            visitorEntity.setNic(visitorDTO.getNic());
            visitorEntity.setName(visitorDTO.getName());
            visitorEntity.setDate(visitorDTO.getDate());
            visitorEntity.setEntryTime(visitorDTO.getEntryTime());
            visitorEntity.setExitTime(visitorDTO.getExitTime());
            visitorEntity.setState("Pending");
            visitorEntity.setStudent(student);
            visitorEntity.setWarden(warden);
            visitorEntity.setSecurity(security);

            visitorRepository.save(visitorEntity);

            return new ServiceResponse(true, "Request Is Sent. Please Wait For Approval.", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResponse(false, "Request Cannot Be Sent. Please Try Again.", null);
        }
    }

    @Override
    public ServiceResponse updateVisitor(VisitorDTO visitorDTO) {
        if (!isExist(visitorDTO.getVisitor_id())) {
            return new ServiceResponse(false, "Request Not Found", null);
        }

        if (visitorDTO.getStudent_id() == null || visitorDTO.getStudent_id().isEmpty()) {
            return new ServiceResponse(false, "Student ID is required.", null);
        }

        String state = visitorDTO.getState();
        if ("Pending".equals(state) && visitorDTO.getWarden_id() != null) {
            return new ServiceResponse(false, "Warden ID should not be assigned while state is Pending.", null);
        }

        if ("Approved".equals(state) && (visitorDTO.getWarden_id() == null || visitorDTO.getWarden_id().isEmpty())) {
            return new ServiceResponse(false, "Warden ID is required when approving the request.", null);
        }

        try {
            VisitorEntity existingVisitor = visitorRepository.findById(visitorDTO.getVisitor_id()).orElse(null);
            if (existingVisitor == null) {
                return new ServiceResponse(false, "Visitor not found", null);
            }

            StudentEntity student = studentRepository.findById(visitorDTO.getStudent_id()).orElse(null);
            WardenEntity warden = wardenRepository.findById(visitorDTO.getWarden_id()).orElse(null);
            SecurityEntity security = securityRepository.findById(visitorDTO.getSecurity_id()).orElse(null);

            existingVisitor.setNic(visitorDTO.getNic());
            existingVisitor.setName(visitorDTO.getName());
            existingVisitor.setDate(visitorDTO.getDate());
            existingVisitor.setEntryTime(visitorDTO.getEntryTime());
            existingVisitor.setExitTime(visitorDTO.getExitTime());
            existingVisitor.setState(visitorDTO.getState());
            existingVisitor.setStudent(student);
            existingVisitor.setWarden(warden);
            existingVisitor.setSecurity(security);

            visitorRepository.save(existingVisitor);

            return new ServiceResponse(true, "Request is Updated Successfully", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResponse(false, "Request Cannot Be Updated. Please Try Again.", null);
        }
    }

    @Override
    public ServiceResponse deleteVisitor(int visitorId) {
        if (isExist(visitorId)) {
            try {
                visitorRepository.deleteById(visitorId);
                return new ServiceResponse(true, "Request is Deleted", null);
            } catch (Exception e) {
                return new ServiceResponse(false, "Request Can't Be Deleted", null);
            }
        } else {
            return new ServiceResponse(false, "Request Not Found", null);
        }
    }

    @Override
    public ServiceResponse getAllVisitor() {
        try {
            List<VisitorEntity> visitors = visitorRepository.findAll();
            if (!visitors.isEmpty()) {
                return new ServiceResponse(true, visitors, null);
            } else {
                return new ServiceResponse(false, "No Visitors Found", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResponse(false, "Error Accessing Visitors", null);
        }
    }

    @Override
    public ServiceResponse getPendingVisitor() {
        try {
            long count = visitorRepository.countByState("Pending");
            return new ServiceResponse(true, count, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResponse(false, "Error Accessing Pending Visitors", null);
        }
    }

    private boolean isExist(int id) {
        return visitorRepository.existsById(id);
    }
}
