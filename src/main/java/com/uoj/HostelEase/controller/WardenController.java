package com.uoj.HostelEase.controller;

import com.uoj.HostelEase.dto.UserLoginResponceDTO;
import com.uoj.HostelEase.dto.VisitorDTO;
import com.uoj.HostelEase.dto.WardenDTO;
import com.uoj.HostelEase.service.WardenService;
import com.uoj.HostelEase.utill.ServiceResponse;
import com.uoj.HostelEase.utill.StandardResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/warden")
public class WardenController {
    private final WardenService wardenService;

    public WardenController(WardenService wardenService) {
        this.wardenService = wardenService;
    }

    @GetMapping("/getallwarden")
    public ResponseEntity<StandardResponce> getAllWarden() {
        ServiceResponse massage = wardenService.getAllWardn();
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

    @PostMapping("/updatewarden")
    public ResponseEntity<StandardResponce> updateWarden(@RequestBody WardenDTO wardenDTO) {
        ServiceResponse massage = wardenService.updateWarden(wardenDTO);
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
    @PostMapping("/deletewarden")
    public ResponseEntity<StandardResponce> deleteWarden(@RequestBody WardenDTO wardenDTO) {
        ServiceResponse massage = wardenService.deleteWarden(wardenDTO.getWarden_id());
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
