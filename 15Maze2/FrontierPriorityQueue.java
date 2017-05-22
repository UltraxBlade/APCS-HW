import java.util.NoSuchElementException;

public class FrontierPriorityQueue implements Frontier{
	private MyHeap locations;
	
	public FrontierPriorityQueue (){
		locations=new MyHeap(false);
	}
	
	public void add(Location location){
		locations.add(location);
	}
	
	public Location next(){
		return locations.remove();
	}
	
	public boolean hasNext(){
		try{
			locations.peek();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
