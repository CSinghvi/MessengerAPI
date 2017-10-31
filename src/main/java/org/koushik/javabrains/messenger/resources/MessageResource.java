package org.koushik.javabrains.messenger.resources;



import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.service.MessageService;


/**
 * Main class which is executed
 * @author M1035998
 *
 */
//this annotation acts as the address and it tells system that this
//class is to be executed
@Path("/messages")
public class MessageResource {

	/**
	 * Creating object of the service class
	 */
	MessageService messageService=new MessageService();
	
	
	/**
	 * This function makes calls to get all messages of service class
	 * It is also doing filtering and pagenation
	 * other func for filtering & pagenation cannot be made becz the api is mapped to this url only
	 * so we have to define methods in this function only
	 * @return list of messages
	 * the response is in the json format
	 */
	//This annotation is used for getting a resource
	//it is get request method of HTTP
	@GET
	//@Produces(MediaType.APPLICATION_XML) //for xml response
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})//for json response
	//@queryparam is mapping the query in browser to local variable say year
	public List<Message> getMessages(@QueryParam("year") int year,
										@QueryParam("start") int start,
										@QueryParam("size") int size)
	{
		if(year>0){
			return messageService.getAllMessagesForYear(year);
		}
		if(start>=0 && size>0){
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}
	
	//This annotation is used for generating a resource
	//it is post method of HTTP
	/**
	 * This function creates a new message
	 * @param message is input 
	 * @return the message which is added
	 * by calling the function of service class
	 */
	@POST
	//This annotation specifies that it accepts JSON format
	//from the browser
	@Consumes(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	//This annotation specifies that it produces or send 
	//data in JSON format to the browser
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	public Message addMessage(Message message){
		return messageService.addMessage(message);
		}
	
	//This annotation is used for updating a resource
	//it is put method of HTTP
	/**
	 * Function for updating or changing the message
	 * @param id is for identifying the message
	 * @param message(new) is passed as parameter
	 * @return updated message
	 */
	@PUT
	//its the address for accessing the particular metod
	//as the parameter is variable and not a constant
	//it is enclosed by {}
	@Path("/{messageId}")
	//This annotation specifies that it accepts JSON format
		//from the browser
	@Consumes(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	//This annotation specifies that it produces or send 
		//data in JSON format to the browser
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	//@pathparam is used to connect or parse the message id which we 
	//enter in the address to the local variable , in this case the local 
	//variable is id.
	public Message updateMessage(@PathParam("messageId") long id, Message message){
		//message with this id is get selected and is updated
		message.setId(id);
		return messageService.updateMessage(message);
		}
	
	//This annotation is used for deleting a resource
		//it is delete method of HTTP
	/**
	 * This function deletes a particular message
	 * @param id
	 */
	@DELETE
	//its the address for accessing the particular metod
		//as the parameter is variable and not a constant
		//it is enclosed by {}
	@Path("/{messageId}")
	//This annotation specifies that it produces or send 
			//data in JSON format to the browser
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	public void deleteMessage(@PathParam("messageId") long id){
		messageService.removeMessage(id);
		}
	
	//This annotation is used for getting a resource
		//it is get request method of HTTP
	/**Function is for getting a particular message
	 * @param id
	 * @return a paricular message
	 */
	@GET
	//its the address for accessing the particular method
			//as the parameter is variable and not a constant
			//it is enclosed by {}
	@Path("/{messageId}")
	//This annotation specifies that it produces or send 
	//data in JSON format to the browser
	//@Produces(MediaType.APPLICATION_XML)    //for xml response
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})		//for json response
		public Message getMessage(@PathParam("messageId") long id,@Context UriInfo uriInfo)
	{
		Message message= messageService.getMessage(id);
		//we are implementing HATEOS here which means we are also returning
		//links as response & message.addLink is created in Message.class
		message.addLink(getUriForSelf(uriInfo, message),"self");
		message.addLink(getUriForProfile(uriInfo, message),"profile");
		message.addLink(getUriForComments(uriInfo, message),"comments");
		return message;
	}

	/**This function is generating a URi which we will send as a response
	 * @param uriInfo
	 * @param message
	 * @return uri
	 * UriInfo gives access to URibasebuilder which give info of uri
	 * and using .path("") method we can append methods/classes in the uri
	 */
	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri= uriInfo.getBaseUriBuilder()
		//for comments as CommentResource is a subREsource we have to first give the main resource class		
				.path(MessageResource.class)
		//we are specifying Main resource class name and sub reource method name		
				.path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class)
		//the method name contains messageId,but we want its value so we are
		//using .resolveTemplate for getting messageId value
				.resolveTemplate("messageId",message.getId())
				.build()
				.toString();
		return uri;
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri= uriInfo.getBaseUriBuilder()//http://localhost:8080/messenger/webapi/
				.path(ProfileResource.class)//						                  /profiles	
				.path(message.getAuthor())//													/AuthorName
				.build()
				.toString();//As uri generated is in URI format we have to convert it to the string
		return uri;
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri= uriInfo.getBaseUriBuilder()
		.path(MessageResource.class)
		.path(Long.toString(message.getId()))
		.build()
		.toString();
		return uri;
	}
	
	/**
	 * This function is to identify that CommentResource is sub resource
	 * under MessageResource
	 * @return
	 */
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
}
