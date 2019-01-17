import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class Minesweeper extends JFrame {

	
	
		
		
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
