package a3.views;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import a3.GameWorldProxy;
import a3.gameObjects.GameObject;
import a3.interfaces.IDrawable;
import a3.interfaces.IObserver;
import a3.interfaces.ISelectable;

public class MapView extends JPanel implements IObserver, MouseListener{
    GameWorldProxy gWP;
	
	public MapView(GameWorldProxy g){
        this.gWP = g;
		this.setBorder(new LineBorder(Color.gray, 1));
        this.setBackground(Color.BLACK);
        this.setSize(700,700);
        addMouseListener(this);
	}

	public void update(GameWorldProxy o, Object arg) {
        this.repaint();
	}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Iterator theGameObjects = gWP.getGameObjectCollection().getIterator();
        while (theGameObjects.hasNext()){
            GameObject go = (GameObject)theGameObjects.next();
            if (go instanceof IDrawable)
                ((IDrawable)go).draw(g);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gWP.isPaused()){
            Point p = e.getPoint();
            if (e.isControlDown()){
                Iterator theGameObjects2 = gWP.getGameObjectCollection().getIterator();
                while (theGameObjects2.hasNext()){
                    GameObject go = (GameObject)theGameObjects2.next();
                    if (go instanceof ISelectable){
                        if (((ISelectable)go).contains(p)) {
                            if (((ISelectable) go).isSelected()) {
                                ((ISelectable) go).setSelected(false);
                            } else {
                                ((ISelectable) go).setSelected(true);
                            }
                        }
                    }
                }

            } else {
                Iterator theGameObjects = gWP.getGameObjectCollection().getIterator();
                while (theGameObjects.hasNext()) {
                    GameObject go = (GameObject) theGameObjects.next();
                    if (go instanceof ISelectable) {
                        if (((ISelectable) go).contains(p)) {
                            if (((ISelectable) go).isSelected()) {
                                ((ISelectable) go).setSelected(false);
                            } else {
                                ((ISelectable) go).setSelected(true);
                            }
                        } else {
                            ((ISelectable) go).setSelected(false);
                        }
                    }

                }
            }
            this.repaint();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
