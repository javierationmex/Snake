package a4.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a4.GameWorld;

public class SnakeHitBodyCommand extends AbstractAction{

	private GameWorld gw;
	
	public SnakeHitBodyCommand(){
		super("Snake Collided with its body");
	}
	
	public void setTarget(GameWorld gameWorld){
		gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.collisionWithBody();
		System.out.println("Snake Hit Body");
	}

}
