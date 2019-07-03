package ecs;

import java.util.UUID;

import components.*;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class EntityContainer {

    private long[] _flags;
    private long[] _tmpFlags;

    private UUID[] _uniqueIDs;
    private UUID[] _tmpUniqueIDs;

    // All Components	
    public Tag[] Tags;
    public WorldPosition[] WorldPositions;
    public SpriteID[] SpriteIDs;

    public EntityContainer() 
    {
        int e = 0;

        _flags = new long[e];
        _uniqueIDs = new UUID[e];

        Tags = new Tag[e];
        WorldPositions = new WorldPosition[e];
        SpriteIDs = new SpriteID[e];
    }

    public long[] GetAllEntityFlags() 
    {
        return _flags;
    }

    public void LoadEntitiesFromJSON(JSONArray jsonArray)
    {
        for (int i = 0; i < jsonArray.size(); i += 1) 
        {
            // pull one json entity from JSONArray
            JSONObject jsonEntity = jsonArray.getJSONObject(i);

            // get components from json entity
            JSONArray components = jsonEntity.getJSONArray("COMPONENTS");

            // set empty flag
            long flag = Components.Empty;

            // create flag by bitwise OR |= every component id found onto flag
            for (int j = 0; j < components.size(); j += 1) {
                JSONObject jsonComponent = components.getJSONObject(j);
                flag |= jsonComponent.getLong("ID");
            }

            // using flag, create entity
            int entity = CreateEntity(flag);

            // for every jsonComponent in components,
            // if its component ID matches with a ComponentID,
            // run build the object with FromJSONdd

            for (int j = 0; j < components.size(); j += 1) {
                JSONObject jsonComponent = components.getJSONObject(j);
                JSONObject data = jsonComponent.getJSONObject("DATA");

                long componentID = jsonComponent.getLong("ID");
                BuildEntityFromJSONData(entity, componentID, data);
            }
        }
    }
    // System.err.println
    public JSONArray ExportEntitiesToJSON() 
    {
        JSONArray jsonArray = new JSONArray();


        for (int i = 0; i < _flags.length; i += 1) {
            JSONObject jsonEntity = new JSONObject();
            JSONArray jsonComponents = new JSONArray();

            long flag = _flags[i];

            if ((flag & Components.Tag) > 0) jsonComponents.append(Tags[i].ToJSON());
            if ((flag & Components.WorldPosition) > 0) jsonComponents.append(WorldPositions[i].ToJSON());
            if ((flag & Components.SpriteID) > 0) jsonComponents.append(SpriteIDs[i].ToJSON());

            jsonEntity.setJSONArray("COMPONENTS", jsonComponents);

            jsonArray.append(jsonEntity);
        }

        return jsonArray;
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
        else if ((flag & Components.WorldPosition) > 0) 
        {
            c = WorldPositions[entity];
        }
        else if ((flag & Components.SpriteID) > 0) 
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
        if ((flag & Components.WorldPosition) > 0)
        {
            WorldPositions[next] = new WorldPosition();
        }
        if ((flag & Components.SpriteID) > 0) 
        {
            SpriteIDs[next] = new SpriteID();
        }

        return next;
    }

    private void BuildEntityFromJSONData(int entity, long componentID, JSONObject data) 
    {
        // component data is transferred from file to Entities object
        if ((componentID & Components.Tag) > 0) 
        {
            Tags[entity].FromJSON(data);
        } else if ((componentID & Components.WorldPosition) > 0) 
        {
            WorldPositions[entity].FromJSON(data);
        } else if ((componentID & Components.SpriteID) > 0) 
        {
            SpriteIDs[entity].FromJSON(data);
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

        for (i = 0; i < _uniqueIDs.length; i += 1) {
            _tmpUniqueIDs[i] = _uniqueIDs[i];
        }

        _uniqueIDs = _tmpUniqueIDs;

        ///////////////////////////////////
        // RESIZE COMPONENTS///////////////
        ///////////////////////////////////

        // 0. TAG

        Tag[] tempTags = new Tag[next];

        for (i = 0; i < Tags.length; i += 1) {
            tempTags[i] = Tags[i];
        }

        Tags = tempTags;

        // 1. WorldPositions

        WorldPosition[] tempWorldPositions = new WorldPosition[next];

        for (i = 0; i < WorldPositions.length; i += 1) {
            tempWorldPositions[i] = WorldPositions[i];
        }

        WorldPositions = tempWorldPositions;

        // 2. SpriteIDs

        SpriteID[] tempSpriteIDs = new SpriteID[next];

        for (i = 0; i < SpriteIDs.length; i += 1) {
            tempSpriteIDs[i] = SpriteIDs[i];
        }

        SpriteIDs = tempSpriteIDs;

        // return expanded address ( minus 1 because of 0 index array access)

        return next - 1;
    }
}