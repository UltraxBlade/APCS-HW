
public class QueenBoard {
	private int[][] board;
	private int numSolutions;
	
	public QueenBoard (int n){
		board = new int[n][n];
		numSolutions = -1;
	}
	
	public void solve(){
		solver(0);
	}
	
	private boolean solver(int row){
		for (int i = 0; i<board.length; i++){
			if (board[row][i]==0){
				addQueen(row, i);
				if (row==board.length-1){
					return true;
				} else {
					if(!solver(row + 1)){
						removeQueen(row, i);
					} else {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void countSolutions(){
		numSolutions=0;
		csolver(0);
	}
	
	private void csolver(int row){
		for (int i = 0; i<board.length; i++){
			if (board[row][i]==0){
				addQueen(row, i);
				if (row==board.length-1){
					numSolutions++;
				} else {
					csolver(row+1);
				}
				removeQueen(row, i);
			}
		}
	}
	
	public int getSolutionCount(){
		return numSolutions;
	}
	
	private void addQueen(int row, int col){
		for (int i = 0; i<board.length; i++){
			board[row][i]++;
		}
		for (int i = 0; i<board.length; i++){
			board[i][col]++;
		}
		for (int i = 0; (i<(board.length - row)) && (i<(board.length-col));i++){
			board[row+i][col+i]++;
		}
		for (int i = 0; (i<=row) && (i<=col);i++){
			board[row-i][col-i]++;
		}
		for (int i = 0; (i<(board.length - row)) && (i<=col);i++){
			board[row+i][col-i]++;
		}
		for (int i = 0; (i<=row) && (i<(board.length-col));i++){
			board[row-i][col+i]++;
		}
		board[row][col]=-1;
	}
	
	private void removeQueen(int row, int col){
		for (int i = 0; i<board.length; i++){
			board[row][i]--;
		}
		for (int i = 0; i<board.length; i++){
			board[i][col]--;
		}
		for (int i = 0; (i<(board.length - row)) && (i<(board.length-col));i++){
			board[row+i][col+i]--;
		}
		for (int i = 0; (i<=row) && (i<=col);i++){
			board[row-i][col-i]--;
		}
		for (int i = 0; (i<(board.length - row)) && (i<=col);i++){
			board[row+i][col-i]--;
		}
		for (int i = 0; (i<=row) && (i<(board.length-col));i++){
			board[row-i][col+i]--;
		}
		board[row][col]=0;
	}
	
	public String toString(){
		String s = "";
		for (int x = 0; x<board.length; x++){
			for (int y = 0; y<board.length; y++){
				if(board[x][y]==-1){
					s+="Q ";
				} else {
					s+="- ";
				}
			}
			s+="\n";
		}
		return s;
	}
}
