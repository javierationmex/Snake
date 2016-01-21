package a4.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a4.GameWorld;

public class WeaselHitSnakeCommand extends AbstractAction {

	private GameWorld gw;
	
	public WeaselHitSnakeCommand(){
		super("Weasel eats snake");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.collisionWithWeasel();
        System.out.println("Snake Hit Weasel");
		
	}

	public void setTarget(GameWorld gameWorld) {
		gw = gameWorld;
		
	}

}
