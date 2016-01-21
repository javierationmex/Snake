package a2.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a2.GameWorld;

public class BirdHitSnakeCommand extends AbstractAction{

	private GameWorld gw;
	
	public BirdHitSnakeCommand(){
		super("Bird eats the Snake");
	}
	
	public void setTarget(GameWorld gameWorld){
		gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.collisionWithBird();
		
	}

}
