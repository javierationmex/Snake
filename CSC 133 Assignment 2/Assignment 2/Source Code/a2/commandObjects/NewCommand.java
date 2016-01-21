package a2.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class NewCommand extends AbstractAction{

	public NewCommand(){
		super ("New");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("New requested from " + e.getActionCommand() + " " + e.getClass());
		
	}

}
