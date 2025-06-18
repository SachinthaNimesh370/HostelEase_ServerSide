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
        if(isEnable(roomDTO.getRoomId())){
            return new ServiceResponse(false, roomDTO.getRoomId() +" : Is Already Registered",null);
        }
        try {
            RoomEntity roomEntity=modelMapper.map(roomDTO,RoomEntity.class);
            roomEntity.setCurrentCount(0);
            roomRepository.save(roomEntity);
            return new ServiceResponse(true, "Room registered successfully",null);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ServiceResponse(false, "Registration Failed",null);
        }

    }

    @Override
    public boolean isEnable(String id){
        if(roomRepository.existsById(id)){
            return true;
        }
        return false;

    }

    @Override
    public ServiceResponse updateRoom(RoomDTO roomDTO) {
        if(isEnable(roomDTO.getRoomId())){
            try {
                RoomEntity roomEntity=modelMapper.map(roomDTO,RoomEntity.class);
                roomRepository.save(roomEntity);
                return new ServiceResponse(true, "Room Updated successfully",null);
            }catch (Exception e){
                System.out.println(e.getMessage());
                return new ServiceResponse(false, "Update Failed",null);
            }

        }else {
            return new ServiceResponse(false, "Can't Find Room ID",null);
        }
    }

}
