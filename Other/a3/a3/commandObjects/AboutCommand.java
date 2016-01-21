package a3.commandObjects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class AboutCommand extends AbstractAction{
	
	public AboutCommand(){
		super("About");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JOptionPane.showMessageDialog(null,
		        "Javier Garrido\nCSC 133\nSnake Version Number: 2",
		        "Information",
		        JOptionPane.INFORMATION_MESSAGE);
		
	}

}
