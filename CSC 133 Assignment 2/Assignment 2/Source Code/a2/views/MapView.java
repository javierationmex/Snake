package a2.views;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import a2.GameWorldProxy;
import a2.interfaces.IObservable;
import a2.interfaces.IObserver;

public class MapView extends JPanel implements IObserver{
	JPanel mapPanel;
	
	public MapView(){
		mapPanel = new JPanel();
		mapPanel.setBorder(new LineBorder(Color.gray, 1));
	}

	public JPanel getMapPanel() {
		return mapPanel;
	}

	@Override
	public void update(GameWorldProxy o, Object arg) {
		o.generateMap();
		
	}
	
	
	
}
