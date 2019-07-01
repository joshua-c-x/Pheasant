package res;

import java.util.ArrayList;

public class WorldRenderer 
{
	private ArrayList<Animation> _animations;
	private Animation _currentAnimation;
	private boolean _render;
	
	public WorldRenderer(boolean render) 
	{
		_render     = render;
		_animations = new ArrayList<Animation>();
	}
	
	public ArrayList<Animation> Animations() 
	{
		return _animations;
	}
	
	public void SetCurrentAnimation(String name) 
	{
		for(Animation animation : _animations) 
		{
			if(animation.Name().equals(name)) 
			{
				_currentAnimation = animation;
				break;
			}
		}
		return;
	}
	
	public boolean Render() 
	{
		return _render;
	}
	
	public void InsertAnimation(Animation animation) 
	{
		_animations.add(animation);
	}
}
