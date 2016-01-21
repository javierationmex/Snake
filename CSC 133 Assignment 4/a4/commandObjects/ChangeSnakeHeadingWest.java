package a4.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a4.GameWorld;

public class ChangeSnakeHeadingWest extends AbstractAction{

	private GameWorld gw;
	
	public ChangeSnakeHeadingWest(){
		super ("Change Snake Heading west");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.changeSnakeHeading('w');
		
	}

	public void setTarget(GameWorld gameWorld) {
		// TODO Auto-generated method stub
		gw = gameWorld;
	}

}
