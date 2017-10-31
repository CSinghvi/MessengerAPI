package org.koushik.javabrains.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.model.Profile;

/**
 * Mock class for fetching or storing mock data
 * @author M1035998
 *
 */
public class DatabaseClass {

	/**
	 * A Map which contain messge information as value and is mapped to message id as key
	 */
	private static Map<Long,Message> messages=new HashMap<>();
	private static Map<String,Profile> profiles=new HashMap<>();
	/**
	 * @return the messages
	 */
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	/**
	 * @param messages the messages to set
	 */
	public static void setMessages(Map<Long, Message> messages) {
		DatabaseClass.messages = messages;
	}
	/**
	 * @return the profiles
	 */
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	/**
	 * @param profiles the profiles to set
	 */
	public static void setProfiles(Map<String, Profile> profiles) {
		DatabaseClass.profiles = profiles;
	}

	
}
