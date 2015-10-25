package fr.mokel.arduino.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import fr.mokel.arduino.game.Player.PLAYER;

public class Board extends JPanel implements Observer {

	private Player p1;
	private Player p2;
	private Ball b;
	static int WIDTH = 500;
	static int HEIGHT = 500;
	private String info = null;
	
	public Board() {
		setBackground(Color.DARK_GRAY);
		p1 = new Player(PLAYER.ONE);
		p2 = new Player(PLAYER.TWO);
		b = new Ball();
//		addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if(e.getKeyCode() == KeyEvent.VK_SPACE && info != null) {
//					System.out.println("starting new game");
//					info = null;
//					initGame();
//					synchronized (this) {
//						notifyAll();
//					}
//				}
//			}
//		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		p1.draw(g);
		p2.draw(g);
		b.draw(g);
		if(info != null) {
			g.drawString(info, 100, 100);
		}
	}
	
	public void update(Observable o, Object arg) {
		try {
			String position = (String)arg;
			if(position != null && position.length() > 2 && position.indexOf('-') > 0) {
				String[] split = position.split("-");
				if(split != null && split.length == 2) {
					int int1 = Integer.valueOf(split[0]);
					int int2 = Integer.valueOf(split[1]);
//					System.out.println("Position : " +split[0] + " -- "+split[1]);
					p1.setY(int1);
					p2.setY(int2);
//					repaint();
//					Pong.g.repaint();
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void moveBall() {
		b.step();
	}

	public void frame() {
		Pong.g.repaint();
	}

	public PLAYER checkGame() {
		PLAYER p = b.isOnEdge();
		if( p != null) {
			b.bounce();
			switch (p) {
			case ONE:
				return (!p1.isCollided(b)) ? PLAYER.ONE : null;
			case TWO:
				return (!p2.isCollided(b)) ? PLAYER.TWO : null;
			}
		}
		return null;
	}

	public void enterFinalState(PLAYER p) {
		info = "Player " + p + " looses";
		frame();
	}

	public void initGame() {
		b.init();
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE && info != null) {
			System.out.println("starting new game");
			info = null;
			initGame();
			synchronized (this) {
				notifyAll();
			}
		} else if(e.getKeyCode() == KeyEvent.VK_UP) {
			p2.moveUp();
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.moveDown();
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			p1.moveUp();
		} else if(e.getKeyCode() == KeyEvent.VK_X) {
			p1.moveDown();
		}
		
	}

	private boolean keyBoardMode = false;
	public void setKeyboardMode(boolean c) {
		keyBoardMode = c;
	}

}
