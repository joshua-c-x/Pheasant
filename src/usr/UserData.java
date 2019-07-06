package usr;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "userName", "timeCreated", "fileUUID"})
public class UserData 
{
	public String userName;
	public String fileUUID;
	public long timeCreated;
	
	public UserData() {}
}
