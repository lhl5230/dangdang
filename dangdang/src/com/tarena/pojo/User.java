package com.tarena.pojo;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email;
	private String nickname;
	private String password;
	private Integer userIntegral;
	private String isEmailVerify;
	private String emailVerifyCode;
	private long lastLoginTime;
	private String lastLoginIp;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String email, String password, Integer userIntegral) {
		this.email = email;
		this.password = password;
		this.userIntegral = userIntegral;
	}

	/** full constructor */
	public User(Integer id,String email, String nickname, String password,
			Integer userIntegral, String isEmailVerify, String emailVerifyCode,
			long lastLoginTime, String lastLoginIp) {
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.userIntegral = userIntegral;
		this.isEmailVerify = isEmailVerify;
		this.emailVerifyCode = emailVerifyCode;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserIntegral() {
		return this.userIntegral;
	}

	public void setUserIntegral(Integer userIntegral) {
		this.userIntegral = userIntegral;
	}

	public String getIsEmailVerify() {
		return this.isEmailVerify;
	}

	public void setIsEmailVerify(String isEmailVerify) {
		this.isEmailVerify = isEmailVerify;
	}

	public String getEmailVerifyCode() {
		return this.emailVerifyCode;
	}

	public void setEmailVerifyCode(String emailVerifyCode) {
		this.emailVerifyCode = emailVerifyCode;
	}

	public long getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

}