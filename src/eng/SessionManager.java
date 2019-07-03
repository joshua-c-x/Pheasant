package eng;

import usr.Session;

public class SessionManager 
{	
	public static Session ActiveSession;
	
	public static boolean ActiveSessionExists() 
	{
		return ActiveSession != null;
	}
	
	public static void LoadSession(Session session) 
	{
		ActiveSession = session;
	}
}
