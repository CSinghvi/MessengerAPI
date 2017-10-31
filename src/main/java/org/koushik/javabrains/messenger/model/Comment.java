package org.koushik.javabrains.messenger.model;

import java.util.Date;

/**
 * POJO class for comments
 * @author M1035998
 *
 */
public class Comment {

	private long id;
	private String message;
	private Date created;
	private String author;
	/**
	 * 
	 */
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param message
	 * @param author
	 */
	public Comment(long id, String message, String author) {
		super();
		this.id = id;
		this.message = message;
		this.author = author;
		this.created=new Date();
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
