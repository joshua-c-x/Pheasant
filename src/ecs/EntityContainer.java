package ecs;

import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import components.*;
import eng.Engine;
import eng.FileManager;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class EntityContainer {

    private long[] _flags;
    private long[] _tmpFlags;

    private UUID[] _uniqueIDs;
    private UUID[] _tmpUniqueIDs;

    // All Components	
    public Tag[] Tags;
    public Position[] WorldPositions;
    public Sprite[] SpriteIDs;

    public EntityContainer() 
    {
        int e = 0;

        _flags = new long[e];
        _uniqueIDs = new UUID[e];

        Tags = new Tag[e];
        WorldPositions = new Position[e];
        SpriteIDs = new Sprite[e];
    }

    public long[] Flags() 
    {
        return _flags;
    }

    public void LoadEntityContainerJsonNode(JsonNode entityNodes)
    {
    	if(entityNodes == null || entityNodes.isMissingNode()) 
    	{
    		Engine.ErrorAndHalt("entity json node is missing!");
    	}
    	
        for (JsonNode entityNode : entityNodes) 
        {     
            long flag = 0;
            
            ArrayNode arrayComponents = (ArrayNode)entityNode.path("components");
            
            // create flag by bitwise OR |= every component id found onto flag
            
            if (arrayComponents.isArray()) 
            {
                for (final JsonNode componentNode : arrayComponents) 
                {
                    long componentFlag = componentNode.path("componentID").asLong();
                    flag |= componentFlag;
                }
                
                int entity = CreateEntity(flag);
                
                for (final JsonNode componentNode : arrayComponents) 
                {
                    AddComponentToEntity(entity, componentNode);
                }
            }
        }
    }

    public JsonNode ExportEntityContainerJsonNode() 
    {
        ObjectNode entityContainerNode = FileManager.ObjMapper.createObjectNode();
       
        entityContainerNode.putArray("entities");
        
        for (int i = 0; i < _flags.length; i += 1) 
        {
            ObjectNode entity = FileManager.ObjMapper.createObjectNode();
            
            entity.putArray("components");
            
            long flag = _flags[i];

            if ((flag & Components.Tag) > 0) entity.withArray("components").add(Tags[i].ToJsonNode());
            if ((flag & Components.Position) > 0) entity.withArray("components").add(WorldPositions[i].ToJsonNode());
            if ((flag & Components.Sprite) > 0) entity.withArray("components").add(SpriteIDs[i].ToJsonNode());
            
            entityContainerNode.withArray("entities").add(entity);
        }

        return entityContainerNode;
    }

    public Boolean HasComponent(int entity, long componentID)
    {
        return ((_flags[entity] & componentID) > 0);
    }

    public Components GetComponent(int entity, long componentID) 
    {
        Components c = null;

        long flag = _flags[entity] & componentID;
        // ^ otherwise the first component the entity has that matches
        // will be returned

        if ((flag & Components.Tag) > 0) 
        {
            c = Tags[entity];
        }
        else if ((flag & Components.Position) > 0) 
        {
            c = WorldPositions[entity];
        }
        else if ((flag & Components.Sprite) > 0) 
        {
            c = SpriteIDs[entity];
        }
        
        return c;
    }

    private int CreateEntity(long flag) 
    {
        int next = Expand();

        _flags[next] = flag;
        _uniqueIDs[next] = UUID.randomUUID();

        if ((flag & Components.Tag) > 0) 
        {
            Tags[next] = new Tag();
        }
        if ((flag & Components.Position) > 0)
        {
            WorldPositions[next] = new Position();
        }
        if ((flag & Components.Sprite) > 0) 
        {
            SpriteIDs[next] = new Sprite();
        }

        return next;
    }

    private void AddComponentToEntity(int entity, JsonNode componentNode) 
    {
    	long componentID = componentNode.path("componentID").asLong();
    	
        if ((componentID & Components.Tag) > 0) 
        {
            Tags[entity].FromJsonNode(componentNode);
        } 
        else if ((componentID & Components.Position) > 0) 
        {
            WorldPositions[entity].FromJsonNode(componentNode);
        } 
        else if ((componentID & Components.Sprite) > 0) 
        {
            SpriteIDs[entity].FromJsonNode(componentNode);
        }
    }

    private int Expand() 
    {
        int prev, next, i;

        prev = _flags.length;
        next = prev + 1;

        ///////////////////////////////////
        // RESIZE IDS					 //
        ///////////////////////////////////

        // 0. initialize _tmp
        _tmpFlags = new long[next];

        // 1. set every _tmp[i] to _ids[i]
        for (i = 0; i < _flags.length; i += 1) {
            _tmpFlags[i] = _flags[i];
        }

        // 2. resize _ids 
        _flags = _tmpFlags;

        ///////////////////////////////////
        // RESIZE UUIDS ///////////////////
        ///////////////////////////////////

        _tmpUniqueIDs = new UUID[next];

        for (i = 0; i < _uniqueIDs.length; i += 1) 
        {
            _tmpUniqueIDs[i] = _uniqueIDs[i];
        }

        _uniqueIDs = _tmpUniqueIDs;

        ///////////////////////////////////
        // RESIZE COMPONENTS///////////////
        ///////////////////////////////////

        // 0. TAG

        Tag[] tempTags = new Tag[next];

        for (i = 0; i < Tags.length; i += 1) 
        {
            tempTags[i] = Tags[i];
        }

        Tags = tempTags;

        // 1. WorldPositions

        Position[] tempWorldPositions = new Position[next];

        for (i = 0; i < WorldPositions.length; i += 1) 
        {
            tempWorldPositions[i] = WorldPositions[i];
        }

        WorldPositions = tempWorldPositions;

        // 2. SpriteIDs

        Sprite[] tempSpriteIDs = new Sprite[next];

        for (i = 0; i < SpriteIDs.length; i += 1) 
        {
            tempSpriteIDs[i] = SpriteIDs[i];
        }

        SpriteIDs = tempSpriteIDs;

        // return expanded address ( minus 1 because of 0 index array access)

        return next - 1;
    }
}