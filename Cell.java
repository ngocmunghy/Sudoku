
public class Cell {

	private int number;
	private boolean fixed;

	public Cell () {
		fixed = false;
	}

	public Cell (int number, boolean fixed) {
		this.number = number;
		this.fixed  = fixed;
	}

	public int getNumber () {
		return this.number;
	}

	public void setNumber (int number) {
		this.number = number;
	}

	public boolean isEmpty () {
		return this.number == 0;
	}

	public boolean isFixed () {
		return this.fixed;
	}

	public void setFixed (boolean fixed) {
		this.fixed = fixed;
	}

	public String toString () {
		return "" + this.number;
	}

	public boolean equals (Cell c) {
		return this.number == c.number;
	}

	public boolean equals (int number) {
		return this.number == number;
	}
}