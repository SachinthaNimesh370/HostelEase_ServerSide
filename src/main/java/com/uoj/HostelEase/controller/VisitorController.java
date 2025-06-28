package com.uoj.HostelEase.controller;

import com.uoj.HostelEase.dto.UserLoginResponceDTO;
import com.uoj.HostelEase.dto.VisitorDTO;
import com.uoj.HostelEase.service.VisitorService;
import com.uoj.HostelEase.utill.ServiceResponse;
import com.uoj.HostelEase.utill.StandardResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/visitor")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping("/newvisitor")
    public ResponseEntity<StandardResponce> newVisitor(@RequestBody VisitorDTO visitorDTO) {
        ServiceResponse message = visitorService.newVisitor(visitorDTO);
        if(message.isSuccess()) {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            201, "Created", new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()), message.getRole()),
                    HttpStatus.CREATED);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updatevisitor")
    public ResponseEntity<StandardResponce> updateVisitor(@RequestBody VisitorDTO visitorDTO) {
        ServiceResponse message = visitorService.updateVisitor(visitorDTO);
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

    @PostMapping("/deletevisitor")
    public ResponseEntity<StandardResponce> deleteVisitor(@RequestBody VisitorDTO visitorDTO) {
        ServiceResponse message = visitorService.deleteVisitor(visitorDTO.getVisitor_id());
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
    @GetMapping("getallvisitor")
    public ResponseEntity<StandardResponce> getAllVisitor() {
        ServiceResponse message = visitorService.getAllVisitor();
        if(message.isSuccess()) {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200, "Ok", new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()),message.getRole()),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getpendingvisitor")
    public ResponseEntity<StandardResponce> getPendingVisitor() {
        ServiceResponse message = visitorService.getPendingVisitor();
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
