
import java.awt.*;
import javax.swing.*;

public class Grid extends JPanel {

	private Sudoku sudoku;
	private GridCell[][] gridCells = new GridCell[9][9];

	public Grid (int level) {
		this.sudoku = new Sudoku(level);
		this.initGrid();
	}

	public void setSudoku (Sudoku sudoku) {
		this.sudoku = sudoku;
		this.refreshGrid();
	}
	
	public Sudoku getSudoku () {
		return this.sudoku;
	}

	public void refreshGrid () {
		this.drawGridCells();
		this.updateUI();
	}

	public void updateBoardCell (int row, int column, int number) {
		this.sudoku.getBoard().setCell(row, column, number, false);
		this.sudoku.getBoard().printBoard();
	}

	private void initGrid () {
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout (9, 9));
		this.drawGridCells();
	}

	private void drawGridCells () {
		this.removeAll();
		for (int i = 0; i < 9; i ++) {
			for (int j = 0; j < 9; j++) {
				this.gridCells[i][j] = new GridCell(this, i, j, this.sudoku.getBoard().getCell(i, j));
				this.add(this.gridCells[i][j]);
			}
		}		
	}

	public void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setBackground(Color.white);
		super.paintComponent(g2);
	
		int width = getSize().width;
		int height = getSize().height;

		for (int i = 1; i < 10; i++) {
			if (i % 3 == 0) {
				g2.setStroke(new BasicStroke(3));
				g2.drawLine(i * width/9, 0, i * width/9, height);
				g2.drawLine(0, i * height/9, width, i * height/9);
				g2.setStroke(new BasicStroke(1));
			} else {
				g2.drawLine(i * width/9, 0, i * width/9, height);
				g2.drawLine(0, i * height/9, width, i * height/9);
			}
		}
	}

	public void highlightCells (boolean[][] checkMatrix) {
		for (int i = 0; i < 9; i ++) {
			for (int j = 0; j < 9; j++) {
				this.gridCells[i][j].highlightCell(checkMatrix[i][j]);
			}
		}
	}
}