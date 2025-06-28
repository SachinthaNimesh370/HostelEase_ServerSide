package com.uoj.HostelEase.controller;

import com.uoj.HostelEase.dto.RoomDTO;
import com.uoj.HostelEase.dto.UserLoginResponceDTO;
import com.uoj.HostelEase.service.RoomService;
import com.uoj.HostelEase.utill.ServiceResponse;
import com.uoj.HostelEase.utill.StandardResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService wardenService;

    public RoomController(RoomService wardenService) {
        this.wardenService = wardenService;
    }

    @GetMapping("getallroom")
    public ResponseEntity<StandardResponce> getAllRooms() {
        ServiceResponse message =wardenService.getAllRooms();
        if(message.isSuccess()) {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200, "Ok", new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()), message.getRole()),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getnoroom")
    public ResponseEntity<StandardResponce> getNoOfRooms() {
        ServiceResponse message =wardenService.getNoOfRooms();
        if(message.isSuccess()) {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200, "Ok", new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()), message.getRole()),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getnoroomavailable")
    public ResponseEntity<StandardResponce> getNoOfRoomsAvailable() {
        ServiceResponse message =wardenService.getNoOfRoomsAvailable();
        if(message.isSuccess()) {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200, "Ok", new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()), message.getRole()),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getoccupancy")
    public ResponseEntity<StandardResponce> getOccupancy() {
        ServiceResponse message =wardenService.getOccupancy();
        if(message.isSuccess()) {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200, "Ok", new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()), message.getRole()),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }




    @PostMapping("/newroom")
    public ResponseEntity<StandardResponce> newRoom(@RequestBody RoomDTO roomDTO) {
        ServiceResponse message =wardenService.newRoom(roomDTO);
        if(message.isSuccess()) {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            201, "Created", new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()), message.getRole()),
                    HttpStatus.CREATED);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateroom")
    public ResponseEntity<StandardResponce> updateRoom(@RequestBody RoomDTO roomDTO) {
        System.out.println(roomDTO.getRoom_id());
        ServiceResponse message =wardenService.updateRoom(roomDTO);
        if(message.isSuccess()) {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200, "Ok", new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()), message.getRole()),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/deleteroom")
    public ResponseEntity<StandardResponce> deleteRoom(@RequestBody RoomDTO roomDTO) {
        ServiceResponse message= wardenService.deleteRoom(roomDTO.getRoom_id());
        if(message.isSuccess()) {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200, "Ok", new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()), message.getRole()),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }

    }
}
