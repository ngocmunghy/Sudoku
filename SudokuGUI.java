import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuGUI extends JFrame {
	
	private Grid grid;
	private JButton solve, check, generate;
	private JCheckBoxMenuItem itemEasy, itemMedium, itemHard;
	private int level;

	public SudokuGUI () {
		setLevel(1);
		initWindow();
	}

	public void setLevel (int level) {
		this.level = level;
		if (itemEasy != null) itemEasy.setSelected(this.level == 1);
		if (itemMedium != null) itemMedium.setSelected(this.level == 2);
		if (itemHard != null) itemHard.setSelected(this.level == 3);
	}

	public void initWindow () {
		setTitle("Sudoku");
		setSize(450, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		drawMenu();
		drawPanel();
		setVisible(true);
	}

	public void drawMenu () {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuMenu = new JMenu("Menu");
		menuMenu.setMnemonic(KeyEvent.VK_M);
		JMenuItem itemNew = new JMenuItem("New");
		itemNew.setSelected(true);
		itemNew.setMnemonic(KeyEvent.VK_N);
		itemNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateBoard();
			}
		});
		menuMenu.add(itemNew);
		JMenuItem itemCheck = new JMenuItem("Check");
		itemCheck.setMnemonic(KeyEvent.VK_C);
		itemCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBoard();
			}
		});
		menuMenu.add(itemCheck);		
		JMenuItem itemSolve = new JMenuItem("Solve");
		itemSolve.setMnemonic(KeyEvent.VK_S);
		itemSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solveBoard();
			}
		});
		menuMenu.add(itemSolve);
		menuMenu.addSeparator();
		JMenuItem itemExit = new JMenuItem("Exit");
		itemExit.setMnemonic(KeyEvent.VK_E);
		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		menuMenu.add(itemExit);	
		menuBar.add(menuMenu);
		
		JMenu menuLevel = new JMenu("Level");
		menuLevel.setMnemonic(KeyEvent.VK_L);
		this.itemEasy = new JCheckBoxMenuItem("Easy");
		this.itemEasy.setMnemonic(KeyEvent.VK_E);
		this.itemEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLevel(1);
			}
		});		
		menuLevel.add(itemEasy);
		this.itemMedium = new JCheckBoxMenuItem("Medium");
		this.itemMedium.setMnemonic(KeyEvent.VK_M);
		this.itemMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLevel(2);
			}
		});		
		menuLevel.add(itemMedium);		
		this.itemHard = new JCheckBoxMenuItem("Hard");
		this.itemHard.setMnemonic(KeyEvent.VK_H);
		this.itemHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLevel(3);
			}
		});		
		menuLevel.add(itemHard);
		this.setLevel(this.level);
		menuBar.add(menuLevel);
		
		this.setJMenuBar(menuBar);
	}

	public void drawPanel () {
		setLayout(new BorderLayout(0, 40));
	
		this.grid = new Grid(this.level);
		add(this.grid, BorderLayout.CENTER);
		
		this.generate = new JButton("Generate"); 		
		this.generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateBoard();
			}
		});

		this.check = new JButton ("Check");
		this.check.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e) {
				checkBoard();
			}
		});

		this.solve = new JButton("Solve");
		this.solve.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e) {
				solveBoard();
			}
		});
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 3, 20, 20));
		buttons.add(this.generate);
		buttons.add(this.check);
		buttons.add(this.solve);		
		add(buttons, BorderLayout.SOUTH);
	}

	private void solveBoard() {
		this.grid.getSudoku().solveBoard();
		this.grid.refreshGrid();
	}

	private void checkBoard() {
		if (this.grid.getSudoku().isSolved()) {
			JOptionPane.showMessageDialog(null, "All correct! Congratulations!");
		} else {
			this.grid.highlightCells(this.grid.getSudoku().checkBoard());
		}
	}

	private void generateBoard () {
		this.grid.setSudoku(new Sudoku(this.level));
	}
	
	public static void main (String[] args) {
		new SudokuGUI();
	}
}
