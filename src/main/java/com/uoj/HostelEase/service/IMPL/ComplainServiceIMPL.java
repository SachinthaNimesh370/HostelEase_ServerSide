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
        return null;
    }

    @Override
    public ServiceResponse deleteComplain(ComplainDTO complainDTO) {
        return null;
    }
}
