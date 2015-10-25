package fr.mokel.arduino.game;

import fr.mokel.arduino.game.Player.PLAYER;

public class PongEngine extends Thread {
	
	private Board board;
	
	public PongEngine(Board board) {
		this.board = board;
	}

	@Override
	public void run() {
		while(true) {
			PLAYER p = board.checkGame();
			if(p != null) {
				//looser
				System.out.println("LOOSER: " + p);
				board.enterFinalState(p);
				try {
					synchronized (board) {
						board.wait();
					}
					board.initGame();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				board.moveBall();
				board.frame();
			}
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
