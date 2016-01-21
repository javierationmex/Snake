package a3.commandObjects;

import a3.GameWorld;
import a3.views.MapView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Javier G on 11/30/2014.
 */
public class DeleteCommand extends AbstractAction {

    private GameWorld gw;
    private MapView mv;

    public DeleteCommand(MapView mv){
        super("Delete");
        this.mv = mv;
    }

    public void setTarget(GameWorld gameWorld){
        gw = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gw.deleteSelction(mv);
    }
}
