package ctx;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import def.Application;
import eng.Context;
import eng.FileManager;
import eng.IO;
import vis.Assets;

public class MainMenu extends Context
{

	private static ArrayList<String> _lines;
	
	public MainMenu() 
	{
		super(Context.Name.MAINMENU);
	}

	@Override
	public int OnEnter() 
	{
		FileManager.LoadUsers();
		
		_lines = new ArrayList<String>();
				
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
		_lines.clear();
		JsonNode userName;	
		for(ObjectNode user : FileManager.Users) 
		{
			
			userName = user.path("userName");
			if(userName.isMissingNode() != true) 
			{
				_lines.add(userName.asText());
			}
		}
		return 0;
	}

	@Override
	public int DrawContext(float delta) 
	{
		Application.PROCESSING.image(Assets.Images.get("mainbg"),0,0);
		
		int index = 0;
		if(_lines.size() > 0) 
		{
			for(String line : _lines) 
			{
				Application.PROCESSING.textFont(Assets.Fonts.get(Assets.Fonts_Font0));
				Application.PROCESSING.fill(0);
				Application.PROCESSING.text(line, Application.PROCESSING.width / 2, (Application.PROCESSING.height / 2) - 50f + (index * 16));
				index += 1;
			}
			
		}
		
		return 0;
	}

	@Override
	public void Open() 
	{
		OnEnter();
	}

	@Override
	public void Close() 
	{
	
	}
}
