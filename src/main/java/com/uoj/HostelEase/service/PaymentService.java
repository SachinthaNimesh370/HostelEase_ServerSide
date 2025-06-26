package com.uoj.HostelEase.service;

import com.uoj.HostelEase.dto.PaymentDTO;
import com.uoj.HostelEase.utill.ServiceResponse;

public interface PaymentService {
    ServiceResponse newPayment(PaymentDTO paymentDTO);

    ServiceResponse updatePayment(PaymentDTO paymentDTO);

    ServiceResponse deletePayment(int paymentId);

    ServiceResponse getAllPayment();

    ServiceResponse getPendingPayment();
}

