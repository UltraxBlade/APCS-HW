import java.util.LinkedList;
import java.util.Queue;

public class FrontierQueue implements Frontier{
	private Queue<Location> locations;
	
	public FrontierQueue(){
		locations=new LinkedList<Location>();
	}
	
	public void add(Location location){
		locations.add(location);
	}
	
	public Location next(){
		return locations.remove();
	}
	
	public boolean hasNext(){
		return !locations.isEmpty();
	}
}