package vis;

import java.util.HashMap;

public class AnimationLibrary 
{
	public HashMap<String, Animation> Animations;	
	
	public AnimationLibrary(Animation...animations) 
	{
		Animations = new HashMap<String, Animation>();
		
		for(int i = 0; i < animations.length; i += 1)
		{
			Animations.put(animations[i].Name().toLowerCase(), animations[i]);
		}
	}
	
	public boolean ContainsAnimation(String animationName) 
	{
		return this.Animations.containsKey(animationName);
	}
}
