public class Map 
{
  String _location;
  int[][] _collisions;
  
  Map(int[][] collisions, String location) 
  {
    _collisions = collisions;
    _location = location;
  }
  
  public String Location() 
  {
    return _location;
  }
  
  public int[][] Collisions() 
  {
    return _collisions;
  }
}
