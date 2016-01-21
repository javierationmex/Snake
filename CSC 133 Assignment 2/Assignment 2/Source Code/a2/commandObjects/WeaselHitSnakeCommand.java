package a2.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a2.GameWorld;

public class WeaselHitSnakeCommand extends AbstractAction {

	private GameWorld gw;
	
	public WeaselHitSnakeCommand(){
		super("Weasel eats snake");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.collisionWithWeasel();
		
	}

	public void setTarget(GameWorld gameWorld) {
		gw = gameWorld;
		
	}

}
