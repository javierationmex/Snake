package a2.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class UndoCommand extends AbstractAction {

	public UndoCommand(){
		super("Undo");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Undo requested from " + e.getActionCommand() + " " + e.getClass());
		
	}

}
