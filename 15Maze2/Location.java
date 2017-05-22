
public class Location implements Comparable<Location>{
	private int row;
	private int col;
	private int distToGoal;
	private int distToStart;
	private Location previous;
	private boolean aStar;
	
	public Location(int r, int c, Location previous, int distToStart, int distToGoal, boolean aStar){
		row=r;
		col=c;
		this.previous=previous;
		this.distToStart=distToStart;
		this.distToGoal=distToGoal;
		this.aStar=aStar;
	}
	
	public int compareTo(Location other){
		if(aStar){
			return (distToGoal+distToStart)-(other.distToGoal+other.distToStart);
		} else {
			return distToGoal-other.distToGoal;
		}
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	
	public Location getPrev(){
		return previous;
	}
	
	public int getDistToStart(){
		return distToStart;
	}
}
