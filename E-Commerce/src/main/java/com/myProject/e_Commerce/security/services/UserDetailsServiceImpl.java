package com.myProject.e_Commerce.security.services;

import com.myProject.e_Commerce.exception.ResourceNotFoundException;
import com.myProject.e_Commerce.exception.UserNotFoundException;
import com.myProject.e_Commerce.model.User;
import com.myProject.e_Commerce.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
@Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(()->new UserNotFoundException("User not found "));

        return UserDetailsImpl.build(user);
    }
}
