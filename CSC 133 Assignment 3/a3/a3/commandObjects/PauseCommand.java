package a3.commandObjects;

import a3.Game;
import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Javier G on 11/30/2014.
 */
public class PauseCommand extends AbstractAction {
    private GameWorld gw;
    private Game g;

    public PauseCommand(Game g){
        super("Pause Game");
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        pauseGame(e);

    }

    public void setTarget(GameWorld gameWorld) {
        // TODO Auto-generated method stub
        gw = gameWorld;
    }

    public void pauseGame(ActionEvent e){
        JButton b = (JButton)e.getSource();
        if (gw.isPaused()){
            gw.getGameTimer().start();
            gw.setPaused(false);
            b.setText("Pause Game");
            if(gw.isSound()){
                gw.playBackgroundMusic();
            }
            g.enableCommands();

        } else {
            gw.getGameTimer().stop();
            gw.setPaused(true);
            b.setText("Resume Game");
            gw.stopBackgroundMusic();
            g.disableCommands();

        }
    }

}
