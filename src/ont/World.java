package ont;

import java.util.ArrayList;

import ecs.Entity;
import eng.IO;

public class World 
{
  ArrayList<GameObject> _gameObjects;
  ArrayList<Actor> _actors;
  ArrayList<Map> _maps;
  Player _player;
  
  public String Temp = "";
  
  public World() 
  {
    _gameObjects  = new ArrayList<GameObject>();
    _actors       = new ArrayList<Actor>();
    _maps         = new ArrayList<Map>();
  }
  
  public Player Player() 
  {
    return _player;
  }
  
  public ArrayList<GameObject> GameObjects() 
  {
    return _gameObjects;
  }
  
  public ArrayList<Actor> Actors() 
  {
    return _actors;
  }
  
  public ArrayList<Map> Maps() 
  {
    return _maps;
  }
  
  public void SetTemp(String temp) 
  {
    Temp = temp;
  }
  
  public void AddMap(Map map) 
  {
    _maps.add(map);
  }
  
  public void SetPlayer(Entity e) 
  {
    _player = new Player(e);
  }
  
  public void AddActor(Entity e) 
  {
    _actors.add(new Actor(e));
  }
  
  public void AddGameObject(Entity e) 
  {	  
    _gameObjects.add(new GameObject(e));
  }
  
  public void Update(IO io, float delta) 
  {
    // todo Update()
    _player.Update(io, delta);
    
    for(Actor actor : _actors) 
    {
      actor.Update(io, delta);
    }
    
    for(GameObject obj : _gameObjects) 
    {
      obj.Update(io, delta);
    }
  }
}
