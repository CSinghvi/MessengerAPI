package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.model.Comment;
import org.koushik.javabrains.messenger.model.ErrorMessage;
import org.koushik.javabrains.messenger.model.Message;

/**
 * This class is for commsents
 * @author M1035998
 *
 */
public class CommentService {

	private Map<Long,Message> messages=DatabaseClass.getMessages();
	
		
	
	/**
	 * This function is used for getting the list of all Comments
	 * @return list of Comments 
	 */
	public List<Comment> getAllComments(long messageId)
	{
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	
	/**
	 * To get a particular comment
	 * @param commentid,messageId
	 * @return comment
	 * In the later tutorial we modified the code to handle the exception using 
	 * Web Application Exception
	 * jersey knows some exceptions such as Web Application Exception
	 *  which does not requires exceptionMapper
	 */
	public Comment getComment(long messageId,long commentId){
		
		ErrorMessage errorMessage=new ErrorMessage("Not Found",
				404,"www.google.com");
		Response response= Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		
		Message message=messages.get(messageId);
		if(message==null){
			//throw new WebApplicationException(Status.NOT_FOUND);
			throw new WebApplicationException(response);
		}
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		Comment comment=comments.get(commentId);
		if(comment==null){
			//throw new WebApplicationException(Status.NOT_FOUND);
			throw new WebApplicationException(response);
		}
		return comment;
	}
	
	/**
	 * FUNCTION FOR CREATING the comment
	 * @param comment,messageId
	 * @return comment
	 */
	public Comment addComment(long messageId,Comment comment)
	{
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(),comment);
		return comment;//dispalying the recently added message
	}
	
	/**
	 * Function for updating the comment
	 * @param comment,messageId
	 * @return updated comment
	 */
	public Comment updateComment(long messageId,Comment comment)
	{
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		if(comment.getId()<=0)//check if the message is null
		{
			return null;
		}
		//if not then replace old message with updated one
		comments.put(comment.getId(),comment);
		return comment;//returns updated message
	}
	
	/**
	 * Function to delete particular comment
	 * @param commentid,messageId
	 * @return nothing as comment is deleted
	 */
	public Comment removeComment(long messageId,long commentId)
	{
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
	
}
