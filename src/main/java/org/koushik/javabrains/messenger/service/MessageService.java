package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.exception.DataNotFoundException;
import org.koushik.javabrains.messenger.model.Message;

/**
 * This class contains the business logic
 * @author M1035998
 *
 */
public class MessageService {
	
	/**
	 * Creating object of map class and retriving messages stored
	 * in the map
	 */
	private Map<Long,Message> messages=DatabaseClass.getMessages();
	
	/**
	 * Entering some inital values of message in the map
	 */
	public MessageService(){
		messages.put(1l,new Message(1,"Hello World","Koushik"));
		messages.put(2l,new Message(2,"Hello jersey","chirayu"));
	}

	/**
	 * This function is used for getting the list of all messages
	 * @return list of messages 
	 */
	public List<Message> getAllMessages()
	{
	//***CODE AT THE STARTING OF THE PROGRAM, USED HARD CODED VALUE*** 
//		Message m1=new Message(1l,"Hello World!!","Koushik");
//		Message m2=new Message(2l,"Hello jersey!!","Chirayu");
//		List<Message> list=new ArrayList<>();
//		list.add(m1);
//		list.add(m2);
//		return list;
		return new ArrayList<Message>(messages.values());
	}
	
	/**Function for filtering messages for a particular year
	 * @param year
	 * @return list of messages
	 */
	public List<Message> getAllMessagesForYear(int year)
	{
		List<Message> messagesForYear=new ArrayList<>();
	//calender instance is created for comparing to the year of messages
		Calendar cal=Calendar.getInstance();
		for(Message message:messages.values()){
			//message date is set to calender
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year)
			{
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	/**
	 * FUNCTION FOR GETTING PARTICULAR SET OF MESSAGES
	 * @param start
	 * @param size
	 * @return
	 */
	public List<Message> getAllMessagesPaginated(int start,int size){
		ArrayList<Message> list=new ArrayList<Message>(messages.values());
		if(start+size>list.size()) return new ArrayList<Message>();
		return list.subList(start, start+size);
	}
	
	/**
	 * To get a particular message
	 * @param id
	 * @return message
	 */
	public Message getMessage(long id){
		Message message= messages.get(id);
	if(message==null)
	{
		throw new DataNotFoundException("message with id "+id+" not found");
	}
	return message;	
	}
	
	/**
	 * FUNCTION FOR CREATING NE MESSAGE
	 * @param message
	 * @return message
	 */
	public Message addMessage(Message message)
	{
		message.setId(messages.size()+1);//incrementing value of id by 1
		messages.put(message.getId(),message);//inserting message into map
		return message;//dispalying the recently added message
	}
	
	/**
	 * Function for updating the message
	 * @param message
	 * @return updated message
	 */
	public Message updateMessage(Message message)
	{
		if(message.getId()<=0)//check if the message is null
		{
			return null;
		}
		//if not then replace old message with updated one
		messages.put(message.getId(),message);
		return message;//returns updated message
	}
	
	/**
	 * Function to delete particular message
	 * @param id
	 * @return nothing as message is deleted
	 */
	public Message removeMessage(long id)
	{
		return messages.remove(id);
	}
	
}
