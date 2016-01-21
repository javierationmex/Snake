package a4.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a4.GameWorld;

public class ChangeSnakeHeadingNorth extends AbstractAction{

	private GameWorld gw;
	
	public ChangeSnakeHeadingNorth(){
		super("Change Snake Heading north");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.changeSnakeHeading('n');
	}

	public void setTarget(GameWorld gameWorld) {
		// TODO Auto-generated method stub
		gw = gameWorld;
	}

}