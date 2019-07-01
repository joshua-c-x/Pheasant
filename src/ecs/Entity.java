package ecs;
import java.util.ArrayList;
import java.util.UUID;

import processing.data.JSONArray;
import processing.data.JSONObject;
import res.Animation;
import res.WorldRenderer;

public class Entity 
{
  private int _tag, _tileX, _tileY, _tilesX, _tilesY, _imageWidth, _imageHeight;
  private float _radius;
  private boolean _render;
  private String _location, _name;
  private UUID _uuid;
  private ArrayList<IComponent> _components;
  private WorldRenderer _worldRenderer;
  
  public Entity(int tag, String location, String name, boolean render) 
  {
	_tag        = tag;
	_render     = render;
	_location   = location;
	_name       = name;
	_uuid       = UUID.randomUUID();  
	_components = new ArrayList<IComponent>();
	_worldRenderer = new WorldRenderer(_render);
  }
  
  public void SetDimensions(int tileX, int tileY, int tilesX, int tilesY, int imageWidth, int imageHeight, float radius) 
  {
	  _tileX       = tileX;
	  _tileY       = tileY;
	  _tilesX      = tilesX;
	  _tilesY      = tilesY;
	  _imageWidth  = imageWidth;
	  _imageHeight = imageHeight; 
	  _radius      = radius;
  }
  
  
  public WorldRenderer GetWorldRenderer() 
  {
	  return _worldRenderer;
  }
  
  public void AddAnimation(Animation animation) 
  {
	  _worldRenderer.InsertAnimation(animation);
  }
  
  public JSONObject ToJSON() 
  {
	  JSONObject json = new JSONObject();
	  
	  json.setInt("TAG", this.Tag());
	  json.setString("LOCATION", this.Location());
	  json.setString("NAME", this.Name());
	  json.setInt("TILEX", this.TileX());
	  json.setInt("TILEY", this.TileY());
	  json.setInt("TILESX", this.TilesX());
	  json.setInt("TILESY", this.TilesY());
	  json.setInt("IMAGEWIDTH", this.ImageWidth());
	  json.setInt("IMAGEHEIGHT", this.ImageHeight());
	  json.setFloat("RADIUS", this.Radius());
	  json.setBoolean("RENDER", this.Render());

	  JSONArray componentsJSON = new JSONArray();
	  JSONArray animationsJSON = new JSONArray();	
	  
	  ArrayList<Animation> animations = GetWorldRenderer().Animations();
	  
	  for(int j = 0; j < animations.size(); j += 1) 
	  {
		  	Animation animation = animations.get(j);

		  	String name = animation.Name();
		  	int[] frames = animation.Frames();
		  	
		  	JSONObject jsonAnimation = new JSONObject();
			JSONArray jsonArrayFrames = new JSONArray();
			
		  	for(int i = 0; i < frames.length; i += 1) 
		  	{		  		
		  		jsonArrayFrames.setInt(i, frames[i]);
		  	}
		  	
			jsonAnimation.setString("NAME", name); 
			jsonAnimation.setJSONArray("FRAMES", jsonArrayFrames);
			
			animationsJSON.setJSONObject(j, jsonAnimation);
	  }
	  
	  json.setJSONArray("ANIMATIONS", animationsJSON);
	  json.setJSONArray("COMPONENTS", componentsJSON);
	  
	  return json;
  }
  
  public void AddComponent(IComponent component) 
  {
	  _components.add(component);
  }
  
  public void RemoveComponent(IComponent component) 
  {
	  _components.remove(component);
  }
  
  public Entity GetSelfAsEntity() 
  {
	  return this;
  }

  public UUID GetUUID() 
  {
	  return _uuid;
  }
  
  public void SetTileX(int x) 
  {
	  _tileX = x;
  }
  
  public void SetTileY(int y) 
  {
	  _tileY = y;
  }
  
  public float Radius() 
  {
	  return _radius;  
  }
  
  public boolean Render() 
  {
	  return _render;
  }
  
  public int TileX() 
  {
	  return _tileX;
  }
  
  public int TileY() 
  {
	  return _tileY;
  }
  
  public int TilesX() 
  {
	  return _tilesX;
  }
  
  public int TilesY() 
  {
	  return _tilesY;
  }
  
  public int ImageWidth() 
  {
	  return _imageWidth;
  }
  
  public int ImageHeight() 
  {
	  return _imageHeight;
  }
  
  public int Tag() 
  {
	  return _tag;
  }
  
  public String Location() 
  {
	  return _location;
  }
  	
  public String Name() 
  {
	  return _name;
  }

}
