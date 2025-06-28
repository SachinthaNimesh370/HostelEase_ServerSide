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
            // Map fields except student and warden
            PaymentEntity paymentEntity = new PaymentEntity();
            paymentEntity.setAmount(paymentDTO.getAmount());
            paymentEntity.setDate(paymentDTO.getDate());
            paymentEntity.setDescription(paymentDTO.getDescription());
            paymentEntity.setStatus(paymentDTO.getStatus());

            // Fetch and set the student
            Optional<StudentEntity> studentOpt = studentRepository.findById(paymentDTO.getStudent_id());
            if (studentOpt.isEmpty()) {
                return new ServiceResponse(false, "Invalid Student ID", null);
            }
            paymentEntity.setStudent(studentOpt.get());

            // Fetch and set the warden
            Optional<WardenEntity> wardenOpt = wardenRepository.findById(paymentDTO.getWarden_id());
            if (wardenOpt.isEmpty()) {
                return new ServiceResponse(false, "Invalid Warden ID", null);
            }
            paymentEntity.setWarden(wardenOpt.get());

            // Save the payment
            paymentRepository.save(paymentEntity);
            return new ServiceResponse(true, "Payment Receipt Sent. Please Wait for Approval.", null);

        } catch (Exception e) {
            System.out.println(e);
            return new ServiceResponse(false, "Payment Receipt Sending Failed. Please Try Again!", null);
        }
    }


    @Override
    public ServiceResponse updatePayment(PaymentDTO paymentDTO) {
        Optional<PaymentEntity> existingOpt = paymentRepository.findById(paymentDTO.getPayment_id());

        if (existingOpt.isEmpty()) {
            return new ServiceResponse(false, "Payment Not Found", null);
        }
        try {
            PaymentEntity existing = existingOpt.get();
            existing.setAmount(paymentDTO.getAmount());
            existing.setDate(paymentDTO.getDate());
            existing.setDescription(paymentDTO.getDescription());
            existing.setStatus(paymentDTO.getStatus());
            StudentEntity student = studentRepository.findById(paymentDTO.getStudent_id()).orElse(null);
            WardenEntity warden = null;
            if (paymentDTO.getWarden_id() != null) {
                warden = wardenRepository.findById(paymentDTO.getWarden_id()).orElse(null);
            }
            String status = paymentDTO.getStatus();
            if ("Pending".equals(status)) {
                if (student == null) {
                    return new ServiceResponse(false, "Invalid student ID.", null);
                }
                existing.setStudent(student);
                existing.setWarden(warden);
                paymentRepository.save(existing);
                return new ServiceResponse(true, "Payment Receipt Sent. Please Wait For Approval.", null);
            }
            if ("Approved".equals(status)) {
                if (paymentDTO.getWarden_id() == null || warden == null) {
                    return new ServiceResponse(false, "Please assign a valid Warden ID for approval.", null);
                }
                if (student == null) {
                    return new ServiceResponse(false, "Invalid student ID.", null);
                }
                existing.setStudent(student);
                existing.setWarden(warden);
                paymentRepository.save(existing);
                return new ServiceResponse(true, "Payment Approved Successfully.", null);
            }
            return new ServiceResponse(false, "Invalid payment status provided.", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResponse(false, "Payment Receipt Sending Failed. Please Try Again!", null);
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

    @Override
    public ServiceResponse getPendingPayment() {
        try {
            long count = paymentRepository.countByStatus("Pending");
            return new ServiceResponse(true, count, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResponse(false, "Sorry. Can't Access", null);
        }
    }

}
