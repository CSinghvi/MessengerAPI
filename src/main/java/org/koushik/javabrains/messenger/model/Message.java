package org.koushik.javabrains.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * POJO class for messages
 * @author M1035998
 *
 */
/*As we are passing the response in xml format , the browser will not 
understand as the data is not in xml format , so its need's to be converted
into XML format, jersey do it for us using @XmlRootElement*/ 
@XmlRootElement
public class Message {
	private long id;
	private String message;
	private Date created;
	private String author;
	private Map<Long,Comment> comments=new HashMap<>();
	private List<Link> links=new ArrayList<>();
	
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

	/**
	 * @param id
	 * @param message
	 * @param created
	 * @param author
	 */
	public Message(long id, String message,String author) {
		super();
		this.id = id;
		this.message = message;
		this.author = author;
		this.created = new Date();
	}
	/**
	 * @param id
	 * @param message
	 * @param author
	 */
//	public Message(long id, String message, String author) {
//		super();
//		this.id = id;
//		this.message = message;
//		this.author = author;
//	}
	/**
	 * 
	 */
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the comments
	 */
	@XmlTransient//By this comment section will not show when message API IS called
	public Map<Long, Comment> getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}
	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}
	/**
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	/**
	 * rhis method is used in message resource for adding link and its rel
	 * @param url
	 * @param rel
	 */
	public void addLink(String url,String rel){
		Link link=new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}
	
}
