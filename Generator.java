
import java.util.Random;

public class Generator {

	private Board board = new Board();
	private Board srcBoard = new Board(); //srcBoard is a board to backtrack to if something goes wrong

	public Generator () {
	}

	public Board getBoard () {
		return this.board;
	}

	public void generateBoard (int diff) {
		initBoard();
		Random random = new Random();
		if (diff == 1) {
			dig(30 + random.nextInt(10));
		} else if (diff == 2) {
			dig(40 + random.nextInt(10));
		} else {
			dig(50 + random.nextInt(10));
		}
	}

	private void initBoard () {
		int i, j, n;

		do {
			board.clear();
			for (int k = 0; k < 80; k++) {
				i = random();
				j = random();
				n = random() + 1;

				if (!board.contains(i, j, n)) {
					board.setCell(i,j,n,true);
				}
			}
		} while (!solvable());

		srcBoard.copy(board);
	}
	
	private int random () {
		Random rand = new Random();
		return rand.nextInt(9);
	}

	private boolean solvable () {
		Solver solver = new Solver(board, true);
		return solver.solveBoard();
	}

	private boolean multSol () {
		int n = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (srcBoard.getCell(i,j) != null && srcBoard.getCell(i,j).equals(0)) {
					//if a cell in the original puzzle is empty
					for (int k = 1; k < 10; k++) {
						//loop through all possible numbers for this cell
						board.copy(srcBoard);
						board.setCell(i,j,k, true);
						if (!board.contains(i, j, k) && solvable()) {
							//count all possible solutions for numbers in this cell
							n++;
						}
					}

					if (n > 1) {
						//if there is more than one possible solution, return true
						return true;
					}
					board.setCell(i,j,0,false);
				}
			}
		}
		//if after testing all possible numbers for all empty cells there is not more than one solution, return false
		return false;
	}
	
	private void dig (int n) {
		int i, j, k, dug = 0;
		while (dug < n) {
			i = random();
			j = random();
			if (board.getCell(i,j) == null || !board.getCell(i,j).equals(0)) {
				if (board.getCell(i,j) == null) {
					k = 0;
				} else {
					k = board.getCell(i,j).getNumber();
				}
				board.setCell(i, j, 0, false);
				if (!multSol()) {
					dug++;
				} else {
					board.setCell(i, j, k, true);
				}
			}
		}
	}
}