package components;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ecs.Components;
import eng.Engine;
import eng.FileManager;
import processing.data.JSONObject;

public class Sprite extends Components
{	
	public int SpriteID;
	public int Index;
	public float SuperDuration;
	public boolean OverrideDuration;
	
	public Sprite() 
	{
		super(Components.Sprite, "sprite");
	}
	
	@Override
	public JsonNode ToJsonNode() 
	{
		ObjectNode node = FileManager.ObjMapper.createObjectNode();
		
		node.put("componentID", ComponentID());
		node.put("componentName", ComponentName());
		node.put("spriteID", SpriteID);

		return (JsonNode)node;
	}

	@Override
	public void FromJsonNode(JsonNode jsonNode) 
	{
		SpriteID = jsonNode.path("spriteID").asInt();
	}
}
