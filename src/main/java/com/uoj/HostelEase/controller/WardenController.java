package com.uoj.HostelEase.controller;

import com.uoj.HostelEase.dto.RoomDTO;
import com.uoj.HostelEase.dto.UserLoginResponceDTO;
import com.uoj.HostelEase.service.WardenService;
import com.uoj.HostelEase.utill.ServiceResponse;
import com.uoj.HostelEase.utill.StandardResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/warden")
public class WardenController {
    private final WardenService wardenService;

    public WardenController(WardenService wardenService) {
        this.wardenService = wardenService;
    }

    //New Room Save
    @PostMapping("/newroom")
    public ResponseEntity<StandardResponce> newRoom(@RequestBody RoomDTO roomDTO) {
        ServiceResponse massage =wardenService.newRoom(roomDTO);
        if(massage.isSuccess()) {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200, "Ok", new UserLoginResponceDTO(
                            massage.getObject(), LocalDateTime.now()), massage.getRole()),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad", new UserLoginResponceDTO(
                            massage.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateroom")
    public ResponseEntity<StandardResponce> updateRoom(@RequestBody RoomDTO roomDTO) {
        ServiceResponse massage =wardenService.updateRoom(roomDTO);
        if(massage.isSuccess()) {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200, "Ok", new UserLoginResponceDTO(
                            massage.getObject(), LocalDateTime.now()), massage.getRole()),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad", new UserLoginResponceDTO(
                            massage.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteroom")
    public ResponseEntity<StandardResponce> deleteRoom(@RequestBody RoomDTO roomDTO) {
        ServiceResponse massage= wardenService.deleteRoom(roomDTO.getRoomId());
        if(massage.isSuccess()) {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200, "Ok", new UserLoginResponceDTO(
                            massage.getObject(), LocalDateTime.now()), massage.getRole()),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad", new UserLoginResponceDTO(
                            massage.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }

    }

}
