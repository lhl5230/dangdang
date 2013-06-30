package com.tarena.pojo;

/**
 * Book entity. @author MyEclipse Persistence Tools
 * 继承Product 来实现继承关联映射
 */

public class Book extends Product implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String author;
	private String publishing;
	private long publishTime;
	private String wordNumber;
	private String whichEdtion;
	private String totalPage;
	private Integer printTime;
	private String printNumber;
	private String isbn;
	private String authorSummary;
	private String catalogue;

	// Constructors

	/** default constructor */
	public Book() {
	}

	/** minimal constructor */
	public Book(String author, String publishing, long publishTime,
			String authorSummary, String catalogue) {
		this.author = author;
		this.publishing = publishing;
		this.publishTime = publishTime;
		this.authorSummary = authorSummary;
		this.catalogue = catalogue;
	}

	/** full constructor */
	public Book(String author, String publishing, long publishTime,
			String wordNumber, String whichEdtion, String totalPage,
			Integer printTime, String printNumber, String isbn,
			String authorSummary, String catalogue) {
		this.author = author;
		this.publishing = publishing;
		this.publishTime = publishTime;
		this.wordNumber = wordNumber;
		this.whichEdtion = whichEdtion;
		this.totalPage = totalPage;
		this.printTime = printTime;
		this.printNumber = printNumber;
		this.isbn = isbn;
		this.authorSummary = authorSummary;
		this.catalogue = catalogue;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishing() {
		return this.publishing;
	}

	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}

	public long getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}

	public String getWordNumber() {
		return this.wordNumber;
	}

	public void setWordNumber(String wordNumber) {
		this.wordNumber = wordNumber;
	}

	public String getWhichEdtion() {
		return this.whichEdtion;
	}

	public void setWhichEdtion(String whichEdtion) {
		this.whichEdtion = whichEdtion;
	}

	public String getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPrintTime() {
		return this.printTime;
	}

	public void setPrintTime(Integer printTime) {
		this.printTime = printTime;
	}

	public String getPrintNumber() {
		return this.printNumber;
	}

	public void setPrintNumber(String printNumber) {
		this.printNumber = printNumber;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthorSummary() {
		return this.authorSummary;
	}

	public void setAuthorSummary(String authorSummary) {
		this.authorSummary = authorSummary;
	}

	public String getCatalogue() {
		return this.catalogue;
	}

	public void setCatalogue(String catalogue) {
		this.catalogue = catalogue;
	}

}