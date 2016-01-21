package a4.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a4.GameWorld;

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
		
		//gw.collisionWithMoney();
		
	}

}
