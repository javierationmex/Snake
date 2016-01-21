package a2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

import a2.commandObjects.AboutCommand;
import a2.commandObjects.BirdHitSnakeCommand;
import a2.commandObjects.ChangeSnakeHeadingEast;
import a2.commandObjects.ChangeSnakeHeadingNorth;
import a2.commandObjects.ChangeSnakeHeadingSouth;
import a2.commandObjects.ChangeSnakeHeadingWest;
import a2.commandObjects.ChangeStrategiesCommand;
import a2.commandObjects.ClockTickCommand;
import a2.commandObjects.NewCommand;
import a2.commandObjects.QuitCommand;
import a2.commandObjects.SaveCommand;
import a2.commandObjects.SnakeEatsFoodCommand;
import a2.commandObjects.SnakeHitBodyCommand;
import a2.commandObjects.SnakeHitMoneyCommand;
import a2.commandObjects.SnakeHitWallCommand;
import a2.commandObjects.SoundCommand;
import a2.commandObjects.UndoCommand;
import a2.commandObjects.WeaselHitSnakeCommand;
import a2.views.MapView;
import a2.views.ScoreView;


/**
 * @author Javier G
 *
 */
public class Game extends JFrame{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	/**
	 * Instantiate the Game World Object and call initLayout
	 * to set up the layout of the game
	 */
	public Game() {
		//Instantiate a GameWorld object
		gw = new GameWorld();
		
		//Call
		gw.initLayout();
		
		mv = new MapView();
		sv = new ScoreView();
		
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		setTitle("Snake!");
		setSize(1000,800);
		setLayout(new BorderLayout());
		
		//-----------Commands--------------------------------------------------------------//
		NewCommand newCommand = new NewCommand();
		SaveCommand saveCommand = new SaveCommand();
		UndoCommand undoCommand = new UndoCommand();
		SoundCommand soundCommand = new SoundCommand();
		AboutCommand aboutCommand = new AboutCommand();
		QuitCommand quitCommand = new QuitCommand();
		SnakeHitBodyCommand snakeHitBodyCommand = new SnakeHitBodyCommand();
		BirdHitSnakeCommand birdHitSnakeCommand = new BirdHitSnakeCommand();
		SnakeHitMoneyCommand snakeHitMoneyCommand = new SnakeHitMoneyCommand();
		SnakeEatsFoodCommand snakeEatsFoodCommand = new SnakeEatsFoodCommand();
		SnakeHitWallCommand snakeHitWallCommand = new SnakeHitWallCommand();
		WeaselHitSnakeCommand weaselHitSnakeCommand = new WeaselHitSnakeCommand();
		ChangeStrategiesCommand changeStrategiesCommand = new ChangeStrategiesCommand();
		ClockTickCommand clockTickCommand = new ClockTickCommand();
		ChangeSnakeHeadingNorth changeSnakeHeadingNorth = new ChangeSnakeHeadingNorth();
		ChangeSnakeHeadingSouth changeSnakeHeadingSouth = new ChangeSnakeHeadingSouth();
		ChangeSnakeHeadingEast changeSnakeHeadingEast = new ChangeSnakeHeadingEast();
		ChangeSnakeHeadingWest changeSnakeHeadingWest = new ChangeSnakeHeadingWest();

		//Setting target for commands that require modifying GameWorld values
		soundCommand.setTarget(gw);
		snakeHitBodyCommand.setTarget(gw);
		birdHitSnakeCommand.setTarget(gw);
		snakeHitMoneyCommand.setTarget(gw);
		snakeEatsFoodCommand.setTarget(gw);
		snakeHitWallCommand.setTarget(gw);
		weaselHitSnakeCommand.setTarget(gw);
		changeStrategiesCommand.setTarget(gw);
		clockTickCommand.setTarget(gw);
		changeSnakeHeadingNorth.setTarget(gw);
		changeSnakeHeadingSouth.setTarget(gw);
		changeSnakeHeadingEast.setTarget(gw);
		changeSnakeHeadingWest.setTarget(gw);
		//-----------Commands Ends---------------------------------------------------------//
		
		
		//-----------Key Bindings----------------------------------------------------------//
		int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap imap = mv.getMapPanel().getInputMap(mapName);
		
		KeyStroke upArrow = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
		KeyStroke downArrow = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
		KeyStroke leftArrow = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
		KeyStroke rightArrow = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
		KeyStroke space = KeyStroke.getKeyStroke("SPACE");
		
		imap.put(upArrow, "up");
		imap.put(downArrow, "down");
		imap.put(leftArrow, "left");
		imap.put(rightArrow, "right");
		imap.put(space, "spaceBar");
		
		ActionMap amap = mv.getMapPanel().getActionMap();
		
		amap.put("up", changeSnakeHeadingNorth);
		amap.put("down", changeSnakeHeadingSouth);
		amap.put("left", changeSnakeHeadingWest);
		amap.put("right", changeSnakeHeadingEast);
		amap.put("spaceBar", changeStrategiesCommand);
		
		this.requestFocus();
		//-----------Key Bindings End------------------------------------------------------//
		
		
		//-----------Menu Creating Begins--------------------------------------------------//
		JMenuBar menu = new JMenuBar();//createMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem fileNew = new JMenuItem("New");
		JMenuItem fileSave = new JMenuItem("Save");
		JMenuItem fileUndo = new JMenuItem("Undo");
		JCheckBoxMenuItem fileSoundCheckBox= new JCheckBoxMenuItem("Sound");
		JMenuItem fileAbout = new JMenuItem("About");
		JMenuItem fileQuit = new JMenuItem("Quit");
		
		fileMenu.add(fileNew);
		fileMenu.add(fileSave);
		fileMenu.add(fileUndo);
		fileMenu.add(fileSoundCheckBox);
		fileMenu.add(fileAbout);
		fileMenu.add(fileQuit);
		
		fileNew.setAction(newCommand);
		fileSave.setAction(saveCommand);
		fileUndo.setAction(undoCommand);
		fileSoundCheckBox.setAction(soundCommand);
		fileAbout.setAction(aboutCommand);
		fileQuit.setAction(quitCommand);
		
		menu.add(fileMenu);
	
		JMenu commandsMenu = new JMenu("Commands");
		JMenuItem command1= new JMenuItem("Snake collided with its body.");
		JMenuItem command2= new JMenuItem("Bird collided with the snake.");
		JMenuItem command3 = new JMenuItem("Snake collided with money");
		JMenuItem command4 = new JMenuItem("Snake eats food");
		JMenuItem command5 = new JMenuItem("Snake collided with a wall");
		JMenuItem command6 = new JMenuItem("Weasel collided with the snake");
		JMenuItem command7 = new JMenuItem("Change strategies");
		JMenuItem command8= new JMenuItem("Clock tick");
		
		commandsMenu.add(command1);
		commandsMenu.add(command2);
		commandsMenu.add(command3);
		commandsMenu.add(command4);
		commandsMenu.add(command5);
		commandsMenu.add(command6);
		commandsMenu.add(command7);
		commandsMenu.add(command8);
		
		command1.setAction(snakeHitBodyCommand);
		command2.setAction(birdHitSnakeCommand);
		command3.setAction(snakeHitMoneyCommand);
		command4.setAction(snakeEatsFoodCommand);
		command5.setAction(snakeHitWallCommand);
		command6.setAction(weaselHitSnakeCommand);
		command7.setAction(changeStrategiesCommand);
		command8.setAction(clockTickCommand);
		
		menu.add(commandsMenu);
		
		this.setJMenuBar(menu);
		//-----------Menu Creating Ends--------------------------------------------------//
		
		
		//-----------Panel Creating Begins-----------------------------------------------//
		JPanel commandPanel = new JPanel();
		commandPanel.setBorder(new LineBorder(Color.gray, 1));
		commandPanel.setLayout(new GridLayout(10,1));
		commandPanel.setBorder(BorderFactory.createTitledBorder(
		        BorderFactory.createEtchedBorder(), "Commands:"));
		
		this.add(commandPanel, BorderLayout.WEST);
		this.add(sv.getScoresPanel(), BorderLayout.NORTH);
		this.add(mv.getMapPanel(), BorderLayout.CENTER);
		
		JButton command1Button = new JButton ("Snake hit body.");
		JButton command2Button = new JButton ("Bird hit snake.");
		JButton command3Button = new JButton ("Snake hit money.");
		JButton command4Button = new JButton ("Snake eats food.");
		JButton command5Button = new JButton ("Snake hit wall.");
		JButton command6Button = new JButton ("Weasel hit snake.");
		JButton command7Button = new JButton ("Change strategies.");
		JButton command8Button = new JButton ("Clock tick.");
		JButton command9Button = new JButton ("Quit.");
		
		commandPanel.add(command1Button);
		commandPanel.add(command2Button);
		commandPanel.add(command3Button);
		commandPanel.add(command4Button);
		commandPanel.add(command5Button);
		commandPanel.add(command6Button);
		commandPanel.add(command7Button);
		commandPanel.add(command8Button);
		commandPanel.add(command9Button);
		
		command1Button.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); 
		command2Button.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); 
		command3Button.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); 
		command4Button.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); 
		command5Button.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); 
		command6Button.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); 
		command7Button.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); 
		command8Button.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); 
		command9Button.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); 
		
		command1Button.setAction(snakeHitBodyCommand);
		command2Button.setAction(birdHitSnakeCommand);
		command3Button.setAction(snakeHitMoneyCommand);
		command4Button.setAction(snakeEatsFoodCommand);
		command5Button.setAction(snakeHitWallCommand);
		command6Button.setAction(weaselHitSnakeCommand);
		command7Button.setAction(changeStrategiesCommand);
		command8Button.setAction(clockTickCommand);
		command9Button.setAction(quitCommand);
		
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//-----------Panel Creating Ends-------------------------------------------------//
	}
	
}
