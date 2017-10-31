package org.koushik.javabrains.messenger.model;

/**
 * Class for storing the links to messenger attributes like messages etc.
 * @author M1035998
 *
 */
public class Link {
private String link;
private String rel;
/**
 * 
 */
public Link() {
	super();
	// TODO Auto-generated constructor stub
}
/**
 * @param link
 * @param rel
 */
public Link(String link, String rel) {
	super();
	this.link = link;
	this.rel = rel;
}
/**
 * @return the link
 */
public String getLink() {
	return link;
}
/**
 * @param link the link to set
 */
public void setLink(String link) {
	this.link = link;
}
/**
 * @return the rel
 */
public String getRel() {
	return rel;
}
/**
 * @param rel the rel to set
 */
public void setRel(String rel) {
	this.rel = rel;
}


}
