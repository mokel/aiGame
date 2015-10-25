package fr.mokel.arduino.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player implements IDrawable{

	private Rectangle pos;
	private static int WIDTH = 7;
	private static int HEIGHT = 100;
	static int ACC = 4;
	private PLAYER player;
	private int x = 0;
	
	public Player(PLAYER player) {
		pos = new Rectangle(x, Board.HEIGHT/2, WIDTH, HEIGHT);
		this.player = player;
		x = player == PLAYER.ONE ? 0 : Board.WIDTH - Player.WIDTH;
	}

	public void draw(Graphics g) {
		
		g.setColor(Color.ORANGE);
		g.fillRect(x, Double.valueOf(pos.getY()).intValue(), WIDTH, HEIGHT);
	}
	
	public void setY(int y) {
		int oldY = Double.valueOf(pos.getY()).intValue()/ACC;
		if(oldY == y-1 || oldY == y +1 || oldY == y){
//			System.out.println("SAME");
		} else {
			pos.setFrame(x, y*ACC, WIDTH, HEIGHT);
		}
	}

	public static enum PLAYER {
		ONE,TWO,NULL;
	}

	public boolean isCollided(Ball b) {
		return pos.intersects(b.getPosition());
	}

	public void moveUp() {
		double oldY = pos.getY();
		double y = oldY - ACC*7;
		pos.setFrame(x, y, WIDTH, HEIGHT);
	}

	public void moveDown() {
		double oldY = pos.getY();
		double y = oldY + ACC*7;
		pos.setFrame(x, y, WIDTH, HEIGHT);
	}
}
