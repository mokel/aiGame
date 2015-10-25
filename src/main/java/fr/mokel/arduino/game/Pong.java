package fr.mokel.arduino.game;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Pong extends JFrame {

	static Pong g;
	private Board board;
	
	public static void main(String[] args) {
		try {
			g = new Pong();
			g.setUp();
			PongEngine eng = new PongEngine(g.getBoard());
			eng.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pong() {
		setLayout(new GridBagLayout());
		board = new Board();
		board.setPreferredSize(new Dimension(Board.WIDTH,Board.HEIGHT));
		GridBagConstraints gdcCan = new GridBagConstraints();
		gdcCan.gridx=0;gdcCan.gridy=0;
		add(board,gdcCan);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				board.keyPressed(e);
			}
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setUp() {
		board.setKeyboardMode(true);
	}

}
