public class SudokuSolver {
	
	int board[][] = new int[9][9];
	
	public SudokuSolver(int[][] board) {
		this.board = board;
	}
	
	public void printBoard() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0 && j != 0) {
					System.out.print("| " + board[i][j] + " ");
				} else {
					System.out.print(board[i][j] + " ");
				}
			}
			if (i % 3 == 2 && i != 8) {
				System.out.println("\n--------------------");
			} else {
				System.out.println();
			}
		}
	}
	
	public int[] nextEmptySpace() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == 0) {
					int[] ret = {i,j};
					return ret;
				}
			}
		}
		return null;
	}
	
	public boolean checkValid(int num, int[] pos) {
		
		// check if row is valid
		for (int i = 0; i < 9; i++) {
			if (board[pos[0]][i] == num && i != pos[1]) {
				return false;
			}
		}
		
		// check if column is valid
		for (int i = 0; i < 9; i++) {
			if (board[i][pos[1]] == num && i != pos[0]) {
				return false;
			}
		}
		
		// check if subgrid is valid
		int box_x = pos[1] / 3;
		int box_y = pos[0] / 3;
		for (int i = box_y * 3; i < box_y * 3 + 3; i++) {
			for (int j = box_x * 3; j < box_x * 3 + 3; j++) {
				if (board[i][j] == num && pos[0] != i && pos[1] != j) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean solve() {
		int empty[] = nextEmptySpace();
		if (empty == null) {
			return true;
		}
		
		int row = empty[0];
		int col = empty[1];
		
		for (int i = 1; i < 10; i++) {
			if (checkValid(i, empty)) {
				board[row][col] = i;
				
				if (solve()) {
					return true;
				}
				
				board[row][col] = 0;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		// example board
		int board[][] = {
	 		{7,8,0,4,0,0,1,2,0},
	 		{6,0,0,0,7,5,0,0,9},
			{0,0,0,6,0,1,0,7,8},
			{0,0,7,0,4,0,2,6,0},
			{0,0,1,0,5,0,9,3,0},
			{9,0,4,0,6,0,0,0,5},
			{0,7,0,3,0,0,0,1,2},
			{1,2,0,0,0,7,4,0,0},
			{0,4,9,2,0,6,0,0,7}
		    };
		
		SudokuSolver solver = new SudokuSolver(board);
		solver.printBoard();
		solver.solve();
		System.out.println("\n\n\n\n");
		solver.printBoard();
	}
	
}
