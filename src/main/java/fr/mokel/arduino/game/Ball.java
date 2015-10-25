package fr.mokel.arduino.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Date;
import java.util.Random;

import fr.mokel.arduino.game.Player.PLAYER;

public class Ball implements IDrawable {

	private static int SIZE = 20;
	
	private Rectangle position;
	private Random rand;
	
	private int x_move;
	private int y_move;
	private int way;
	
	public Ball() {
		rand = new Random(new Date().getTime());
		init();
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval(Double.valueOf(position.getX()).intValue(), Double.valueOf(position.getY()).intValue(), SIZE, SIZE);
	}

	public Rectangle getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position.setFrame(position.x, position.y, SIZE, SIZE);
	}

	public void step() {
		double x = position.getX() + x_move;
		double y = position.getY() + y_move;
		if(y<0 || y > Board.HEIGHT-SIZE) {
			y_move *= -1;
		}
		if(x<0 || x > Board.WIDTH-SIZE) {
//			x = 0;
		}
		
		this.position.setFrame(x, y, SIZE, SIZE);
	}
	
	public void init(){
		position = new Rectangle(Board.WIDTH/2, Board.HEIGHT/2, SIZE, SIZE);
		
		way = rand.nextBoolean() ? -1:1;
		x_move = rand.nextInt(3)*2+4;
		x_move *= way;
		y_move = rand.nextInt(3)*2+1;
	}

	public PLAYER isOnEdge() {
		if (position.getX() < 0) {
			return PLAYER.ONE;
		}
		if (position.getX() > Board.WIDTH-SIZE) {
			return PLAYER.TWO;
		}
		return null;
	}

	public void bounce() {
		if (position.getX() < 0) {
			position.setLocation(0, Double.valueOf(position.getY()).intValue());
		}
		if (position.getX() > Board.WIDTH-SIZE) {
			position.setLocation(Board.WIDTH-SIZE, Double.valueOf(position.getY()).intValue());
		}
		way *= -1;
		x_move = rand.nextInt(3)*2+4;
		x_move *= way;
		y_move = rand.nextInt(3)*2+1;
	}

}
