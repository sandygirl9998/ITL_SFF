package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.User;
import com.lti.exception.UserServiceException;
import com.lti.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/login")
	public @ResponseBody LoginStatus login(@RequestBody Login login) {
		try {
			User user = userService.login(login);
			LoginStatus status = new LoginStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Login Successful!");
			status.setCustomerId(user.getUserId());
			status.setCustomerName(user.getName());
			return status;
		}
		catch(UserServiceException e) {
			LoginStatus status = new LoginStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;			
		}
	}
	
	//for farmer and bidder approval
			@PostMapping(value = "/userstatus")
			public @ResponseBody Status updateUserStatus(@RequestParam("userStatus") String userStatus, @RequestParam("userId") int userId) {
				userService.updateStatus(userId, userStatus);
				Status status = new Status();
				status.setStatus(StatusType.SUCCESS);
				status.setMessage("Approval Done");
				return status;
			}

}
