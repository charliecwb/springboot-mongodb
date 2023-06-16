package com.charliecwb.springbootmongodb.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Boolean canLogin;
	private Boolean needTwoFA;
	
	public LoginDTO() {}

	public LoginDTO(Boolean canLogin, Boolean needTwoFA) {
		this.canLogin = canLogin;
		this.needTwoFA = needTwoFA;
	}

	public Boolean getCanLogin() {
		return canLogin;
	}

	public void setCanLogin(Boolean canLogin) {
		this.canLogin = canLogin;
	}

	public Boolean getNeedTwoFA() {
		return needTwoFA;
	}

	public void setNeedTwoFA(Boolean needTwoFA) {
		this.needTwoFA = needTwoFA;
	}
	

}
