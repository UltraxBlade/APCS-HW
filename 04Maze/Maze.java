
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
	private char[][] maze;
	private boolean animate;
	
	public Maze(String fileName) throws FileNotFoundException {
		maze=new char[0][0];
		animate=false;
		File text = new File(fileName);
		Scanner inf = new Scanner(text);
		int numStarts=0;
		int numEnds=0;
		while(inf.hasNextLine()){
			String line = inf.nextLine();
			if(maze.length==0){
				maze=new char[1][line.length()];
			} else {
				char[][] tempMaze = new char[maze.length][line.length()];
				for (int i = 0;i<maze.length;i++){
					for (int i2 = 0;i2<line.length();i2++){
						tempMaze[i][i2]=maze[i][i2];
					}
				}
				maze=new char[maze.length+1][line.length()];
				for (int i = 0;i<tempMaze.length;i++){
					for (int i2 = 0;i2<line.length();i2++){
						maze[i][i2]=tempMaze[i][i2];
					}
				}
			}
			for(int i = 0;i<line.length();i++){
				maze[maze.length-1][i]=line.charAt(i);
				if (line.charAt(i)=='S'){
					numStarts++;
				}
				if (line.charAt(i)=='E'){
					numEnds++;
				}
				if(numStarts>1||numEnds>1){
					System.out.println("Too many starts or ends");
					System.exit(1);
				}
			}
		}
		if (numStarts==0||numEnds==0){
			System.out.println("No start or end");
			System.exit(1);
		}
		
	}
	
	private void wait(int millis){
		try {
			Thread.sleep(millis);
		}
		catch (InterruptedException e) {
		}
	}
	
	public void setAnimate(boolean b){
		animate = b;
	}

	public void clearTerminal(){
		System.out.println("\033[2J\033[1;1H");
	}
	
	public void solve(){
		int startr=-1,startc=-1;
		for (int r=0;r<maze.length;r++){
			for (int c=0;c<maze[0].length;c++){
				if (maze[r][c]=='S'){
					startr=r;
					startc=c;
					r=maze.length;
					c=maze[0].length;
				}
			}
		}
		maze[startr][startc] = ' ';
		solver(startr,startc);
	}

	private boolean solver(int row, int col){
		if(animate){
			clearTerminal();
			System.out.println(this);
			wait(20);
		}
		if (maze[row][col]=='E'){
			return true;
		}
		maze[row][col]='@';
		if (maze[row-1][col]==' ' || maze[row-1][col]=='E'){
			if(solver(row-1,col)){
				return true;
			}
		}
		if (maze[row+1][col]==' ' || maze[row+1][col]=='E'){
			if(solver(row+1,col)){
				return true;
			}
		}
		if (maze[row][col-1]==' ' || maze[row][col-1]=='E'){
			if(solver(row,col-1)){
				return true;
			}
		}
		if (maze[row][col+1]==' ' || maze[row][col+1]=='E'){
			if(solver(row,col+1)){
				return true;
			}
		}
		maze[row][col]='.';
		return false;
	}
	
	public String toString(){
		String s = "";
		for (int x = 0; x<maze.length; x++){
			for (int y = 0; y<maze[0].length; y++){
				s+=maze[x][y];
			}
			s+="\n";
		}
		return s;
	}
}
