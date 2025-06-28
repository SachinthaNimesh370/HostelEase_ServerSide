package com.uoj.HostelEase.controller;

import com.uoj.HostelEase.dto.PaymentDTO;
import com.uoj.HostelEase.dto.UserLoginResponceDTO;
import com.uoj.HostelEase.service.PaymentService;
import com.uoj.HostelEase.utill.ServiceResponse;
import com.uoj.HostelEase.utill.StandardResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/newpayment")
    public ResponseEntity<StandardResponce> newPayment(@RequestBody PaymentDTO paymentDTO) {
        ServiceResponse message =paymentService.newPayment(paymentDTO);
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
    @PostMapping("/updatepayment")
    public ResponseEntity<StandardResponce> updatePayment(@RequestBody PaymentDTO paymentDTO) {
        ServiceResponse message =paymentService.updatePayment(paymentDTO);
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

    @PostMapping("/deletepayment")
    public ResponseEntity<StandardResponce> deletePayment(@RequestBody PaymentDTO paymentDTO) {
        System.out.println(paymentDTO.getPayment_id());
        ServiceResponse message = paymentService.deletePayment(paymentDTO.getPayment_id());
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

    @GetMapping("/getallpayment")
    public ResponseEntity<StandardResponce> getAllPayment(){
        ServiceResponse message = paymentService.getAllPayment();
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

    @GetMapping("/getpendingpayment")
    public ResponseEntity<StandardResponce> getPendingPayment(){
        ServiceResponse message = paymentService.getPendingPayment();
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
