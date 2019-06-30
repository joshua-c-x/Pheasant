package eng;
import usr.*;

public class SessionManager
{
  Session _activeSession;
  SessionManager() {}  
  public void LoadSessionFromUserFile(UserFile userFile) 
  {
    _activeSession = new Session(userFile.UserName);
    
    _activeSession.LoadGameObjectsFromJSON(userFile.GameObjectsJSONArray);
    _activeSession.LoadActorsFromJSON(userFile.ActorsJSONArray);
    _activeSession.LoadMapsFromJSON(userFile.MapsJSONArray);
    _activeSession.LoadPlayerFromJSON(userFile.PlayerJSONObject);
    _activeSession.LoadWorldFromJSON(userFile.WorldJSONObject);
  }
  public boolean ActiveSessionExists() 
  {
    return _activeSession != null;
  }
  public Session ActiveSession() 
  {
    return _activeSession;
  }
  public void Close() 
  {
    _activeSession = null;
  }
}
