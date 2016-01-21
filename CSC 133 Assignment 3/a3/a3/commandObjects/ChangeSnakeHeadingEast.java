package a3.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a3.GameWorld;

public class ChangeSnakeHeadingEast extends AbstractAction{

	private GameWorld gw;
	
	public ChangeSnakeHeadingEast(){
		super ("Change Snake Heading east");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.changeSnakeHeading('e');
	}

	public void setTarget(GameWorld gameWorld) {
		// TODO Auto-generated method stub
		gw = gameWorld;
	}

}
