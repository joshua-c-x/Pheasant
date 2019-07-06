package eng;

public abstract class Context 
{
	public static enum ExitCode { CONTINUE, EXITCONTEXT };	
	public static enum Name { MAINMENU, LOAD, CREATE, EXIT, PLAY }
	
	protected final Name _name;
	protected ExitCode _exitCode;
	
	public Context(Name state) 
	{
		_name = state;
	}
	
	public Name GetName() { return _name; }	
	
	public abstract int Update(IO io, float delta);
	public abstract int DrawContext(float delta);
	public abstract void Open();
	public abstract void Close();
	protected abstract int OnEnter();
	protected abstract int OnExit();	
	
}
