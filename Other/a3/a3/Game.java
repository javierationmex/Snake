package a3;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

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

import a3.commandObjects.*;
import a3.views.MapView;
import a3.views.ScoreView;


/**
 * @author Javier G
 *
 */
public class Game extends JFrame{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
    private GameWorldProxy gWP;

    private NewCommand newCommand;
    private SaveCommand saveCommand;
    private UndoCommand undoCommand;
    private SoundCommand soundCommand;
    private AboutCommand aboutCommand;
    private QuitCommand quitCommand;
    private SnakeHitBodyCommand snakeHitBodyCommand;
    private BirdHitSnakeCommand birdHitSnakeCommand;
    private SnakeHitMoneyCommand snakeHitMoneyCommand;
    private SnakeEatsFoodCommand snakeEatsFoodCommand;
    private SnakeHitWallCommand snakeHitWallCommand;
    private WeaselHitSnakeCommand weaselHitSnakeCommand;
    private ChangeStrategiesCommand changeStrategiesCommand ;
    private ClockTickCommand clockTickCommand;
    private ChangeSnakeHeadingNorth changeSnakeHeadingNorth;
    private ChangeSnakeHeadingSouth changeSnakeHeadingSouth;
    private ChangeSnakeHeadingEast changeSnakeHeadingEast;
    private ChangeSnakeHeadingWest changeSnakeHeadingWest;
    private PauseCommand pauseCommand;
    private DeleteCommand deleteCommand;
	
	/**
	 * Instantiate the Game World Object and call initLayout
	 * to set up the layout of the game
	 */
	public Game() {
		//Instantiate a GameWorld object
		gw = new GameWorld();
        gWP = new GameWorldProxy(gw);
		
		//Call
		gw.initLayout();

		mv = new MapView(gWP);
		sv = new ScoreView(gWP);
		
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		setTitle("Snake!");
		setSize(1150,1150);
		setLayout(new BorderLayout());
		
		//-----------Commands--------------------------------------------------------------//
		newCommand = new NewCommand();
		saveCommand = new SaveCommand();
		undoCommand = new UndoCommand();
		soundCommand = new SoundCommand();
		aboutCommand = new AboutCommand();
		quitCommand = new QuitCommand();
		snakeHitBodyCommand = new SnakeHitBodyCommand();
		birdHitSnakeCommand = new BirdHitSnakeCommand();
		snakeHitMoneyCommand = new SnakeHitMoneyCommand();
		snakeEatsFoodCommand = new SnakeEatsFoodCommand();
		snakeHitWallCommand = new SnakeHitWallCommand();
		weaselHitSnakeCommand = new WeaselHitSnakeCommand();
		changeStrategiesCommand = new ChangeStrategiesCommand();
		clockTickCommand = new ClockTickCommand();
		changeSnakeHeadingNorth = new ChangeSnakeHeadingNorth();
		changeSnakeHeadingSouth = new ChangeSnakeHeadingSouth();
		changeSnakeHeadingEast = new ChangeSnakeHeadingEast();
		changeSnakeHeadingWest = new ChangeSnakeHeadingWest();
        pauseCommand = new PauseCommand(this);
        deleteCommand = new DeleteCommand(mv);
        deleteCommand.setEnabled(false);

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
        pauseCommand.setTarget(gw);
        deleteCommand.setTarget(gw);
		//-----------Commands Ends---------------------------------------------------------//
		
		
		//-----------Key Bindings----------------------------------------------------------//
		int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap imap = mv.getInputMap(mapName);

        KeyStroke upArrow = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
		KeyStroke downArrow = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
		KeyStroke leftArrow = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
		KeyStroke rightArrow = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
		KeyStroke space = KeyStroke.getKeyStroke("SPACE");
        KeyStroke delete = KeyStroke.getKeyStroke("DELETE");
		
		imap.put(upArrow, "up");
		imap.put(downArrow, "down");
		imap.put(leftArrow, "left");
		imap.put(rightArrow, "right");
		imap.put(space, "spaceBar");
        imap.put(delete, "del");

        ActionMap amap = mv.getActionMap();

		amap.put("up", changeSnakeHeadingNorth);
		amap.put("down", changeSnakeHeadingSouth);
		amap.put("left", changeSnakeHeadingWest);
		amap.put("right", changeSnakeHeadingEast);
		amap.put("spaceBar", changeStrategiesCommand);
        amap.put("del", deleteCommand);
		
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
		this.add(sv, BorderLayout.NORTH);
        this.add(mv, BorderLayout.CENTER);

        JButton pauseButton = new JButton("Pause Game");
        JButton deleteButton = new JButton("Delete");
		JButton quitButton = new JButton ("Quit");

        commandPanel.add(pauseButton);
        commandPanel.add(deleteButton);
		commandPanel.add(quitButton);

        pauseButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        deleteButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
		quitButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");

		quitButton.setAction(quitCommand);
        deleteButton.setAction(deleteCommand);
        pauseButton.setAction(pauseCommand);
		
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//-----------Panel Creating Ends-------------------------------------------------//



	}

    public void enableCommands(){
        snakeEatsFoodCommand.setEnabled(true);
        snakeHitBodyCommand.setEnabled(true);
        snakeHitMoneyCommand.setEnabled(true);
        snakeHitWallCommand.setEnabled(true);
        clockTickCommand.setEnabled(true);
        changeSnakeHeadingEast.setEnabled(true);
        changeSnakeHeadingNorth.setEnabled(true);
        changeSnakeHeadingSouth.setEnabled(true);
        changeSnakeHeadingWest.setEnabled(true);
        weaselHitSnakeCommand.setEnabled(true);
        birdHitSnakeCommand.setEnabled(true);
        changeStrategiesCommand.setEnabled(true);
        deleteCommand.setEnabled(false);
    }

    public void disableCommands(){
        snakeEatsFoodCommand.setEnabled(false);
        snakeHitBodyCommand.setEnabled(false);
        snakeHitMoneyCommand.setEnabled(false);
        snakeHitWallCommand.setEnabled(false);
        clockTickCommand.setEnabled(false);
        changeSnakeHeadingEast.setEnabled(false);
        changeSnakeHeadingNorth.setEnabled(false);
        changeSnakeHeadingSouth.setEnabled(false);
        changeSnakeHeadingWest.setEnabled(false);
        weaselHitSnakeCommand.setEnabled(false);
        birdHitSnakeCommand.setEnabled(false);
        changeStrategiesCommand.setEnabled(false);
        deleteCommand.setEnabled(true);
    }

    public NewCommand getNewCommand() {
        return newCommand;
    }

    public QuitCommand getQuitCommand() {
        return quitCommand;
    }
}
