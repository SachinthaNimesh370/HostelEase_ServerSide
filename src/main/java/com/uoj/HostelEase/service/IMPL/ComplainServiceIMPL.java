package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.dto.ComplainDTO;
import com.uoj.HostelEase.entity.ComplainEntity;
import com.uoj.HostelEase.repo.ComplainRepository;
import com.uoj.HostelEase.service.ComplainService;
import com.uoj.HostelEase.utill.ServiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ComplainServiceIMPL implements ComplainService {
    private final ComplainRepository complainRepository;
    private final ModelMapper modelMapper;

    public ComplainServiceIMPL(ComplainRepository complainRepository, ModelMapper modelMapper) {
        this.complainRepository = complainRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ServiceResponse newComplain(ComplainDTO complainDTO) {
        try {
            ComplainEntity complainEntity = modelMapper.map(complainDTO, ComplainEntity.class);
            complainRepository.save(complainEntity);
            return new ServiceResponse(true,"Complain Saved",null);
        } catch (Exception e) {
            System.out.println(e);
            return new ServiceResponse(false,"Complain Can't Save",null);
        }

    }

    @Override
    public ServiceResponse updateComplain(ComplainDTO complainDTO) {
        if(isExist(complainDTO.getComplain_id())){
            try {
                ComplainEntity complainEntity = modelMapper.map(complainDTO, ComplainEntity.class);
                complainRepository.save(complainEntity);
                return new ServiceResponse(true,"Complain Saved",null);
            } catch (Exception e) {
                System.out.println(e);
                return new ServiceResponse(false,"Complain Can't Save",null);
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

    private boolean isExist(int id){
        if(complainRepository.existsById(id)){
            return true;
        }
        return false;
    }
}
