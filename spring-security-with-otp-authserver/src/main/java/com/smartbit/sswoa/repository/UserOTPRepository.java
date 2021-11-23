package com.smartbit.sswoa.repository;

import com.smartbit.sswoa.model.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserOTPRepository  extends JpaRepository<UserOtp,String> {
  Optional<UserOtp> findByUsername(String username);

}
