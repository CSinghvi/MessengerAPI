package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.model.Profile;

public class ProfileService {

private Map<String,Profile> profiles=DatabaseClass.getProfiles();
	
	public ProfileService(){
		profiles.put("koushik",new Profile(1l,"Koushik","Koushik","Kothagal"));
		
	}

	public List<Profile> getAllProfiles()
	{
	//***CODE AT THE STARTING OF THE PROGRAM, USED HARD CODED VALUE*** 
//		Message m1=new Message(1l,"Hello World!!","Koushik");
//		Message m2=new Message(2l,"Hello jersey!!","Chirayu");
//		List<Message> list=new ArrayList<>();
//		list.add(m1);
//		list.add(m2);
//		return list;
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile)
	{
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(),profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile)
	{
		if(profile.getProfileName().isEmpty())
		{
			return null;
		}
		profiles.put(profile.getProfileName(),profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName)
	{
		return profiles.remove(profileName);
	}
	
}
