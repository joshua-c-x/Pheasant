package ctx;

import eng.Context;
import eng.IO;

public class Create extends Context
{

	public Create() 
	{
		super(Context.Name.CREATE);
	}

	@Override
	public int OnEnter() 
	{

		return 0;
	}

	@Override
	public int OnExit() 
	{

		return 0;
	}
	
	@Override
	public int Update(IO io, float delta) 
	{
		return 0;
	}

	@Override
	public int DrawContext(float delta) 
	{
		return 0;
	}
	
	@Override
	public void Open() 
	{
	
	}

	@Override
	public void Close() 
	{
	
	}
}
