package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailServiceIMPL implements UserDetailsService {
    private final UserRepository userRepository;



    public MyUserDetailServiceIMPL(UserRepository userRepository) {

        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity =userRepository.findByName(username).orElse(null);
        if(userEntity != null){
            UserDetails user = User.builder()
                    .username(userEntity.getName())
                    .password(userEntity.getPassword())
                    .build();
            return user;
        }else {
            return null;
        }

    }




}
