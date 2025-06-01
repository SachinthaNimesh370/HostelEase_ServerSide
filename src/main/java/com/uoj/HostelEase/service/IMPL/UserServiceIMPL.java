package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.dto.UserLoginRequestDTO;
import com.uoj.HostelEase.dto.UserRegRequestDTO;
import com.uoj.HostelEase.entity.UserEntity;
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
        if(isEnablePerson(userRegRequestDTO.getName())){
            return new ServiceResponse(false, "User already registered !");
        }

        try {
            UserEntity userEntity=modelMapper.map(userRegRequestDTO,UserEntity.class);
            userEntity.setPassword(passwordEncoder.encode(userRegRequestDTO.getPassword()));
            userRepository.save(userEntity);
            return new ServiceResponse(true, "User registered successfully");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ServiceResponse(false, "Registration Failed");
        }

    }

    @Override
    public ServiceResponse signIn(UserLoginRequestDTO userLoginRequestDTO) {
        if(isEnablePerson(userLoginRequestDTO.getUserName())){
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userLoginRequestDTO.getUserName(),userLoginRequestDTO.getPassword()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ServiceResponse(false,"Login failed. Please check your password.");
            }
            // After checking Valid user issue the key
            Map<String,String> map =clams(userLoginRequestDTO.getUserName());
            return new ServiceResponse(true,jwtService.jwtToken(userLoginRequestDTO.getUserName(),map));
        }
        else{
            return new ServiceResponse(false,"Login failed. No registered user found with the provided information.");
        }
    }
    private Map<String,String> clams(String userName){
        Map<String,String> map =new HashMap<>();
        map.put("role",userRepository.findRoleByName(userName));
        return map;
    }

    @Override
    public boolean isEnablePerson(String userName) {
        return userRepository.existsByName(userName);
    }
}
