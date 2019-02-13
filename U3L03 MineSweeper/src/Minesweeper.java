import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Minesweeper extends JFrame {

	private static final int GRIDSIZE = 10;
	private static final int NUMBEROFHOLES = 10;
	private TerrainButton[][] terrain = new TerrainButton[GRIDSIZE][GRIDSIZE];
	private int totalRevealed = 0;
		
		
	public Minesweeper(){	
		
		setPreferredSize(new Dimension(800,220));

		initGUI();
			
		setTitle("Minesweeper");
		setResizable(false);
		pack(); //pack means pack tightly -- override the sizes 
		setLocationRelativeTo(null); //centers on screen, do this after packing but before visible
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void initGUI(){
		
		// title label 
		JLabel titleLabel = new JLabel("Minesweeper");
		
		Font titlefount = new Font (Font.MONOSPACED, Font.BOLD + Font.ITALIC, 32);
		titleLabel.setFont(titlefount);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(new Color(47,102,191));;
		titleLabel.setOpaque(true);
		
		add(titleLabel,BorderLayout.PAGE_START);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(GRIDSIZE,GRIDSIZE));
		add(centerPanel,BorderLayout.CENTER);
		for(int r=0; r<GRIDSIZE; r++) {
			for(int c=0; c<GRIDSIZE; c++) {
				terrain[r][c] = new TerrainButton(r,c);	
				terrain[r][c].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						TerrainButton button = (TerrainButton)e.getSource();
						int row = button.getRow();
						int col = button.getCol();
						clickTerrain(row,col);
						
					}
				});
				centerPanel.add(terrain[r][c]);
			}
		}	
		setHoles();
	}
	
	private void clickTerrain(int row, int col) {
		if(terrain[row][col].hasHole()) {
			String message = "Ooops, you stepped on hole. Do you want to play again?";
			promptForNewGame(message);		
		}
		else {
			check(row,col);
			checkNeighbors(row,col);
			if(totalRevealed == GRIDSIZE*GRIDSIZE-NUMBEROFHOLES) {
				String message = "Good Job, you revealed all free tiles";
				promptForNewGame(message);
			}
		}
	}
	
	private void promptForNewGame(String message) {
		showHoles();
		int option = JOptionPane.showConfirmDialog(this, message,"Play Again?",JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			newGame();
		}
		else {
			System.exit(0);
		}
	}
	
	private void newGame() {
		for(int r=0;r<GRIDSIZE;r++) {
			for (int c=0;c<GRIDSIZE;c++) {
				terrain[r][c].reset();
			}
		}
		setHoles();
		totalRevealed = 0;
	}

	private void showHoles() {
		for(int r=0;r<GRIDSIZE;r++) {
			for (int c=0;c<GRIDSIZE;c++) {
				terrain[r][c].reveal(true);
			}
		}
	}

	private void check(int row, int col) {
		if(row > -1 && row < GRIDSIZE && col > -1 && col < GRIDSIZE 
				&& !terrain[row][col].hasHole() &&  !terrain[row][col].isRevealed()) {
			terrain[row][col].reveal(true);
			totalRevealed = totalRevealed + 1;
			if(!terrain[row][col].isNextToHoles()) {
				checkNeighbors(row,col);
			}
		}
	}
	
	private void checkNeighbors(int row, int col) {
		check(row-1,col-1);
		check(row-1,col  );
		check(row-1,col+1);		
		check(row  ,col-1);
		check(row  ,col+1);		
		check(row+1,col-1);
		check(row+1,col  );
		check(row+1,col+1);
	}
		
	private void setHoles() {
		Random rand = new Random();
		int pickRow;
		int pickCol;
		for(int i=0; i<NUMBEROFHOLES; i++) {
			do {
				pickRow = rand.nextInt(GRIDSIZE);
				pickCol = rand.nextInt(GRIDSIZE);				
			}
			while(terrain[pickRow][pickCol].hasHole());
			terrain[pickRow][pickCol].setHole(true);
			addToNeighborsHoleCount(pickRow,pickCol);
			//terrain[pickRow][pickCol].reveal(true);
		}
	}
	
	private void addToNeighborsHoleCount(int row, int col) {
		addToHoleCount(row-1,col-1);
		addToHoleCount(row-1,col  );
		addToHoleCount(row-1,col+1);		
		addToHoleCount(row  ,col-1);
		addToHoleCount(row  ,col+1);		
		addToHoleCount(row+1,col-1);
		addToHoleCount(row+1,col  );
		addToHoleCount(row+1,col+1);
	}
	
	private void addToHoleCount(int row, int col ) {
		if(row > -1 && row < GRIDSIZE && col > -1 && col < GRIDSIZE) {
			terrain[row][col].increaseHoleCount();
			//terrain[row][col].reveal(true);
		}
	}
	
	
	
	public static void main(String[] args) {
			try {
	            String className = UIManager.getCrossPlatformLookAndFeelClassName();
	            UIManager.setLookAndFeel(className);
	        } catch ( Exception e) {}
	        
	        EventQueue.invokeLater(new Runnable (){
	            @Override
	            public void run() {
	                new Minesweeper();
	            }   
	        });
	        
	       

		
	}

}
