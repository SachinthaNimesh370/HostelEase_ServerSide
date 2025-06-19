package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.dto.UserLoginRequestDTO;
import com.uoj.HostelEase.dto.UserRegRequestDTO;
import com.uoj.HostelEase.entity.UserEntity;
import com.uoj.HostelEase.repo.UserRepository;
import com.uoj.HostelEase.service.UserService;
import com.uoj.HostelEase.utill.ServiceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceIMPL implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTServiceIMPL jwtService;

    public UserServiceIMPL(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTServiceIMPL jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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

             System.out.println(userRepository.findStateByRegNo(userLoginRequestDTO.getRegNo()));

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
}
