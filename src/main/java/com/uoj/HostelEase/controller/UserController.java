package com.uoj.HostelEase.controller;

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
        ServiceResponse massage = userService.signUp(userRegRequestDTO);
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200, "Ok", massage.getObject(),null),
                    HttpStatus.OK);
        }else{
            System.out.println(massage.getObject());
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400, "Bad", massage.getObject(),null),
                    HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/signin")
    public ResponseEntity<StandardResponce> signIn(@RequestBody UserLoginRequestDTO userLoginRequestDTO){

        ServiceResponse massage = userService.signIn(userLoginRequestDTO);
        System.out.println(massage.getObject());
        if(massage.isSuccess()){
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200,"Ok",new UserLoginResponceDTO(
                            massage.getObject(), LocalDateTime.now()),massage.getRole()),
                    HttpStatus.OK);
        }else {
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad", new UserLoginResponceDTO(
                            massage.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/userupdate")
    public ResponseEntity<StandardResponce> userUpdate(@RequestBody UserRegRequestDTO userRegRequestDTO){
        ServiceResponse massage = userService.userUpdate(userRegRequestDTO);
        if(massage.isSuccess()){
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            200,"Ok",new UserLoginResponceDTO(
                            massage.getObject(), LocalDateTime.now()),massage.getRole()),
                    HttpStatus.OK);
        }else {
            return  new ResponseEntity<StandardResponce>(
                    new StandardResponce(
                            400,"Bad", new UserLoginResponceDTO(
                            massage.getObject(),null),null),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
