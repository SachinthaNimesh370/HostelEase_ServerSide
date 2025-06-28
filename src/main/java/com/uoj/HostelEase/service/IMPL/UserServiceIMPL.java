package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.dto.UserApproveDTO;
import com.uoj.HostelEase.dto.UserLoginRequestDTO;
import com.uoj.HostelEase.dto.UserRegRequestDTO;
import com.uoj.HostelEase.entity.*;
import com.uoj.HostelEase.repo.*;
import com.uoj.HostelEase.service.UserService;
import com.uoj.HostelEase.utill.ServiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceIMPL implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTServiceIMPL jwtService;
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final WardenRepository wardenRepository;
    private final SecurityRepository securityRepository;


    public UserServiceIMPL(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTServiceIMPL jwtService, AdminRepository adminRepository, StudentRepository studentRepository, WardenRepository wardenRepository, SecurityRepository securityRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.wardenRepository = wardenRepository;
        this.securityRepository = securityRepository;
    }

    @Override
    public ServiceResponse signUp(UserRegRequestDTO userRegRequestDTO) {
        if(isEnablePerson(userRegRequestDTO.getRegNo())){
            return new ServiceResponse(false, "User already registered !",null);
        }

        try {
            UserEntity userEntity=modelMapper.map(userRegRequestDTO,UserEntity.class);
            userEntity.setPassword(passwordEncoder.encode(userRegRequestDTO.getPassword()));
            userEntity.setState(false);
            userRepository.save(userEntity);
            return new ServiceResponse(true, "User registered successfully",null);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ServiceResponse(false, "Registration Failed",null);
        }
    }

    @Override
    public ServiceResponse signIn(UserLoginRequestDTO userLoginRequestDTO) {
        if(isEnablePerson(userLoginRequestDTO.getRegNo())){
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userLoginRequestDTO.getRegNo(),userLoginRequestDTO.getPassword()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ServiceResponse(false,"Login failed. Please check your password.",null);
            }

            if(userRepository.findStateByRegNo(userLoginRequestDTO.getRegNo())){
                // After checking Valid user issue the key
                Map<String,String> map =clams(userLoginRequestDTO.getRegNo());
                System.out.println(map);
                return new ServiceResponse(true,jwtService.jwtToken(userLoginRequestDTO.getRegNo(),map),map);
            }else {
                return new ServiceResponse(false,"Login failed. Please Waiting For Approve By Admin",null);
            }
        }
        else{
            return new ServiceResponse(false,"Login failed. No registered user found with the provided information.",null);
        }
    }
    private Map<String,String> clams(String regNo){
        Map<String,String> map =new HashMap<>();
        map.put("role",userRepository.findRoleByRegNo(regNo));
        return map;
    }

    @Override
    public boolean isEnablePerson(String regNo) {
        return userRepository.existsByRegNo(regNo);
    }

    @Override
    public ServiceResponse userUpdate(UserApproveDTO userApproveDTO) {
        if(isEnablePerson(userApproveDTO.getRegNo())){
            try{
                UserEntity userEntity=modelMapper.map(userApproveDTO,UserEntity.class);
                userEntity.setPassword(getUserDetails(userApproveDTO.getRegNo()).getPassword());
                userRepository.save(userEntity);
                if(userEntity.isState()){
                    if(userEntity.getRole().equals("Student")){
                        student(userEntity.getRegNo(),userApproveDTO.getAdmin_id());
                    } else if (userEntity.getRole().equals("Admin")) {
                        admin(userEntity.getRegNo());
                    } else if (userEntity.getRole().equals("Warden")) {
                        warden(userEntity.getRegNo(),userApproveDTO.getAdmin_id());
                    }else if (userEntity.getRole().equals("Security")) {
                        security(userEntity.getRegNo());
                    }
                }
                else {
                    return new ServiceResponse(false, "Process Has Something Wrong.Please Try Again",null);
                }
                return new ServiceResponse(true, "User Updated successfully",null);

            }catch(Exception e){
                System.out.println("Reason for Update fail " +e.getMessage());
                return new ServiceResponse(false, "User Update fail",null);
            }
        }
        return null;
    }
    private UserEntity getUserDetails(String regNo){
        Optional<UserEntity> userDetails=userRepository.findByRegNo(regNo);
        return userDetails.get();
    }

    @Override
    public ServiceResponse getAllUser() {

        List<UserEntity> users = new ArrayList<>();
        try{
            users.addAll(userRepository.findAll());
            if(users.size()>0){
                return new ServiceResponse(true, users,null);
            }else{
                return new ServiceResponse(false, "No Any users",null);
            }

        } catch (Exception e) {
            return new ServiceResponse(false, "Sorry.Can't Access",null);
        }

    }

    @Override
    public ServiceResponse getAllWarden() {
        List<UserEntity> users = new ArrayList<>();
        try{
            users.addAll(userRepository.findByRoleLike("Warden"));
            if(users.size()>0){
                return new ServiceResponse(true, users,null);
            }else{
                return new ServiceResponse(false, "No Any Warden",null);
            }

        } catch (Exception e) {
            return new ServiceResponse(false, "Sorry.Can't Access",null);
        }
    }

    @Override
    public ServiceResponse getAllAdmin() {
        List<UserEntity> users = new ArrayList<>();
        try{
            users.addAll(userRepository.findByRoleLike("Admin"));
            if(users.size()>0){
                return new ServiceResponse(true, users,null);
            }else{
                return new ServiceResponse(false, "No Any Admin",null);
            }

        } catch (Exception e) {
            return new ServiceResponse(false, "Sorry.Can't Access",null);
        }
    }

    @Override
    public ServiceResponse getAllStudent() {
        List<UserEntity> users = new ArrayList<>();
        try{
            users.addAll(userRepository.findByRoleLike("Student"));
            if(users.size()>0){
                return new ServiceResponse(true, users,null);
            }else{
                return new ServiceResponse(false, "No Any Student",null);
            }

        } catch (Exception e) {
            return new ServiceResponse(false, "Sorry.Can't Access",null);
        }
    }

    @Override
    public ServiceResponse getAllSecurity() {
        List<UserEntity> users = new ArrayList<>();
        try{
            users.addAll(userRepository.findByRoleLike("Security"));
            if(users.size()>0){
                return new ServiceResponse(true, users,null);
            }else{
                return new ServiceResponse(false, "No Any Security",null);
            }

        } catch (Exception e) {
            return new ServiceResponse(false, "Sorry.Can't Access",null);
        }
    }

    @Override
    public ServiceResponse deleteUser(String id) {
        if(isEnablePerson(id)){
            try {
                userRepository.deleteById(id);
                return new ServiceResponse(true, "User Deleted successfully",null);
            }catch (Exception e){
                System.out.println(e.getMessage());
                return new ServiceResponse(false, "User Delete Failed",null);
            }
        }else{
            return new ServiceResponse(false, "Can't Find User Reg No",null);
        }
    }

    private void student(String regNo,String admin_id){
        try{
            StudentEntity student = new StudentEntity();
            AdminEntity admin = new AdminEntity();
            admin.setAdmin_id(admin_id);
            student.setStudent_id(regNo);
            student.setAdmin(admin);
            studentRepository.save(student);
            System.out.println("student added successfully to Student Entity");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void admin(String regNo){
        try{
            AdminEntity admin = new AdminEntity();
            admin.setAdmin_id(regNo);
            adminRepository.save(admin);
            System.out.println("admin added successfully to Admin Entity");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void warden(String regNo,String admin_id){
        try{
            WardenEntity warden = new WardenEntity();
            AdminEntity admin = new AdminEntity();
            admin.setAdmin_id(admin_id);
            warden.setWarden_id(regNo);
            warden.setAdmin(admin);
            wardenRepository.save(warden);
            System.out.println("Warden added successfully to Warden Entity");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void security(String regNo){
        try{
            SecurityEntity security = new SecurityEntity();
            security.setSecurity_id(regNo);
            securityRepository.save(security);
            System.out.println("Admin added successfully to Security Entity");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
