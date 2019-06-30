package msg;
public class Message 
{
  int Flag;
  String[] Box;
  String Address;
  String Description;
  
  Message(int flag, String address, String description) 
  {
    Flag = flag;
    Box = new String[0];
    Address = address;
    Description = description;
  }
  
  Message(int flag, String[] box, String address, String description) 
  {
    Flag = flag;
    Box = box;
    Address = address;
    Description = description;
  }
}


