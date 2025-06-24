package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.dto.VisitorDTO;
import com.uoj.HostelEase.entity.RoomEntity;
import com.uoj.HostelEase.entity.StudentEntity;
import com.uoj.HostelEase.entity.VisitorEntity;
import com.uoj.HostelEase.entity.WardenEntity;
import com.uoj.HostelEase.repo.StudentRepository;
import com.uoj.HostelEase.repo.VisitorRepository;
import com.uoj.HostelEase.repo.WardenRepository;
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
    private final ModelMapper modelMapper;

    public VisitorServiceIMPL(VisitorRepository visitorRepository, StudentRepository studentRepository, WardenRepository wardenRepository, ModelMapper modelMapper) {
        this.visitorRepository = visitorRepository;
        this.studentRepository = studentRepository;
        this.wardenRepository = wardenRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ServiceResponse newVisitor(VisitorDTO visitorDTO) {
        try {
            StudentEntity student = studentRepository.findById(visitorDTO.getStudent_id()).orElse(null);
            WardenEntity warden = wardenRepository.findById(visitorDTO.getWarden_id()).orElse(null);
            VisitorEntity visitorEntity = new VisitorEntity();

            // Do NOT set the visitor_id manually
            visitorEntity.setNic(visitorDTO.getNic());
            visitorEntity.setName(visitorDTO.getName());
            visitorEntity.setDate(visitorDTO.getDate());
            visitorEntity.setEntryTime(visitorDTO.getEntryTime());
            visitorEntity.setExitTime(visitorDTO.getExitTime());
            visitorEntity.setState("Pending");

            visitorEntity.setStudent(student);
            visitorEntity.setWarden(warden);

            visitorRepository.save(visitorEntity);
            return new ServiceResponse(true,"Request Is Send.Please Wait For Approve",null);
        } catch (Exception e) {
            System.out.println(e);
            return new ServiceResponse(false,"Request Can not Send.Please Try Again",null);
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
            VisitorEntity visitorEntity = modelMapper.map(visitorDTO, VisitorEntity.class);
            visitorRepository.save(visitorEntity);
            return new ServiceResponse(true, "Request is Approved", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResponse(false, "Request cannot be approved. Please try again.", null);
        }
    }


    @Override
    public ServiceResponse deleteVisitor(int visitorId) {
        if(isExist(visitorId)){
            try{
                visitorRepository.deleteById(visitorId);
                return new ServiceResponse(true,"Request Is Deleted",null);
            } catch (Exception e) {
                return new ServiceResponse(false,"Request Can't Delete",null);
            }
        }else {
            return new ServiceResponse(false,"Request Not Found",null);
        }
    }

    @Override
    public ServiceResponse getAllVisitor() {
        List<VisitorEntity> visitors = new ArrayList<>();
        try{
            visitors.addAll(visitorRepository.findAll());
            if(visitors.size()>0){
                return new ServiceResponse(true, visitors,null);
            }else{
                return new ServiceResponse(false, "No Any Visitors",null);
            }

        } catch (Exception e) {
            return new ServiceResponse(false, "Sorry.Can't Access",null);
        }
    }

    private boolean isExist(int id){
        if(visitorRepository.existsById(id)){
            return true;
        }
        return false;
    }
}
