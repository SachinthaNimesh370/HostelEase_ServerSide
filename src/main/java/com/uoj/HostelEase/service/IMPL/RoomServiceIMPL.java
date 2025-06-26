package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.dto.RoomDTO;
import com.uoj.HostelEase.entity.RoomEntity;
import com.uoj.HostelEase.repo.RoomRepository;
import com.uoj.HostelEase.service.RoomService;
import com.uoj.HostelEase.utill.ServiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceIMPL implements RoomService {
    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;

    public RoomServiceIMPL(ModelMapper modelMapper, RoomRepository roomRepository) {
        this.modelMapper = modelMapper;
        this.roomRepository = roomRepository;
    }

    @Override
    public ServiceResponse newRoom(RoomDTO roomDTO) {
        if(isEnable(roomDTO.getRoom_id())){
            return new ServiceResponse(false, roomDTO.getRoom_id() +" : Is Already Registered",null);
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
        if(isEnable(roomDTO.getRoom_id())){
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

    @Override
    public ServiceResponse deleteRoom(String id) {
        if(isEnable(id)){
            try {
                roomRepository.deleteById(id);
                return new ServiceResponse(true, "Room Deleted successfully",null);
            }catch (Exception e){
                System.out.println(e.getMessage());
                return new ServiceResponse(false, "Delete Failed",null);
            }
        }else{
            return new ServiceResponse(false, "Can't Find Room ID",null);
        }
    }

    @Override
    public ServiceResponse getAllRooms() {

        List<RoomEntity> rooms = new ArrayList<>();
        try{
            rooms.addAll(roomRepository.findAll());
            if(rooms.size()>0){
                return new ServiceResponse(true, rooms,null);
            }else{
                return new ServiceResponse(false, "Not Registered Rooms",null);
            }

        } catch (Exception e) {
            return new ServiceResponse(false, "Sorry.Can't Access",null);
        }
    }

    @Override
    public ServiceResponse getNoOfRooms() {
        try {
            long rooms = roomRepository.count();
            return new ServiceResponse(true, rooms, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResponse(false, "Failed to fetch room count: " + e.getMessage(), null);
        }
    }

    @Override
    public ServiceResponse getNoOfRoomsAvailable() {
        try {
            // Fetch count of rooms where currentCount == 0
            long availableRooms = roomRepository.countByCurrentCount(0);
            return new ServiceResponse(true, availableRooms, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResponse(false, "Failed to fetch available room count: " + e.getMessage(), null);
        }
    }


}
