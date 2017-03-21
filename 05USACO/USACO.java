
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class USACO {
	public static int bronze(String fileName) throws FileNotFoundException {
		File input = new File(fileName);
		Scanner reader = new Scanner(input);
		String line = reader.nextLine();
		int[][] pasture=new int[Integer.parseInt(line.split(" ")[0])][Integer.parseInt(line.split(" ")[1])];
		int E=Integer.parseInt(line.split(" ")[2]);
		int N=Integer.parseInt(line.split(" ")[3]);
		for (int r = 0; r<pasture.length; r++){
			line = reader.nextLine();
			for (int c = 0; c<pasture[0].length; c++){
				pasture[r][c]=Integer.parseInt(line.split(" ")[c]);
			}
		}
		for (int cow = 0; cow < N; cow++){
			line = reader.nextLine();
			pasture = cowStomp (Integer.parseInt(line.split(" ")[0])-1, Integer.parseInt(line.split(" ")[1])-1, Integer.parseInt(line.split(" ")[2]), pasture);
		}
		int total=0;
		for (int r = 0; r<pasture.length; r++){
			for (int c = 0; c<pasture[0].length; c++){
				if (E-pasture[r][c]>0){
					total+=E-pasture[r][c];
				}
			}
		}
		return total*72*72;
	}
	
	private static int[][] cowStomp (int r, int c, int d, int[][] pasture){
		int max = pasture[r][c];
		for (int row = 0; row < 3; row++){
			for (int col = 0; col < 3; col++){
				if (pasture[r+row][c+col]>max){
					max=pasture[r+row][c+col];
				}
			}
		}
		max-=d;
		for (int row = 0; row < 3; row++){
			for (int col = 0; col < 3; col++){
				if (pasture[r+row][c+col]>max){
					pasture[r+row][c+col]=max;
				}
			}
		}
		return pasture;
	}
	
	public static int silver(String fileName) throws FileNotFoundException {
		int[][] pasture;
		int T;
		File input = new File(fileName);
		Scanner reader = new Scanner(input);
		String line = reader.nextLine();
		pasture=new int[Integer.parseInt(line.split(" ")[0])][Integer.parseInt(line.split(" ")[1])];
		T=Integer.parseInt(line.split(" ")[2]);
		for (int r = 0; r<pasture.length; r++){
			line = reader.nextLine();
			for (int c = 0; c<pasture[0].length; c++){
				if(line.charAt(c)=='.'){
					pasture[r][c]=0;
				} else {
					pasture[r][c]=-1;
				}
			}
		}
		line=reader.nextLine();
		return countWays(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]), Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]), T, pasture);
	}
	
	private static int countWays(int r, int c, int endR, int endC, int T, int[][] pasture){
		for (int i = 0; i<T;i++){
			int[][] temp = new int[pasture.length][pasture[0].length];
			for (int row = 0; row<pasture.length; row++){
				for (int col = 0; col<pasture[0].length; col++){
					if (pasture[row][col]>=0){
						if (row>0 && pasture[row-1][col]>=0){
							temp[row][col]+=pasture[row-1][col];
						}
						if (col>0 && pasture[row][col-1]>=0){
							temp[row][col]+=pasture[row][col-1];
						}
						if (row<pasture.length-1 && pasture[row+1][col]>=0){
							temp[row][col]+=pasture[row+1][col];
						}
						if (col<pasture[0].length-1 && pasture[row][col+1]>=0){
						temp[row][col]+=pasture[row][col+1];
						}
					}
				}
			}
			pasture=temp;
		}
		return pasture[endR][endC];
	}
	
	public static int silverSlow(String fileName) throws FileNotFoundException {
		char[][] pasture;
		int T;
		File input = new File(fileName);
		Scanner reader = new Scanner(input);
		String line = reader.nextLine();
		pasture=new char[Integer.parseInt(line.split(" ")[0])][Integer.parseInt(line.split(" ")[1])];
		T=Integer.parseInt(line.split(" ")[2]);
		for (int r = 0; r<pasture.length; r++){
			line = reader.nextLine();
			for (int c = 0; c<pasture[0].length; c++){
				pasture[r][c]=line.charAt(c);
			}
		}
		line=reader.nextLine();
		return countWaysSlow(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]), Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]), T, pasture);
	}
	
	private static int countWaysSlow(int r, int c, int endR, int endC, int T, char[][] pasture){
		if (T==0){
			if(r==endR && c==endC){
				return 1;
			}
			return 0;
		}
		int total=0;
		if (r>0 && pasture [r-1][c]=='.'){
			total+=countWaysSlow(r-1, c, endR, endC, T-1, pasture);
		}
		if (c>0 && pasture [r][c-1]=='.'){
			total+=countWaysSlow(r, c-1, endR, endC, T-1, pasture);
		}
		if (r<pasture.length-1 && pasture [r+1][c]=='.'){
			total+=countWaysSlow(r+1, c, endR, endC, T-1, pasture);
		}
		if (c<pasture[0].length-1 && pasture [r][c+1]=='.'){
			total+=countWaysSlow(r, c+1, endR, endC, T-1, pasture);
		}
		return total;
	}
}
