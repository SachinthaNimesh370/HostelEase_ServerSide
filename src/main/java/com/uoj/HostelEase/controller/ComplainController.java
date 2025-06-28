package com.uoj.HostelEase.controller;


import com.uoj.HostelEase.dto.ComplainDTO;
import com.uoj.HostelEase.dto.UserLoginResponceDTO;
import com.uoj.HostelEase.service.ComplainService;
import com.uoj.HostelEase.utill.ServiceResponse;
import com.uoj.HostelEase.utill.StandardResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/complain")
public class ComplainController {
    private final ComplainService complainService;

    public ComplainController(ComplainService complainService) {
        this.complainService = complainService;
    }

    @PostMapping("/newcomplain")
    public ResponseEntity<StandardResponce> newComplain(@RequestBody ComplainDTO complainDTO) {
        ServiceResponse message = complainService.newComplain(complainDTO);
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

    @PostMapping("/updatecomplain")
    public ResponseEntity<StandardResponce> updateComplain(@RequestBody ComplainDTO complainDTO) {
        ServiceResponse message = complainService.updateComplain(complainDTO);
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
    @PostMapping("/deletecomplain")
    public ResponseEntity<StandardResponce> deleteComplain(@RequestBody ComplainDTO complainDTO) {
        ServiceResponse message = complainService.deleteComplain(complainDTO.getComplain_id());
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

    @GetMapping("/getallcomplain")
    public ResponseEntity<StandardResponce> getAllComplain() {
        ServiceResponse message = complainService.getAllComplain();

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

    @GetMapping("/getpendingcomplain")
    public ResponseEntity<StandardResponce> getPendingComplain() {
        ServiceResponse message = complainService.getPendingComplain();

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
