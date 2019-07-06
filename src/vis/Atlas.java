package vis;

import def.Application;
import ont.Tilemap;
import processing.core.PImage;

public class Atlas 
{
	public static final long LocationOfCustomerService = 1 << 0;
	public static final String NameOfCustomerService = "customerservice";
	public static Tilemap TilemapOfCustomerService;

	private static String _nameBuffer;
	private static long _locationBuffer;
	private static PImage _imageBuffer;
	private static int _tileXBuffer, _tileYBuffer;
	private static int[] _collisionBuffer;
	
	private static final String MapImagesPath = Application.PATH + "\\data\\graphics\\locations\\";
	
	//sketchpath
	public static void Build() 
	{
		// Customer Service
		_nameBuffer      = NameOfCustomerService;
		_locationBuffer  = LocationOfCustomerService;
		
		_collisionBuffer = new int[] 
		{
				0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
				0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
				0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
				0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,
				1,1,0,0,0,0,1,1,1,0,0,0,0,1,1,1,1,1,1,1,
				1,1,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,
				1,1,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,
				1,1,0,0,0,1,1,1,1,1,0,0,0,1,1,0,0,0,0,1,
				1,1,0,0,0,1,1,1,1,1,0,0,0,1,1,0,0,0,0,1,
				1,1,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,
				1,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,
				1,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,
				1,1,0,0,0,1,1,1,1,0,1,1,0,1,0,0,1,1,1,1,
				1,1,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0	
		};
		
	}
}
