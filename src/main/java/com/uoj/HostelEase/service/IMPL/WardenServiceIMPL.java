package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.dto.RoomDTO;
import com.uoj.HostelEase.entity.RoomEntity;
import com.uoj.HostelEase.entity.UserEntity;
import com.uoj.HostelEase.repo.RoomRepository;
import com.uoj.HostelEase.service.WardenService;
import com.uoj.HostelEase.utill.ServiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WardenServiceIMPL implements WardenService {
    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;

    public WardenServiceIMPL(ModelMapper modelMapper, RoomRepository roomRepository) {
        this.modelMapper = modelMapper;
        this.roomRepository = roomRepository;
    }

    @Override
    public ServiceResponse newRoom(RoomDTO roomDTO) {

        try {
            RoomEntity roomEntity=modelMapper.map(roomDTO,RoomEntity.class);
            roomRepository.save(roomEntity);
            return new ServiceResponse(true, "Room registered successfully",null);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ServiceResponse(false, "Registration Failed",null);
        }

    }
}
