package com.tarena.pojo;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userId;
	private Integer status;
	private long orderTime;
	private String orderDesc;
	private double totalPrice;
	private String receiveName;
	private String fullAddress;
	private String postalCode;
	private String mobile;
	private String phone;

	// Constructors

	/** default constructor */
	public Order() {
	}

	/** minimal constructor */
	public Order(Integer userId, Integer status, long orderTime,
			double totalPrice) {
		this.userId = userId;
		this.status = status;
		this.orderTime = orderTime;
		this.totalPrice = totalPrice;
	}

	/** full constructor */
	public Order(Integer userId, Integer status, long orderTime,
			String orderDesc, double totalPrice, String receiveName,
			String fullAddress, String postalCode, String mobile, String phone) {
		this.userId = userId;
		this.status = status;
		this.orderTime = orderTime;
		this.orderDesc = orderDesc;
		this.totalPrice = totalPrice;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public long getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderDesc() {
		return this.orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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