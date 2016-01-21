package a4.views;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import a4.GameWorldProxy;
import a4.gameObjects.GameObject;
import a4.gameObjects.Money;
import a4.interfaces.IDrawable;
import a4.interfaces.IObserver;
import a4.interfaces.ISelectable;

public class MapView extends JPanel implements IObserver, MouseListener{
    GameWorldProxy gWP;
    private static double windowLeft = 0;
    private static double windowRight = 1000;
    private static double windowTop = 1000;
    private static double windowBottom = 0;
    private double y = windowTop - windowBottom;
    private double x = windowRight - windowLeft;
    AffineTransform worldToND, ndToScreen, theVTM;
	
	public MapView(GameWorldProxy g){
        this.gWP = g;
		this.setBorder(new LineBorder(Color.gray, 1));
        this.setBackground(Color.BLACK);
        addMouseListener(this);

	}

	public void update(GameWorldProxy o, Object arg) {
        this.repaint();
	}

    private AffineTransform buildWorldToNDXform(double winWidth,double winHeight,double winLeft,double winBottom){
        AffineTransform W2ND = new AffineTransform();
        AffineTransform scale = new AffineTransform();
        W2ND.translate(0-winLeft,0-winBottom);
        scale.scale(1/winWidth, 1/winHeight);
        scale.concatenate(W2ND);
        return scale;
    }

    private AffineTransform buildNDToScreenXform(double panelWidth,double panelHeight){
        AffineTransform NDS = new AffineTransform();
        //AffineTransform scale = new AffineTransform();
        AffineTransform trans = new AffineTransform();
        NDS.scale(panelWidth, 0-panelHeight);
        trans.translate(0, panelHeight);
        trans.concatenate(NDS);
        return trans;
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform saveAt = g2d.getTransform();

        worldToND = buildWorldToNDXform(windowRight,windowTop,windowLeft,windowBottom);
        ndToScreen = buildNDToScreenXform(this.getWidth(),this.getHeight());
        theVTM = (AffineTransform) ndToScreen.clone();
        theVTM.concatenate(worldToND);

        g2d.transform(theVTM);

//        g2d.translate(0, this.getHeight());
//
//        g2d.scale(1,-1);

        Iterator theGameObjects = gWP.getGameObjectCollection().getIterator();
        while (theGameObjects.hasNext()){
            GameObject go = (GameObject)theGameObjects.next();
            if (go instanceof IDrawable)
                ((IDrawable)go).draw(g2d);
        }
        g2d.setTransform(saveAt);
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
