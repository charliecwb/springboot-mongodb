package com.charliecwb.springbootmongodb.models;

public class LoginDTO {	
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
