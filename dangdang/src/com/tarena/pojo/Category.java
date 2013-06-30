package com.tarena.pojo;

import java.util.List;
import java.util.Set;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields


	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer turn;
	private String enName;
	private String name;
	private String description;
	private Integer parentId;
	//增加属性类别 ，找到子类别 . 找到id为1的主类别后，自动该类别下的关联子类别
	private List<Category> subCats;
	//增加属性，用来存储该类别下product的数量.采用自查询映射
	private int pnum;
	//追加属性,用于存储相关的Product信息
	private Set<Product> pros;

	
	// Constructors
	/** default constructor */
	public Category() {
	}

	/** minimal constructor */
	public Category(Integer turn, String enName, String name) {
		this.turn = turn;
		this.enName = enName;
		this.name = name;
	}

	/** full constructor */
	public Category(Integer turn, String enName, String name,
			String description, Integer parentId) {
		this.turn = turn;
		this.enName = enName;
		this.name = name;
		this.description = description;
		this.parentId = parentId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTurn() {
		return this.turn;
	}

	public void setTurn(Integer turn) {
		this.turn = turn;
	}

	public String getEnName() {
		return this.enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public List<Category> getSubCats() {
		return subCats;
	}

	public Set<Product> getPros() {
		return pros;
	}

	public void setPros(Set<Product> pros) {
		this.pros = pros;
	}

	public void setSubCats(List<Category> subCats) {
		this.subCats = subCats;
	}
}