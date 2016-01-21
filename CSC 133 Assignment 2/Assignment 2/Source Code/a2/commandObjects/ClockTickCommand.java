package a2.commandObjects;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;

import a2.GameWorld;
import a2.gameObjects.Fixed;
import a2.gameObjects.GameObject;

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
