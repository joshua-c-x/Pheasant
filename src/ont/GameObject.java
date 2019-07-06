package ont;
import components.*;
import ecs.*;
import eng.IO;
import vis.Assets;
import vis.SpriteSheet;

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
