package com.uoj.HostelEase.service;

import com.uoj.HostelEase.dto.StudentDTO;
import com.uoj.HostelEase.utill.ServiceResponse;

public interface StudentService {
    ServiceResponse updateStudent(StudentDTO studentDTO);

    ServiceResponse getAllStudent();

    ServiceResponse getNoOfStudents();
}
