package vis;
import processing.core.PImage;

public class SpriteSheet 
{
	private int _sheetWidth, _sheetHeight, _columns, _rows, _tileWidth, _tileHeight;
	private PImage _image;
	
	public SpriteSheet(PImage image, int columns, int rows) 
	{
		 _image            = image;
		 _columns          = columns;
		 _rows             = rows;
		 _sheetWidth       = _image.width;
		 _sheetHeight      = _image.height;
		 _tileWidth        = _sheetWidth / columns;
		 _tileHeight       = _sheetHeight / rows;		  
	}
	
	public int SheetWidth() 
	{
		return _sheetWidth;
	}
	
	public int SheetHeight() 
	{
		return _sheetHeight;
	}
	
	public int Columns() 
	{
		return _columns;
	}
	
	public int Rows() 
	{
		return _rows;
	}
	
	public int TileWidth() 
	{
		return _tileWidth;
	}
	
	public int TileHeight() 
	{
		return _tileHeight;
	}
	
	public PImage Image() 
	{
		return _image;
	}
}
