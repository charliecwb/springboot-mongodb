package com.charliecwb.springbootmongodb.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charliecwb.springbootmongodb.domain.User;
import com.charliecwb.springbootmongodb.dto.LoginDTO;
import com.charliecwb.springbootmongodb.dto.UserDetailDTO;
import com.charliecwb.springbootmongodb.resources.util.Util;
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
	
	public LoginDTO validate(UserDetailDTO userDetail) {
		User user = userService.findByUserName(userDetail.getUserName());
		if (user.getLogin().getTwoFA()) {
			userTwoFA.put(user.getLogin().getUserName(), requestTwoFA(user.getName(), user.getPhone()));
			return new LoginDTO(false, true);
		}		
		return new LoginDTO(true, false);
	}
	
	public Boolean login(UserDetailDTO userDetail, String code) {
		Boolean isTwoFAOk = true;
		Boolean isPasswordOk = false;
		User user = userService.findByUserName(userDetail.getUserName());
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
		isPasswordOk = userDetail.getPassword().equals(Util.decryptPassword(user.getLogin().getPassword()));
		
		if (!isPasswordOk) {
			throw new InvalidLoginPasswordException(userDetail.getPassword());
		}
		
		return isTwoFAOk && isPasswordOk;		
	}
}
