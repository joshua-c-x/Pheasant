package components;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ecs.Components;
import eng.FileManager;

public class Controller extends Components
{	
	public static final long Up    = 1 << 0, 
							 Down  = 1 << 1, 
							 Left  = 1 << 2, 
							 Right = 1 << 3;
	public Long Facing;
	
	public Controller() 
	{
		super(Components.Controller, "controller");
	}

	@Override
	public JsonNode ToJsonNode() 
	{
		ObjectNode node = FileManager.ObjMapper.createObjectNode();
		
		node.put("componentID", ComponentID());
		node.put("componentName", ComponentName());
		node.put("facing", Facing);
		
		return (JsonNode)node;
	}

	@Override
	public void FromJsonNode(JsonNode jsonNode) 
	{
		Facing = jsonNode.path("facing").asLong();
	}
}
