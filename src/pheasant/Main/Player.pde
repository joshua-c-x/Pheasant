class Player extends Actor 
{  
  public Player(int tileX, int tileY, int dimX, int dimY, float radius) 
  {
    super(tileX, tileY, dimX, dimY, radius);
  }
  
  void Update(IO io, float delta) 
  {
    if(io.KeysJustPressed.get(Engine.Keys_Up)) 
    {
      _tileY -= 1;
    }
    if(io.KeysJustPressed.get(Engine.Keys_Down)) 
    {
      _tileY += 1;
    }
    if(io.KeysJustPressed.get(Engine.Keys_Left)) 
    {
      _tileX -= 1;
    }
    if(io.KeysJustPressed.get(Engine.Keys_Right)) 
    {
      _tileX += 1;
    }
    DrawDebug();
  }
  
  void DrawDebug() 
  {
    noFill();
    stroke(255,0,255,150);
    rect(_tileX * Engine.TileDimension,_tileY * Engine.TileDimension, Engine.TileDimension, Engine.TileDimension);
    fill(0);
    text('p', _tileX * Engine.TileDimension, (_tileY * Engine.TileDimension) + Engine.TileDimension);
  }
}
