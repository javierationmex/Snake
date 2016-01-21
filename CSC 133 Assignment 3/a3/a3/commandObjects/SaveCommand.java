package a3.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class SaveCommand extends AbstractAction {

	public SaveCommand(){
		super ("Save");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Save requested from " + e.getActionCommand() + " " + e.getClass());
		
	}

}
