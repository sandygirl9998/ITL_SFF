package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lti.dto.Login;
import com.lti.dto.OtpDto;
import com.lti.dto.Status;
import com.lti.service.UserService;

@Controller
@CrossOrigin
public class PasswordResetController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addOtp")
	public @ResponseBody Status addOtp(@RequestBody OtpDto otpDto) {
		return userService.addOtpByUserId(otpDto.getEmail());
	}
	
	
	@PostMapping("/verifyOtp")
	public @ResponseBody Status verifyOtp(@RequestBody OtpDto otpDto) {
		return userService.verifyOtp(otpDto.getEmail(), otpDto.getOtp());
	}
	
	@PutMapping("/changePassword")
	public @ResponseBody Status updateUserPasswordByEmailId(@RequestBody Login login) {
		return userService.updateUserPasswordByEmail(login.getUsername(), login.getPassword());
	}
	
	 
}
