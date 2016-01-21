package a2.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a2.GameWorld;

public class SnakeEatsFoodCommand extends AbstractAction {

	private GameWorld gw;
	
	public SnakeEatsFoodCommand(){
		super("Snake eats Food");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		gw.collisionWithFood();
		
	}

	public void setTarget(GameWorld gameWorld) {
		// TODO Auto-generated method stub
		gw = gameWorld;
	}

}
