package com.uoj.HostelEase.controller;

import com.uoj.HostelEase.dto.UserApproveDTO;
import com.uoj.HostelEase.dto.UserLoginRequestDTO;
import com.uoj.HostelEase.dto.UserLoginResponceDTO;
import com.uoj.HostelEase.dto.UserRegRequestDTO;
import com.uoj.HostelEase.service.UserService;
import com.uoj.HostelEase.utill.ServiceResponse;
import com.uoj.HostelEase.utill.StandardResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/signup")
    public ResponseEntity<StandardResponce> signUp(@RequestBody UserRegRequestDTO userRegRequestDTO){
        ServiceResponse message = userService.signUp(userRegRequestDTO);
        if(message.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            201, "Created", message.getObject(),null),
                    HttpStatus.CREATED);
        }else{
            System.out.println(message.getObject());
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400, "Bad", message.getObject(),null),
                    HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/signin")
    public ResponseEntity<StandardResponce> signIn(@RequestBody UserLoginRequestDTO userLoginRequestDTO){

        ServiceResponse message = userService.signIn(userLoginRequestDTO);
        System.out.println(message.getObject());
        if(message.isSuccess()){
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200,"Ok",new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()),message.getRole()),
                    HttpStatus.OK);
        }else {
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/userupdate")
    public ResponseEntity<StandardResponce> userUpdate(@RequestBody UserApproveDTO userApproveDTO){
        ServiceResponse message = userService.userUpdate(userApproveDTO);
        if(message.isSuccess()){
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200,"Ok",new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()),message.getRole()),
                    HttpStatus.OK);
        }else {
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getalluser")
    public ResponseEntity<StandardResponce> getAllUser(){
        ServiceResponse message = userService.getAllUser();
        if(message.isSuccess()){
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200,"Ok",new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()),message.getRole()),
                    HttpStatus.OK);
        }else {
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getallwarden")
    public ResponseEntity<StandardResponce> getAllWarden(){
        ServiceResponse message = userService.getAllWarden();
        if(message.isSuccess()){
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200,"Ok",new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()),message.getRole()),
                    HttpStatus.OK);
        }else {
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getalladmin")
    public ResponseEntity<StandardResponce> getAllAdmin(){
        ServiceResponse message = userService.getAllAdmin();
        if(message.isSuccess()){
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200,"Ok",new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()),message.getRole()),
                    HttpStatus.OK);
        }else {
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getallsecurity")
    public ResponseEntity<StandardResponce> getAllSecurity(){
        ServiceResponse message = userService.getAllSecurity();
        if(message.isSuccess()){
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200,"Ok",new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()),message.getRole()),
                    HttpStatus.OK);
        }else {
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getallstudent")
    public ResponseEntity<StandardResponce> getAllStudent(){
        ServiceResponse message = userService.getAllStudent();
        if(message.isSuccess()){
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200,"Ok",new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()),message.getRole()),
                    HttpStatus.OK);
        }else {
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/deleteuser")
    public ResponseEntity<StandardResponce> deleteUser(@RequestBody UserRegRequestDTO userDTO){
        ServiceResponse message = userService.deleteUser(userDTO.getRegNo());
        if(message.isSuccess()){
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200,"Ok",new UserLoginResponceDTO(
                            message.getObject(), LocalDateTime.now()),message.getRole()),
                    HttpStatus.OK);
        }else {
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad Request", new UserLoginResponceDTO(
                            message.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
