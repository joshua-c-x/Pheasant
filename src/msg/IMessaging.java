package msg;

import java.util.LinkedList;

public interface IMessaging 
{
  public LinkedList<Message> GetMessages();
  
  public void ReadMessage(Message message);
}
