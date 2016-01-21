package a2.commandObjects;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;

import a2.GameWorld;
import a2.gameObjects.GameObject;
import a2.gameObjects.Money;

public class SnakeHitMoneyCommand extends AbstractAction {

	private GameWorld gw;
	
	public SnakeHitMoneyCommand(){
		super("Snake collided with money");
	}
	
	public void setTarget(GameWorld gameWorld){
		gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		gw.collisionWithMoney();
		
	}

}
