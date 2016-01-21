package a4.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a4.GameWorld;

public class ClockTickCommand extends AbstractAction{
	
	private GameWorld gw;
	
	public ClockTickCommand(){
		super("Clock Tick");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		gw.gameClockTick();
		
	}

	public void setTarget(GameWorld gameWorld) {
		// TODO Auto-generated method stub
		gw = gameWorld;
	}

}
