package knightstour;

public class KnightBoard {
	private int[][] board;
	private int[][] movesBoard;
	private int[][] knightMoves={{2,1}, {2,-1}, {-2,1}, {-2,-1},
			{1,2}, {1,-2}, {-1,2}, {-1,-2}};
	
	public KnightBoard (int rows, int cols){
		board = new int[rows][cols];
		movesBoard = new int[rows][cols];
		updateMovesBoard();
	}
	
	private void updateMovesBoard(){
		movesBoard = new int[movesBoard.length][movesBoard[0].length];
		for (int x = 0; x<board.length; x++){
			for (int y = 0; y<board[0].length; y++){
				if (board[x][y]==0){
					for (int m=0; m<knightMoves.length; m++){
						if (x+knightMoves[m][0]<board.length && x+knightMoves[m][0]>=0
								&& y+knightMoves[m][1]<board[0].length && y+knightMoves[m][1]>=0
								&& board[x+knightMoves[m][0]][y+knightMoves[m][1]]==0){
							movesBoard[x][y]++;
						}
					}
				}
			}
		}
	}
	
	public boolean solve(){
		for (int x = 0; x<board.length; x++){
			for (int y = 0; y<board[0].length; y++){
				if (solver(x, y, 1)){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean solver(int row, int col, int level){
		board[row][col]=level;
		if(level==board.length*board[0].length){
			return true;
		}
		for (int m=0; m<knightMoves.length; m++){
			if (row+knightMoves[m][0]<board.length && row+knightMoves[m][0]>=0
					&& col+knightMoves[m][1]<board[0].length && col+knightMoves[m][1]>=0
					&& board[row+knightMoves[m][0]][col+knightMoves[m][1]]==0){
				if (solver(row+knightMoves[m][0], col+knightMoves[m][1], level+1)){
					return true;
				}
			}
		}
		//System.out.println(this);
		board[row][col]=0;
		return false;
	}
	
	private boolean solver2(int row, int col, int level){
		board[row][col]=level;
		if(level==board.length*board[0].length){
			return true;
		}
		int[] optimizer = {9,9,9,9,9,9,9,9};
		for (int m=0; m<knightMoves.length; m++){
			if (row+knightMoves[m][0]<board.length && row+knightMoves[m][0]>=0
					&& col+knightMoves[m][1]<board[0].length && col+knightMoves[m][1]>=0
					&& board[row+knightMoves[m][0]][col+knightMoves[m][1]]==0){
				optimizer[m]=movesBoard[row+knightMoves[m][0]][col+knightMoves[m][1]];
			}
		}
		int[] testOrder = {0,1,2,3,4,5,6,7};
		for (int i = 0; i<8; i++){
			for (int n = i; n<8; n++){
				if (optimizer[testOrder[n]] < optimizer[testOrder[i]]){
					int temp=testOrder[n];
					testOrder[n]=testOrder[i];
					testOrder[i]=temp;
				}
			}
		}
		for (int i = 0; i<testOrder.length; i++){
			if (optimizer[testOrder[i]]<9){
				if (optimizer[testOrder[i]]>0 || level==board.length*board[0].length-1)
				if(solver2(row+knightMoves[testOrder[i]][0], col+knightMoves[testOrder[i]][1], level+1)){
					return true;
				}
			}
		}
		board[row][col]=0;
		return false;
	}
	
	public String toString(){
		String s = "";
		for (int x = 0; x<board.length; x++){
			for (int y = 0; y<board[0].length; y++){
				s+=" ";
				if (board[x][y]<10 && (board.length*board[0].length)>10){
					s+=" ";
				}
				if (board[x][y]<100 && (board.length*board[0].length)>100){
					s+=" ";
				}
				s+=board[x][y];
			}
			s+="\n";
		}
		return s;
	}
	
	public String movesBoardTest(){
		String s = "";
		for (int x = 0; x<board.length; x++){
			for (int y = 0; y<board[0].length; y++){
				s+=" ";
				s+=movesBoard[x][y];
			}
			s+="\n";
		}
		return s;
	}
}