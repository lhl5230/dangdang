package com.tarena.pojo;

/**
 * ReceiveAddress entity. @author MyEclipse Persistence Tools
 */

public class ReceiveAddress implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userId;
	private String receiveName;
	private String fullAddress;
	private String postalCode;
	private String mobile;
	private String phone;

	// Constructors

	/** default constructor */
	public ReceiveAddress() {
	}

	/** minimal constructor */
	public ReceiveAddress(Integer userId, String receiveName,
			String fullAddress, String postalCode) {
		this.userId = userId;
		this.receiveName = receiveName;
		this.fullAddress = fullAddress;
		this.postalCode = postalCode;
	}

	/** full constructor */
	public ReceiveAddress(Integer userId, String receiveName,
			String fullAddress, String postalCode, String mobile, String phone) {
		this.userId = userId;
		this.receiveName = receiveName;
		this.fullAddress = fullAddress;
		this.postalCode = postalCode;
		this.mobile = mobile;
		this.phone = phone;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getReceiveName() {
		return this.receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getFullAddress() {
		return this.fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}