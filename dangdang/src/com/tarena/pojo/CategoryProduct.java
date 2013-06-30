package com.tarena.pojo;

/**
 * CategoryProduct entity. @author MyEclipse Persistence Tools
 */

public class CategoryProduct implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer productId;
	private Integer catId;

	// Constructors

	/** default constructor */
	public CategoryProduct() {
	}

	/** full constructor */
	public CategoryProduct(Integer productId, Integer catId) {
		this.productId = productId;
		this.catId = catId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getCatId() {
		return this.catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

}