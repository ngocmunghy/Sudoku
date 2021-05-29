
public class Board {

	private Cell[][] board = new Cell[9][9];

	public Board () {
	}

	public Cell getCell (int i, int j) {
		return board[i][j];
	}

	public void setCell (int i, int j, Cell c) {
		board[i][j] = c;
	}

	public void setCell (int i, int j, int number, boolean fixed) {
		if (board[i][j] == null) {
			board[i][j] = new Cell (number, fixed);
		} else {
			board[i][j].setNumber(number);
			board[i][j].setFixed(fixed);
		}
	}

	public Cell[] getCol (int j) {
		Cell[] col = new Cell[9];

		for (int i = 0; i < 9; i++) {
			col[i] = board[i][j];
		}
		return col;
	}

	public Cell[] getRow (int i) {
		return board[i];
	}

	public Cell[] getBlock (int i, int j) {
		//could use improvement
		Cell[] block = new Cell[9];

		i = i - (i % 3);
		j = j - (j % 3);
		int b = 0;

		for (int n = i; n < i+3; n++) {
			for (int m = j; m < j+3; m++) {
				block[b] = board[n][m];
				b++;
			}
		}
		return block;
	}

	public boolean contains(int i, int j, int n) {
		Cell[] col = getCol(j);
		Cell[] row = getRow(i);
		Cell[] block = getBlock(i, j);

		if(contains(col, n) || contains(row, n) || contains(block, n)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean contains (Cell[] array, int n) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && array[i].equals(n)) {
				return true;
			}
		}
		return false;
	}

	public void printBoard () {
		System.out.println("-------------------");
		for (int i = 0; i < 9; i++) {
			System.out.print("|");
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == null || board[i][j].equals(0)) {
					System.out.print("*");
				} else {
					System.out.print(board[i][j]);
				}
				if (j % 3 == 2) {
					System.out.print("|");
				} else {
					System.out.print(" ");
				}
			}
			if (i % 3 == 2) {
				System.out.println("\n-------------------");
			} else {
				System.out.println();
			}
		}
	}

	public void copy (Board board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.board[i][j] = board.getCell(i,j);
			}
		}
	}

	public void clear () {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.board[i][j] = new Cell(0, false);
			}
		}
	}

	public void reset () {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!getCell(i, j).isFixed()) {
					this.board[i][j] = new Cell(0, false);
				}
			}
		}
	}

	public boolean[][] compare (Board compBoard) {
		boolean[][] bools = new boolean[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (getCell(i, j).equals(compBoard.getCell(i, j))) {
					bools[i][j] = true;
				} else {
					bools[i][j] = false;
				}
			}
		}
		return bools;
	}

	public boolean equals (Board board) {
		boolean[][] matchMatrix = this.compare(board);
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!matchMatrix[i][j]) {
					return false;
				}
			}
		}
		return true;			
	}
}