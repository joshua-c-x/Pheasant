package systems;

import components.Position;
import components.Sprite;
import def.Application;
import ecs.Components;
import ecs.EntityContainer;
import ecs.Systems;
import eng.Assets;
import eng.Engine;
import eng.IO;
import eng.Parameters;
import ont.World;
import processing.data.IntList;
import res.Animation;
import res.AnimationLibrary;
import res.SpriteSheet;

public class SpriteSystem extends Systems
{
	public SpriteSystem() {}
	
	private int[] GetSprites(EntityContainer entities) 
	{
		IntList _sprites = new IntList();
		
		long[] flags = entities.Flags();
		
		for(int i = 0; i < flags.length; i += 1) 
		{
			long pattern = Components.Sprite | Components.Position;
			
			if((flags[i] & pattern) == pattern) 
			{
				_sprites.append(i);
			}
		}
		return _sprites.array();
	}
	
	public void Update(EntityContainer entities, World world, IO io, float delta) 
	{
		int[] _sprites = GetSprites(entities);
		
		for(int i : _sprites) 
		{
			Sprite s = (Sprite)entities.GetComponent(i, Components.Sprite);
			Position p = (Position)entities.GetComponent(i, Components.Position);
			
			DrawSprite(s, p.TileX * Parameters.TileDimension, p.TileY * Parameters.TileDimension, delta);
		}
	}
	
	public void UpdateSprite(Sprite sprite, Animation animation, float delta) 
	{
		int index, limit;
		
		animation.Elapsed += delta;
		
		if(animation.Elapsed > animation.FrameDuration()) 
		{
			animation.Elapsed = 0.0f;
			
			index = sprite.Index;	
			index += 1;
			limit = animation.Frames().length;
			index = index < limit ? index : 0;
			index = index > -1 ? index : limit - 1;
			sprite.Index = index;	
		}
	}
	
	public void DrawSprite(Sprite sprite, float x, float y, float delta) 
	{
		SpriteSheet sheet = Assets.SpriteSheets.get(sprite.SpriteID);
		AnimationLibrary lib = Assets.AnimationLibraries.get(sprite.SpriteID);		
		Animation animation;
		
		int f, c, offsetX, offsetY;
	    double col, row;
	    
		if(lib.ContainsAnimation(Assets.AnimationName_Idle)) 
		{
			animation = lib.Animations.get(Assets.AnimationName_WalkDown);
			
			UpdateSprite(sprite, animation, delta);
			
			f = animation.Frames()[sprite.Index];		    
		    
		    c = sheet.Columns();
		    
		    col = f % c;
		    
		    if(f > 0) 
		    {
		       row = Math.floor(f/c);
		    }
		    else 
		    {
		       row = 0;
		    }
		    
		    offsetX = (int)(col * sheet.TileWidth());
		    offsetY = (int)(row * sheet.TileHeight());
		    
		    int _x = (int)x;
		    int _y = (int)y;
		    
		    int tw = sheet.TileWidth();
		    int th = sheet.TileHeight();
		    
		    Application.PROCESSING.copy(sheet.Image(), offsetX, offsetY, tw, th, _x, _y, tw, th);
		}
	}
}
