package com.uoj.HostelEase.service;

import com.uoj.HostelEase.dto.UserLoginRequestDTO;
import com.uoj.HostelEase.dto.UserRegRequestDTO;
import com.uoj.HostelEase.utill.ServiceResponse;

public interface UserService {
    ServiceResponse signUp(UserRegRequestDTO userRegRequestDTO);
    ServiceResponse signIn(UserLoginRequestDTO userLoginRequestDTO);

    boolean isEnablePerson(String userName);


}
