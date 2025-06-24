package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.dto.ComplainDTO;
import com.uoj.HostelEase.entity.ComplainEntity;
import com.uoj.HostelEase.entity.RoomEntity;
import com.uoj.HostelEase.entity.StudentEntity;
import com.uoj.HostelEase.entity.WardenEntity;
import com.uoj.HostelEase.repo.ComplainRepository;
import com.uoj.HostelEase.repo.StudentRepository;
import com.uoj.HostelEase.repo.WardenRepository;
import com.uoj.HostelEase.service.ComplainService;
import com.uoj.HostelEase.utill.ServiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplainServiceIMPL implements ComplainService {
    private final ComplainRepository complainRepository;
    private final StudentRepository studentRepository;
    private final WardenRepository wardenRepository;
    private final ModelMapper modelMapper;

    public ComplainServiceIMPL(ComplainRepository complainRepository, StudentRepository studentRepository, WardenRepository wardenRepository, ModelMapper modelMapper) {
        this.complainRepository = complainRepository;
        this.studentRepository = studentRepository;
        this.wardenRepository = wardenRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ServiceResponse newComplain(ComplainDTO complainDTO) {
        try {
            ComplainEntity complainEntity = new ComplainEntity();

            // Set basic fields
            complainEntity.setCatagory(complainDTO.getCatagory());
            complainEntity.setContent(complainDTO.getContent());
            complainEntity.setDate(complainDTO.getDate());
            complainEntity.setTime(complainDTO.getTime());
            complainEntity.setStatus(complainDTO.getStatus());

            // Fetch and set student
            StudentEntity student = studentRepository.findById(complainDTO.getStudent_id()).orElse(null);
            if (student == null) {
                return new ServiceResponse(false, "Student not found", null);
            }
            complainEntity.setStudent(student);

            // Save
            complainRepository.save(complainEntity);
            return new ServiceResponse(true, "Complain Saved", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResponse(false, "Complain Can't Save", null);
        }
    }


    @Override
    public ServiceResponse updateComplain(ComplainDTO complainDTO) {
        if(isExist(complainDTO.getComplain_id())){
            if(complainDTO.getStatus() != "Pending" && complainDTO.getWarden_id() != null){
                try {
                    ComplainEntity complainEntity = modelMapper.map(complainDTO, ComplainEntity.class);
                    complainRepository.save(complainEntity);
                    return new ServiceResponse(true,"Complain Update",null);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ServiceResponse(false,"Complain Can't Update",null);
                }
            }else{
                return new ServiceResponse(false,"Please Check Warden Id !",null);
            }

        }else {
            return new ServiceResponse(false,"Complain Not Found",null);
        }

    }

    @Override
    public ServiceResponse deleteComplain(int complainId) {
        if(isExist(complainId)){
            try{
                complainRepository.deleteById(complainId);
                return new ServiceResponse(true,"Complain Deleted",null);
            } catch (Exception e) {
                return new ServiceResponse(false,"Complain Can't Delete.Please Try Again!",null);
            }
        }else {
            return new ServiceResponse(false,"Complain Not Found",null);
        }
    }

    @Override
    public ServiceResponse getAllComplain() {
        List<ComplainEntity> rooms = new ArrayList<>();
        try{
            rooms.addAll(complainRepository.findAll());
            if(rooms.size()>0){
                return new ServiceResponse(true, rooms,null);
            }else{
                return new ServiceResponse(false, "Not Complains",null);
            }

        } catch (Exception e) {
            return new ServiceResponse(false, "Sorry.Can't Access",null);
        }

    }

    private boolean isExist(int id){
        if(complainRepository.existsById(id)){
            return true;
        }
        return false;
    }
}
