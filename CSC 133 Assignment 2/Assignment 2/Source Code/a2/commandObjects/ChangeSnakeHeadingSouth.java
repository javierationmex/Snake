package a2.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a2.GameWorld;

public class ChangeSnakeHeadingSouth extends AbstractAction{

	private GameWorld gw;
	
	public ChangeSnakeHeadingSouth (){
		super ("Change Snake Heading south");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.changeSnakeHeading('s');
	}

	public void setTarget(GameWorld gameWorld) {
		// TODO Auto-generated method stub
		gw = gameWorld;
	}

}
