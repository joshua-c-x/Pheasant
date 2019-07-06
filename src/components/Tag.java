package components;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ecs.Components;
import eng.FileManager;

public class Tag extends Components
{
	public String Type;
	
	public Tag() 
	{
		super(Components.Tag, "tag");
	}
	
	@Override
	public JsonNode ToJsonNode() 
	{
		ObjectNode node = FileManager.ObjMapper.createObjectNode();
		
		node.put("componentID", ComponentID());
		node.put("componentName", ComponentName());
		node.put("type", Type);
		
		return (JsonNode)node;
	}

	@Override
	public void FromJsonNode(JsonNode jsonNode) 
	{
		Type = jsonNode.path("tag").asText();
	}
}
