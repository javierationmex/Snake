package a3.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class QuitCommand extends AbstractAction {

	public QuitCommand (){
		super("Quit");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int result = JOptionPane.showConfirmDialog
				(null, 
				"Are you sure you want to exit ?",
				"Confirm Exit",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		
		if (result == JOptionPane.YES_OPTION){
			System.exit(0);
		}
		return;
		
	}

}
