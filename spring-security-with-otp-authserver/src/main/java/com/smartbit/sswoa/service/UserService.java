package com.smartbit.sswoa.service;

import com.smartbit.sswoa.model.User;
import com.smartbit.sswoa.model.UserOtp;
import com.smartbit.sswoa.repository.UserOTPRepository;
import com.smartbit.sswoa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserOTPRepository userOTPRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void authUser(User user) throws NoSuchAlgorithmException {

        Optional<User> optionalUser= userRepository.findByUsername(user.getUsername());
        if(optionalUser.isPresent()){
            if(passwordEncoder.matches(user.getPassword(),optionalUser.get().getPassword())){
                sendOtp(user);
            }
        }
    }

    public Boolean checkOtp(UserOtp userOtp){
        Optional<UserOtp> userOtpOptional= userOTPRepository.findByUsername(userOtp.getUsername());
        if(userOtpOptional.isPresent()){
            return userOtpOptional.get().getCode().equalsIgnoreCase(userOtp.getCode());
        }
        else{
            return false;
        }
    }

    private void sendOtp(User user) throws NoSuchAlgorithmException {
       Optional<UserOtp>  userOtp=userOTPRepository.findByUsername(user.getUsername());
      if( userOtp.isPresent()){
          UserOtp savedUserOtp=  userOtp.get();
          savedUserOtp.setCode(generateCode());
          userOTPRepository.save(savedUserOtp);
      }
      else{
          UserOtp  newUserOtp= new UserOtp(user.getUsername(),generateCode());
          userOTPRepository.save(newUserOtp);
      }

    }

    private String generateCode() throws NoSuchAlgorithmException {
        SecureRandom secureRandom=  SecureRandom.getInstanceStrong();
        int code=secureRandom.nextInt(9000)+1000;
        return String.valueOf(code);
    }
}
