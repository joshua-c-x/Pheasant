class Scene 
{  
  Session _session;
  
  Scene(Session session) 
  {
    _session = session;
  }
  
  ArrayList<GameObject> GetLocalGameObjects() 
  {
    return new ArrayList<GameObject>();
  }
  
  ArrayList<Actor> GetLocalNPCs() 
  {
    return new ArrayList<Actor>();
  }
  
}
