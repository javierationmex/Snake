package a4.views;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import a4.GameWorldProxy;
import a4.interfaces.IObserver;

public class ScoreView extends JPanel implements IObserver{
	JLabel time, score, lives, sound;
	private int clockG, livesG, scoreG;
	private boolean soundG;
    private GameWorldProxy gWP;
	
	public ScoreView(GameWorldProxy g){
        this.setSize(200, 900);
		this.setBorder(new LineBorder(Color.blue, 1));
		time = new JLabel("Time:  ");
		this.add(time);
		score = new JLabel("Current Score:  ");
		this.add(score);
		lives = new JLabel("Lives Left:  ");
		this.add(lives);
		sound = new JLabel("Sound:    ");
		this.add(sound);
		this.gWP = g;
	}

	@Override
	public void update(GameWorldProxy o, Object arg) {
		// TODO Auto-generated method stub

        clockG = o.getClock();
		livesG = o.getLives();
		scoreG = o.getScore();
		soundG = o.isSound();
		
		time.setText("Time: " + clockG); 
		score.setText("Current Score: " + scoreG);
		lives.setText("Lives Left: " + livesG);
		if (soundG){
			sound.setText("Sound: ON");
		} else{
			sound.setText("Sound: OFF");
		}
		
	}
	
	

}
