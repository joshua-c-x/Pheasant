class World 
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
  
  public void SetPlayer(Player player) 
  {
    _player = player;
  }
  
  public void AddMap(Map map) 
  {
    _maps.add(map);
  }
  
  public void AddActor(Actor actor) 
  {
    _actors.add(actor);
  }
  
  public void AddGameObject(GameObject obj) 
  {
    _gameObjects.add(obj);
  }
  
  public void Update(IO io, float delta) 
  {
    // todo Update()
    _player.Update(io, delta);
    
    for(Actor actor : _actors) 
    {
      actor.Update(io, delta);
    }
    
    for(GameObject object : _gameObjects) 
    {
      object.Update(io, delta);
    }
  }
}
