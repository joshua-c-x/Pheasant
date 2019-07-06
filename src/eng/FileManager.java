package eng;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import def.Application;
import usr.*;

public class FileManager
{
    public static ObjectMapper ObjMapper;
    public static ObjectWriter ObjWriter;
    
    public static java.util.Date Time;
    public static ArrayList<ObjectNode> Users;
    public static ArrayList<ObjectNode> Memories;
    
    private FileManager() { }

    public static void InitializeFileManager() 
    {
    	ObjMapper = new ObjectMapper();
    	ObjWriter = ObjMapper.writer();  
    	Time      = new java.util.Date();
    	Users     = new ArrayList<ObjectNode>();
    	Memories  = new ArrayList<ObjectNode>();
    	
    }
    
    public static void LoadMemories() 
    {
    	JsonNode memoriesRoot = null; 
    	try 
    	{
			memoriesRoot = ObjMapper.readTree(PathFinder.FILE_MEMORIES.toFile());		
			
			Memories.clear();
			
			if(memoriesRoot.isArray()) 
			{
				for(JsonNode memory : memoriesRoot) 
				{
					Memories.add((ObjectNode)memory);
				}
			}
		} 
    	catch (JsonParseException e) 
    	{
			e.printStackTrace();
		} 
    	catch (JsonMappingException e) 
    	{
			e.printStackTrace();
		} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
    }
    
    public static void LoadUsers() 
    {
    	JsonNode users = null; 
    	try 
    	{
			users = ObjMapper.readTree(PathFinder.FILE_USERS.toFile());
			Users.clear();
    		for(JsonNode user : users) 
    		{
    			Users.add((ObjectNode)user);
    		}
		} 
    	catch (JsonParseException e) 
    	{
			e.printStackTrace();
		} 
    	catch (JsonMappingException e) 
    	{
			e.printStackTrace();
		} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
    }
    
    public static void AddNewUserToUsersFile(String userName, long timeCreated)
    {    	
    	ObjectNode user = ObjMapper.createObjectNode();
    	
    	user.put("userName", userName.toLowerCase());
    	user.put("timeCreated", timeCreated);
    	user.put("fileUUID", UUID.randomUUID().toString());
    	
    	try
    	{
    		 ObjectNode memory = (ObjectNode)ObjMapper.readTree(PathFinder.FILE_MEMORY_DEFAULT.toFile());
			/// Crucial! 
			memory.put("fileUUID", user.path("fileUUID").asText());	

			WriteUserToMemory(user, memory);
		} 
    	catch (JsonParseException e) 
    	{
			e.printStackTrace();
		} 
    	catch (JsonMappingException e) 
    	{
			e.printStackTrace();
		} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
    }

    public static void SaveMemories() 
    {
    	ArrayNode memories = ObjMapper.createArrayNode();
    	
    	for(JsonNode memory : Memories) 
    	{
    		memories.add(memory);
    	}    	
    	
    	File memoriesFile = PathFinder.FILE_MEMORIES.toFile();
    	
    	if(ObjWriter == null)
    	{
    		ObjWriter = ObjMapper.writer();  
    	}   	
    	try 
    	{
    		ObjWriter.withDefaultPrettyPrinter().writeValue(memoriesFile, memories);
		} 
    	catch (JsonGenerationException e) 
    	{
			e.printStackTrace();
		} 
    	catch (JsonMappingException e) 
    	{
			e.printStackTrace();
		} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
    }
    
    public static void SaveUsers() 
    {
    	ArrayNode users = FileManager.ObjMapper.createArrayNode();
    	
    	for(int i = 0; i < Users.size(); i += 1) 
    	{
    		users.add(Users.get(i));   		
    	}
    	
    	File usersFile = PathFinder.FILE_USERS.toFile();
    	 	
    	try 
    	{
    		ObjWriter.withDefaultPrettyPrinter().writeValue(usersFile, users);
		} 
    	catch (JsonGenerationException e) 
    	{
			e.printStackTrace();
		} 
    	catch (JsonMappingException e) 
    	{
			e.printStackTrace();
		} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
    }
    
    private static void WriteUserToMemory(ObjectNode user, ObjectNode memory) 
    {
    	LoadUsers();
    	LoadMemories();
    	
    	Users.add(user);
    	Memories.add(memory);
    	
    	SaveUsers();
    	SaveMemories();    
    }
}