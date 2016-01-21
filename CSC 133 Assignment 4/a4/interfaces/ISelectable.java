package a4.interfaces;

import java.awt.*;

/**
 * Created by Javier G on 11/30/2014.
 */
public interface ISelectable {

    public void setSelected(boolean yesNo);
    public boolean isSelected();
    public boolean contains(Point p);
    public void draw(Graphics g);
}
