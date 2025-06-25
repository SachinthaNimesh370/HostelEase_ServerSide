package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.dto.StudentDTO;
import com.uoj.HostelEase.dto.WardenDTO;
import com.uoj.HostelEase.entity.AdminEntity;
import com.uoj.HostelEase.entity.RoomEntity;
import com.uoj.HostelEase.entity.StudentEntity;

import com.uoj.HostelEase.entity.WardenEntity;
import com.uoj.HostelEase.repo.AdminRepository;
import com.uoj.HostelEase.repo.RoomRepository;
import com.uoj.HostelEase.repo.StudentRepository;
import com.uoj.HostelEase.repo.WardenRepository;
import com.uoj.HostelEase.service.StudentService;
import com.uoj.HostelEase.utill.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceIMPL implements StudentService {
    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
    private final WardenRepository wardenRepository;
    private final RoomRepository roomRepository;

    public StudentServiceIMPL(StudentRepository studentRepository, AdminRepository adminRepository, WardenRepository wardenRepository, RoomRepository roomRepository) {
        this.studentRepository = studentRepository;
        this.adminRepository = adminRepository;
        this.wardenRepository = wardenRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public ServiceResponse updateStudent(StudentDTO studentDTO) {
        Optional<StudentEntity> optionalStudent = studentRepository.findById(studentDTO.getStudent_id());

        if (optionalStudent.isEmpty()) {
            return new ServiceResponse(false, "Student not found with ID", null);
        }

        if (studentDTO.getAdmin_id() == null || studentDTO.getAdmin_id().isEmpty()) {
            return new ServiceResponse(false, "Admin ID must not be null or empty.", null);
        }

        Optional<AdminEntity> optionalAdmin = adminRepository.findById(studentDTO.getAdmin_id());
        if (optionalAdmin.isEmpty()) {
            return new ServiceResponse(false, "Admin not found with ID: " + studentDTO.getAdmin_id(), null);
        }

        Optional<WardenEntity> optionalWarden = wardenRepository.findById(studentDTO.getWarden_id());
        if (optionalWarden.isEmpty()) {
            return new ServiceResponse(false, "Warden not found with ID: " + studentDTO.getWarden_id(), null);
        }

        Optional<RoomEntity> optionalRoom = roomRepository.findById(studentDTO.getRoom_id());
        if (optionalRoom.isEmpty()) {
            return new ServiceResponse(false, "Room not found with ID: " + studentDTO.getRoom_id(), null);
        }

        StudentEntity studentEntity = optionalStudent.get();
        studentEntity.setDuration(studentDTO.getDuration());
        studentEntity.setAdmin(optionalAdmin.get());
        studentEntity.setWarden(optionalWarden.get());
        studentEntity.setRoom(optionalRoom.get());

        studentRepository.save(studentEntity);

        return new ServiceResponse(true, "Student updated successfully.", null);
    }

    @Override
    public ServiceResponse getAllStudent() {
        try {
            List<StudentEntity> students = studentRepository.findAll();

            if (students.isEmpty()) {
                return new ServiceResponse(false, "No students found.", null);
            }

            List<StudentDTO> studentDTOs = students.stream().map(student -> {
                StudentDTO dto = new StudentDTO();
                dto.setStudent_id(student.getStudent_id());
                dto.setDuration(student.getDuration());

                if (student.getAdmin() != null) {
                    dto.setAdmin_id(student.getAdmin().getAdmin_id());
                }

                if (student.getWarden() != null) {
                    dto.setWarden_id(student.getWarden().getWarden_id());
                }

                if (student.getRoom() != null) {
                    dto.setRoom_id(student.getRoom().getRoom_id());
                }

                return dto;
            }).toList();

            return new ServiceResponse(true, studentDTOs, null);

        } catch (Exception e) {
            return new ServiceResponse(false, "Sorry. Can't Access", null);
        }
    }

}
