package com.tarena.pojo;

/**
 * Item entity. @author MyEclipse Persistence Tools
 */

public class Item implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer orderId;
//	private Integer productId;//增加了多对一关联属性，这项是冗余的，多余的项
	private String productName;
	private double dangPrice;
	private Integer productNum;
	private double amount;
	// 追加属性，添加关联到product
	private Product pro;
	// Constructors

	/** default constructor */
	public Item() {
	}

	/** full constructor */
	public Item(Integer orderId, Integer productId, String productName,
			double dangPrice, Integer productNum, double amount) {
		this.orderId = orderId;
//		this.productId = productId;
		this.productName = productName;
		this.dangPrice = dangPrice;
		this.productNum = productNum;
		this.amount = amount;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

//	public Integer getProductId() {
//		return this.productId;
//	}
//
//	public void setProductId(Integer productId) {
//		this.productId = productId;
//	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getDangPrice() {
		return this.dangPrice;
	}

	public void setDangPrice(double dangPrice) {
		this.dangPrice = dangPrice;
	}

	public Integer getProductNum() {
		return this.productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Product getPro() {
		return pro;
	}

	public void setPro(Product pro) {
		this.pro = pro;
	}

}