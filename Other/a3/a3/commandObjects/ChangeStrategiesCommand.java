package a3.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a3.GameWorld;

public class ChangeStrategiesCommand extends AbstractAction{

	private GameWorld gw;
	
	public ChangeStrategiesCommand(){
		super("Change Strategy");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.changeStrategies();
	}

	public void setTarget(GameWorld gameWorld) {
		// TODO Auto-generated method stub
		gw = gameWorld;
	}

}
