package a4.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a4.GameWorld;

public class SnakeHitWallCommand extends AbstractAction {

	private GameWorld gw;
	
	public SnakeHitWallCommand(){
		super("Snake collided with a wall");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		gw.collisionWithWall();
        //gw.getGameTimer().stop();
        System.out.println("Snake Hit Wall");
		
	}

	public void setTarget(GameWorld gameWorld) {
		// TODO Auto-generated method stub
		gw = gameWorld;
	}

}
