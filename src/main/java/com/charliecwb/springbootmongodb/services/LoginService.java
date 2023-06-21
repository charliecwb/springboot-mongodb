package com.charliecwb.springbootmongodb.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.charliecwb.springbootmongodb.entities.UserEntity;
import com.charliecwb.springbootmongodb.models.LoginDTO;
import com.charliecwb.springbootmongodb.services.exception.InvalidLoginPasswordException;
import com.charliecwb.springbootmongodb.services.exception.InvalidTwoFACodeException;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.verify.CheckResponse;
import com.nexmo.client.verify.VerifyClient;
import com.nexmo.client.verify.VerifyRequest;
import com.nexmo.client.verify.VerifyResponse;
import com.nexmo.client.verify.VerifyStatus;

@Service
public class LoginService {
	private static NexmoService nexmoClient;
	@Autowired
	private UserService userService;
	@Autowired 
	private PasswordEncoder passEncoder;
	private static Map<String, String> userTwoFA = new HashMap<>();	
	private static VerifyClient verifyClient;
	
	public LoginService() {
		nexmoClient = new NexmoService();
		verifyClient = nexmoClient.nexmoBuilder().getVerifyClient();
	}
	
	private Boolean checkTwoFA(String code, String requestId) {				
		CheckResponse checkResponse;
		try {
			checkResponse = verifyClient.check(requestId, code);
			return checkResponse.getStatus() == VerifyStatus.OK;			
		} catch (IOException | NexmoClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String requestTwoFA(String name, String phone) {			
		VerifyRequest request = new VerifyRequest(phone, name);
		request.setLength(4);
		VerifyResponse verifyResponse;
		try {
			verifyResponse = verifyClient.verify(request);
			return verifyResponse.getRequestId();			
		} catch (IOException | NexmoClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public LoginDTO validate(String username) {
		UserEntity user = userService.findByUserName(username);
		if (user.getLogin().getTwoFA()) {
			userTwoFA.put(user.getLogin().getUserName(), requestTwoFA(user.getName(), user.getPhone()));
			return new LoginDTO(false, true);
		}		
		return new LoginDTO(true, false);
	}
	
	public Boolean isValidUser(String username, String password, String code) {
		Boolean isTwoFAOk = true;
		Boolean isPasswordOk = false;
		UserEntity user = userService.findByUserName(username);
		if (user.getLogin().getTwoFA()) {
			if (userTwoFA.containsKey(user.getLogin().getUserName())) {
				try {
					isTwoFAOk = checkTwoFA(code, userTwoFA.get(user.getLogin().getUserName()));
				}
				finally {
					userTwoFA.remove(user.getLogin().getUserName());
					
					if (!isTwoFAOk) {
						throw new InvalidTwoFACodeException(code);
					}
					
				}
			} else {
				isTwoFAOk = false;
			}
		}
		isPasswordOk = passEncoder.matches(password, user.getLogin().getPassword());
		
		if (!isPasswordOk) {
			throw new InvalidLoginPasswordException(password);
		}
		
		return isTwoFAOk && isPasswordOk;	
	}
}
