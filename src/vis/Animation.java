package vis;

public class Animation 
{
	private int[] _frames;
	private String _name;
	private float _duration;
	
	public float Elapsed;
	
	public Animation(String name, int[] frames, float duration) 
	{
		_frames   = frames;
		_name     = name;
		_duration = duration;
		
		Elapsed  = 0.0f;
	}
	
	public int[] Frames() 
	{
		return _frames;
	}
	
	public String Name() 
	{
		return _name;
	}
	
	public float Duration() 
	{
		return _duration;
	}
	
	public float FrameDuration() 
	{
		if(_duration == 0 || _frames.length == 0) 
		{
			return 0;
		} 
		return _duration / (float)_frames.length;
	}
}
