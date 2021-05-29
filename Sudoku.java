import javax.swing.JOptionPane;

public class Sudoku {

	private Board board;
	private Board solution;
	private int level;

	public Sudoku (int level) {
		this.level = level;
		generateBoard();
		getSolution();
	}

	public Board getBoard () {
		return this.board;
	}

	public void generateBoard() {
		Generator generator = new Generator();
		generator.generateBoard(this.level);
		this.board = generator.getBoard();		
	}

	private void getSolution() {
		Board solveBoard = new Board();
		solveBoard.copy(this.board);
		solveBoard.reset();
		Solver solver = new Solver(solveBoard);
		solver.solveBoard();
		this.solution = solver.getBoard();
	}		

	public boolean[][] checkBoard() {
		Board checkBoard = new Board();
		checkBoard.copy(this.board);
		checkBoard.reset();

		Solver solver = new Solver(checkBoard);
		solver.solveBoard();
		checkBoard = solver.getBoard();

		return checkBoard.compare(this.board);
	}	

	public boolean isSolved() {	
		return this.board.equals(this.solution);
	}

	public void solveBoard() {
		this.board = this.solution;
	}		
}
