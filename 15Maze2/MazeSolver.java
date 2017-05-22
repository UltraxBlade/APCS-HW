
public class MazeSolver {
	private Maze maze;
	private Frontier frontier;
	private boolean aStar;
	private boolean animate;
	
	public MazeSolver (String filename){
		this(filename,false);
	}
	
	public MazeSolver (String filename, boolean animated){
		maze=new Maze(filename);
		animate=animated;
	}

	public void solve(){
		solve(1);
	}
	
	private void addNeighbors(Location current){
		if (maze.get(current.getRow()+1, current.getCol())==' '){
			frontier.add(new Location(current.getRow()+1,current.getCol(),current,current.getDistToStart()+1,
					Math.abs((maze.getEnd().getRow())-(current.getRow()+1)) + Math.abs((maze.getEnd().getCol())-(current.getCol())),
					aStar));
			maze.set(current.getRow()+1, current.getCol(), '?');
		}
		if (maze.get(current.getRow()-1, current.getCol())==' '){
			frontier.add(new Location(current.getRow()-1,current.getCol(),current,current.getDistToStart()+1,
					Math.abs((maze.getEnd().getRow())-(current.getRow()-1)) + Math.abs((maze.getEnd().getCol())-(current.getCol())),
					aStar));
			maze.set(current.getRow()-1, current.getCol(), '?');
		}
		if (maze.get(current.getRow(), current.getCol()+1)==' '){
			frontier.add(new Location(current.getRow(),current.getCol()+1,current,current.getDistToStart()+1,
					Math.abs((maze.getEnd().getRow())-(current.getRow())) + Math.abs((maze.getEnd().getCol())-(current.getCol()+1)),
					aStar));
			maze.set(current.getRow(), current.getCol()+1, '?');
		}
		if (maze.get(current.getRow(), current.getCol()-1)==' '){
			frontier.add(new Location(current.getRow(),current.getCol()-1,current,current.getDistToStart()+1,
					Math.abs((maze.getEnd().getRow())-(current.getRow())) + Math.abs((maze.getEnd().getCol())-(current.getCol()-1)),
					aStar));
			maze.set(current.getRow(), current.getCol()-1, '?');
		}
	}
	
	public void solve(int mode){
		aStar=false;
		if (mode==0){
			frontier=new FrontierStack();
		} else if (mode==1){
			frontier=new FrontierQueue();
		} else if (mode==2){
			frontier=new FrontierPriorityQueue();
		} else if (mode==3){
			frontier=new FrontierPriorityQueue();
			aStar=true;
		} else{
			return;
		}
		frontier.add(maze.getStart());
		boolean solved = false;
		Location current = null;
		while(frontier.hasNext() && !solved){
			current=frontier.next();
			if (current.getRow()==maze.getEnd().getRow() && current.getCol()==maze.getEnd().getCol()){
				solved=true;
			} else {
				maze.set(current.getRow(), current.getCol(), '.');
				addNeighbors(current);
				if(animate){
					System.out.println(maze.toString(250));
				}
			}
		}
		if(solved){
			while (current!=null){
				maze.set(current.getRow(), current.getCol(), '@');
				current=current.getPrev();
			}
		}
		maze.set(maze.getStart().getRow(), maze.getStart().getCol(), 'S');
		maze.set(maze.getEnd().getRow(), maze.getEnd().getCol(), 'E');
		System.out.println(maze);
	}
}
