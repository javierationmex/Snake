package a2.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a2.GameWorld;

public class SnakeHitWallCommand extends AbstractAction {

	private GameWorld gw;
	
	public SnakeHitWallCommand(){
		super("Snake collided with a wall");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		gw.collisionWithWall();
		
	}

	public void setTarget(GameWorld gameWorld) {
		// TODO Auto-generated method stub
		gw = gameWorld;
	}

}
