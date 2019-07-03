package ont;
import components.*;
import ecs.*;
import eng.Assets;
import eng.IO;
import res.SpriteSheet;

public class GameObject extends Ontic
{
	public GameObject(int entity, EntityContainer entityContainer) 
	{
		super(entity, entityContainer);
	}
  
	public void Update(IO io, float delta) 
	{
	}
}
