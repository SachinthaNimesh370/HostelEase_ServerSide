package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.dto.PaymentDTO;
import com.uoj.HostelEase.entity.*;
import com.uoj.HostelEase.repo.PaymentRepository;
import com.uoj.HostelEase.repo.StudentRepository;
import com.uoj.HostelEase.repo.UserRepository;
import com.uoj.HostelEase.repo.WardenRepository;
import com.uoj.HostelEase.service.PaymentService;
import com.uoj.HostelEase.utill.ServiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceIMPL implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final WardenRepository wardenRepository;
    private final ModelMapper modelMapper;


    public PaymentServiceIMPL(PaymentRepository paymentRepository, ModelMapper modelMapper, UserRepository userRepository, StudentRepository studentRepository, WardenRepository wardenRepository) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;

        this.studentRepository = studentRepository;
        this.wardenRepository = wardenRepository;
    }

    @Override
    public ServiceResponse newPayment(PaymentDTO paymentDTO) {
        try {
            PaymentEntity paymentEntity=modelMapper.map(paymentDTO,PaymentEntity.class);
            paymentRepository.save(paymentEntity);
            return new ServiceResponse(true,"Payment Receipt Send.Please Wait For Approve",null);
        } catch (Exception e) {
            return new ServiceResponse(false,"Payment Recept Sending Fail.Please Try Again !",null);
        }
    }

    @Override
    public ServiceResponse updatePayment(PaymentDTO paymentDTO) {
        Optional<PaymentEntity> existingOpt = paymentRepository.findById(paymentDTO.getPayment_id());

        if (existingOpt.isPresent()) {
            try {
                PaymentEntity existing = existingOpt.get();

                // Update basic fields
                existing.setAmount(paymentDTO.getAmount());
                existing.setDate(paymentDTO.getDate());
                existing.setDescription(paymentDTO.getDescription());
                existing.setStatus(paymentDTO.getStatus());

                // Fetch related student and warden
                StudentEntity student = studentRepository.findById(paymentDTO.getStudent_id()).orElse(null);
                WardenEntity warden = wardenRepository.findById(paymentDTO.getWarden_id()).orElse(null);

                if (student == null || warden == null) {
                    return new ServiceResponse(false, "Invalid student or warden ID", null);
                }

                existing.setStudent(student);
                existing.setWarden(warden);

                paymentRepository.save(existing);
                return new ServiceResponse(true, "Payment Receipt Sent. Please Wait For Approval.", null);

            } catch (Exception e) {
                e.printStackTrace();
                return new ServiceResponse(false, "Payment Receipt Sending Failed. Please Try Again!", null);
            }
        } else {
            return new ServiceResponse(false, "Payment Not Found", null);
        }
    }



    private boolean isExist(int id){
        if(paymentRepository.existsById(id)){
            return true;
        }
        return false;
    }

    @Override
    public ServiceResponse deletePayment(int paymentId) {
        System.out.println(paymentId);
        System.out.println(isExist(paymentId));
        if(isExist(paymentId)){
            try {
                paymentRepository.deleteById(paymentId);
                return new ServiceResponse(true,"Payment Receipt Delete.",null);
            } catch (Exception e) {
                return new ServiceResponse(true,"Payment Can't Delete.Please Try Again !.",null);
            }
        }else {
            return new ServiceResponse(false,"Payment Not Found",null);
        }
    }

    @Override
    public ServiceResponse getAllPayment() {
        List<PaymentEntity> payments = new ArrayList<>();
        try{
            payments.addAll(paymentRepository.findAll());
            if(payments.size()>0){
                return new ServiceResponse(true, payments,null);
            }else{
                return new ServiceResponse(false, "No Payments",null);
            }

        } catch (Exception e) {
            return new ServiceResponse(false, "Sorry.Can't Access",null);
        }
    }
}
