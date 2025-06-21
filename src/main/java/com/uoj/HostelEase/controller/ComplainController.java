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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/complain")
public class ComplainController {
    private final ComplainService complainService;

    public ComplainController(ComplainService complainService) {
        this.complainService = complainService;
    }

    @PostMapping("/newcomplain")
    public ResponseEntity<StandardResponce> newComplain(@RequestBody ComplainDTO complainDTO) {
        ServiceResponse massage = complainService.newComplain(complainDTO);
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
