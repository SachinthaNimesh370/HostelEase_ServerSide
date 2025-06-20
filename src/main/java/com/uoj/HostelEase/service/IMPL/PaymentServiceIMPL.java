package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.dto.PaymentDTO;
import com.uoj.HostelEase.entity.*;
import com.uoj.HostelEase.repo.PaymentRepository;
import com.uoj.HostelEase.repo.UserRepository;
import com.uoj.HostelEase.service.PaymentService;
import com.uoj.HostelEase.utill.ServiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceIMPL implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;


    public PaymentServiceIMPL(PaymentRepository paymentRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;

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
        if(isExist(paymentDTO.getPayment_id())){
            try {
                PaymentEntity paymentEntity=modelMapper.map(paymentDTO,PaymentEntity.class);
                paymentRepository.save(paymentEntity);
                return new ServiceResponse(true,"Payment Receipt Send.Please Wait For Approve",null);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ServiceResponse(false,"Payment Recept Sending Fail.Please Try Again !",null);
            }
        }else{
            return new ServiceResponse(false,"Payment Not Found",null);
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
}
