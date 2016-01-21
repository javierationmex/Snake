package a4.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;

import a4.GameWorld;

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
        
        gw.setSound(selected);
        gw.notifyObservers();
        if (selected){
            if (!gw.isPaused())
            gw.playBackgroundMusic();
        } else {
            gw.stopBackgroundMusic();
        }

		
	}

}
