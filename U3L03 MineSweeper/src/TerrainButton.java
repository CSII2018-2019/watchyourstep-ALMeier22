import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class TerrainButton extends JButton {

	static final int SIZE = 50;
	
	private int row = 0;
	private int col = 0;
	private int nextToHoles = 0;
	private boolean hole = false;
	private boolean revealed = false;
	
	public TerrainButton(int row, int col) {
		this.row = row;
		this.col = col;
		Dimension size = new Dimension(SIZE,SIZE);
		setPreferredSize(size);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean hasHole() {
		return hole;
	}
	
	public boolean isRevealed() {
		return revealed;
	}
	
	public void setHole(boolean hasHole) {
		hole = hasHole;
	}
	
	public void increaseHoleCount() {
		nextToHoles = nextToHoles + 1;
	}
	
	public boolean isNextToHoles() {
		return nextToHoles > 0;
	}
	
	public void reveal(boolean reveal) {
		revealed = reveal;
		if(revealed) {
			if(hasHole()) {
				setBackground(Color.black);
			}
			else {
				setBackground(Color.cyan);				
			}
			if(nextToHoles > 0) {
				setText("" + nextToHoles);
			}
			else {
				setText("");
			}
		}
		else {
			setBackground(null);
			setText("");
		}
		setFocusPainted(reveal);
	}
	
	public void reset() {
		nextToHoles = 0;
		hole = false;
		revealed = false;
		setText("");
		setBackground(null);
	}
	
	
}
