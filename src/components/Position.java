package components;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ecs.*;
import eng.FileManager;

public class Position extends Components 
{
	public int TileX, TileY;
	
	public Position() 
	{
		super(Components.Position, "position");
	}
	
	@Override
	public JsonNode ToJsonNode() 
	{
		ObjectNode node = FileManager.ObjMapper.createObjectNode();
		
		node.put("componentID", ComponentID());
		node.put("componentName", ComponentName());
		node.put("tileX", TileX);
		node.put("tileY", TileY);
		
		return (JsonNode)node;
	}

	@Override
	public void FromJsonNode(JsonNode jsonNode) 
	{
		TileX = jsonNode.path("tileX").asInt();
		TileY = jsonNode.path("tileY").asInt();
	}
}
