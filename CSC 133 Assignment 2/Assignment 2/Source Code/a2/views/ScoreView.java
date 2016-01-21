package a2.views;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import a2.GameWorldProxy;
import a2.interfaces.IObservable;
import a2.interfaces.IObserver;

public class ScoreView extends JPanel implements IObserver{
	JPanel scoresPanel;
	JLabel time, score, lives, sound;
	private int clockG, livesG, scoreG;
	private boolean soundG;
	
	public ScoreView(){
		scoresPanel = new JPanel();
		scoresPanel.setBorder(new LineBorder(Color.blue, 1));
		time = new JLabel("Time:  ");
		scoresPanel.add(time);
		score = new JLabel("Current Score:  ");
		scoresPanel.add(score);
		lives = new JLabel("Lives Left:  ");
		scoresPanel.add(lives);
		sound = new JLabel("Sound:    ");
		scoresPanel.add(sound);
		
	}

	public JPanel getScoresPanel() {
		return scoresPanel;
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
