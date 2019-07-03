package res;
import java.util.HashMap;
import def.Application;
import processing.core.PImage;

public class Sprite 
{
	private int _sheetWidth, _sheetHeight, _columns, _rows, _tiles, _tileWidth, _tileHeight, _index;
	String _currentAnimation;
	private PImage _spriteSheet;
	private HashMap<String,int[]> _animations;

	public Sprite(int columns, int rows, PImage spriteSheet) 
	{
		 _columns          = columns;
		 _rows             = rows;
		 _sheetWidth       = spriteSheet.width;
		 _sheetHeight      = spriteSheet.height;
		 _tileWidth        = _sheetWidth / columns;
		 _tileHeight       = _sheetHeight / rows;
		 _tiles            = columns * rows;
		 _index            = 0;
		 _currentAnimation = "";
		 
		 _spriteSheet      = spriteSheet;
		 _animations       = new HashMap<String,int[]>();
		 
	}
	
	public void AddAnimation(String name, int[] frames) 
	{
		_animations.put(name, frames);
	}
	
	public void Update() 
	{
		StepForward(1);
	}
	
	public void Play(float x, float y) 
	{
		int[] animation = _animations.get(_currentAnimation);
		
		int f, c, offsetX, offsetY;
	    double col, row;
	    
	    f = animation[_index];
	    c = _columns;
	    
	    col = f % c;
	    
	    if(f > 0) 
	    {
	       row = Math.floor(f/c);
	    }
	    else 
	    {
	       row = 0;
	    }
	    
	    offsetX = (int)(col * _tileWidth);
	    offsetY = (int)(row * _tileHeight);
	    
	    Application.PROCESSING.copy(_spriteSheet,offsetX,offsetY,_tileWidth,_tileHeight,(int)x,(int)y,_tileWidth,_tileHeight);

	}
	

  private boolean AnimationIsValid() 
  {
	return _animations.get(_currentAnimation) != null; 
  }
  
  private int CurrentAnimationLength() 
  {
	return _animations.get(_currentAnimation).length;
  }
  
  public void StepForward(int times) 
  {
	if(AnimationIsValid() == false) { return; }
	
	int length = CurrentAnimationLength();
	
	_index = _index < length ? _index : 0;
	_index = _index > -1 ? _index : 0;
	
	do 
	{
	    _index = _index + 1 < _tiles ? _index + 1 : 0;	
	}
	while (times-- > 0);
  }
  
  public void StepBackward(int times) 
  {
	if(AnimationIsValid() == false) { return; }
	
	int length = CurrentAnimationLength();
	
	_index = _index < length ? _index : 0;
	_index = _index > -1 ? _index : 0;
	
	do 
	{
	    _index = _index - 1 > -1 ? _index - 1 : _tiles - 1;
	}
	while (times-- > 0);
  }
}
