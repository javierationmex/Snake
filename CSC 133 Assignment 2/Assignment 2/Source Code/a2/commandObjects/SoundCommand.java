package a2.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;

import a2.GameWorld;

public class SoundCommand extends AbstractAction {

	private GameWorld gw;
	
	public SoundCommand(){
		super("Sound");
	}
	
	public void setTarget(GameWorld gameWorld){
		gw = gameWorld;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		AbstractButton abstractButton = (AbstractButton) e.getSource();
        boolean selected = abstractButton.getModel().isSelected();
        System.out.println(selected);
        
        gw.setSound(selected);
        gw.notifyObservers();
		
	}

}
