package a4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import a4.gameObjects.*;
import a4.interfaces.*;
import a4.views.MapView;

import javax.swing.*;

/**
 * @author Javier Garrido
 *
 */
public class GameWorld implements IObservable, IGameWorld, ActionListener{

	private int clock = 0;
    private int lives = 3;
    private int score = 0;
    private int rate = 50;
    private int i;
    private boolean sound = false;
    private boolean livesRemaining = true;
    private Food food;
    private Money money;
    private Weasel weasel1, weasel2;
    private Snake snake;
    private Bird bird;
    private Wall southWall, northWall, eastWall, westWall, w1, w2, w3;
    private GameObjectCollection gameObjectCollection;
    private ObserversCollection observerCollection = new ObserversCollection();
    private Timer gameTimer;
    private GameWorldProxy gWP;
    private Sound snakeCollidesWithFoodSound;
    private Sound snakeCollidesWithMoneySound;
    private Sound snakeCollidesWithWeaselSound;
    private Sound backgroundSound;
    private boolean paused;
	
	/**
	 * Empty constructor
	 */
	public GameWorld(){
		initLayout();
        gameTimer = new Timer(rate, this);
        gameTimer.start();
        gWP = new GameWorldProxy(this);

        //String soundDir = "." /*+ File.separator + "src"*/ + File.separator + "a4" + File.separator + "sounds" + File.separator;//For command compile
        String soundDir = "." + File.separator + "src" + File.separator + "a4" + File.separator + "sounds" + File.separator;//For IntelliJ compile
        String foodSoundFile = "food2.wav";
        String moneySoundFile = "money.wav";
        String deathSoundFile = "death2.wav";
        String backgroundSoundFile = "background.wav";

        String foodSoundPath = soundDir + foodSoundFile;
        String moneySoundPath = soundDir + moneySoundFile;
        String deathSoundPath = soundDir + deathSoundFile;
        String backgroundSoundPath = soundDir + backgroundSoundFile;

        snakeCollidesWithFoodSound = new Sound(foodSoundPath);
        snakeCollidesWithMoneySound = new Sound(moneySoundPath);
        snakeCollidesWithWeaselSound = new Sound(deathSoundPath);
        backgroundSound = new Sound(backgroundSoundPath);

        if (sound)
        backgroundSound.loop();

        paused = false;
	}
	
	//Factory Methods
	private Snake makeSnake() {return new Snake(gWP);}
	private Bird makeBird() {return new Bird();}
	private Food makeFood() {return new Food();}
	private Money makeMoney() {return new Money();}
	private Weasel makeWeasel(SnakeHead sH) {return new Weasel(sH);}
	private Wall makeWall(float x, float y, int w, int h) {return new Wall(x, y, w, h, gWP);}
    private Wall makeWall() {return new Wall();}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

    public GameObjectCollection getGameObjectCollection() {
        return gameObjectCollection;
    }

    public int getClock() {
		return clock;
	}

	public int getLives() {
		return lives;
	}

	public int getScore() {
		return score;
	}

	public boolean isSound() {
		return sound;
	}

    public int getRate() {
        return rate;
    }

    public Timer getGameTimer() {
        return gameTimer;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
	 * Initialize the Game World Objects and add them all to the
	 * Game Object Collection
	 */
	public void initLayout(){
		//Initialize game objects
		snake = makeSnake();
		bird = makeBird();
		food = makeFood();
		money = makeMoney();
		weasel1 = makeWeasel(snake.getSnakeHead());
		weasel2 = makeWeasel(snake.getSnakeHead());
		westWall = makeWall(3, 500, 6, 1000);
		northWall = makeWall(500, 997, 988, 6);
		eastWall = makeWall(997, 500, 6, 1000);
		southWall = makeWall(500, 3, 988, 6);
//        w1 = makeWall();
//        w2 = makeWall();

		
		//Add game objects to the collection
		gameObjectCollection = new GameObjectCollection();
		gameObjectCollection.add(snake);
		gameObjectCollection.add(bird);
		gameObjectCollection.add(food);
		gameObjectCollection.add(money);
		gameObjectCollection.add(weasel1);
		gameObjectCollection.add(weasel2);
		gameObjectCollection.add(westWall);
		gameObjectCollection.add(northWall);
		gameObjectCollection.add(eastWall);
		gameObjectCollection.add(southWall);
//        gameObjectCollection.add(w1);
//        gameObjectCollection.add(w2);

	}
	
	// n, s, e, w were input
	/**
	 * Display the action that has occured in game to the user.
	 * Change the snake head direction based on the user input
	 * @param direction
	 */
	public void changeSnakeHeading(char direction){
		//tell game world the snake's head has changed heading and is pointed to input
//		System.out.println();
//		System.out.println("Changing snake heading to " + direction);
		snake.getSnakeHead().turn(direction);
		notifyObservers();
	}
	
	// 1 was input
	/**
	 * Display the action that has occured in game to the user.
	 * Subtract a life from the user lives and restart the game
	 */
	public void collisionWithBody(){
		//lives - 1
		//if player still has lives left, reinitialize the game world
        if(sound)
            snakeCollidesWithWeaselSound.play();

//		System.out.println();
//		System.out.println("Snake collided with its body");
		lives--;
		notifyObservers();
		restartGame();
		
	}
	
	// 2 was input
	/**
	 * Display the action that has occured in game to the user.
	 * Subtract a life from the user lives and restart the game
	 */
	public void collisionWithBird(){
		//lives - 1
		//if player still has lives left, reinitialize the game world

        if (sound)
        snakeCollidesWithWeaselSound.play();

//		System.out.println();
//		System.out.println("Bird ate the snake");
		lives--;
		notifyObservers();
		restartGame();
		
	}
	
	// 3 was input
	/**
	 * Display the action that has occured in game to the user.
	 * Iterate through the game object collection to check for
	 * a Money object. Get it's value and add it to the player score.
	 * Remove that money object and quit out of the while loop.
	 */
	public void collisionWithMoney(){
		
		//add money's value to player's score
		//remove money object (if multiple, pick at random)

        if (sound)
        snakeCollidesWithMoneySound.play();

		Iterator theGameObjects = gameObjectCollection.getIterator();
		while (theGameObjects.hasNext()){
			GameObject go = (GameObject)theGameObjects.next();
			if (go instanceof Money){
                if (((Money) go).isCollidedWith()) {
                    score = score + ((Money) go).getValue();
                    gameObjectCollection.remove(go);
                    break;
                }
            }
		}
		notifyObservers();

	}
	
	// 4 was input
	/**
	 * Display the action that has occured in game to the user.
	 * Iterate through the game object collection to check for
	 * a Food object. Get its amount and add it the the snake's newSegCount
	 * variable which is used to track how many new body segments need to be
	 * added to the snake. Remove that Food object. Then instantiate new
	 * Money and Food objects and add them to the game object collection
	 */
	public void collisionWithFood(){
		
		//increase growth pool for snake's body
		//remove food object (if multiple, pick at random)
		
//		System.out.println();
//		System.out.println("Snake collided with a food");

        if (sound)
        snakeCollidesWithFoodSound.play();
		
		Iterator theGameObjects = gameObjectCollection.getIterator();
		while (theGameObjects.hasNext()){
			GameObject go = (GameObject)theGameObjects.next();
			if (go instanceof Food){
				
				snake.setNewSegCount(((Food) go).getAmount() + snake.getNewSegCount());
				gameObjectCollection.remove(go);
				break;
			}
		}
		
		//add a new random Food object
		//and one or more random Money objects
		money = makeMoney();//new Money();
		food = makeFood();//new Food();
		gameObjectCollection.add(money);
		gameObjectCollection.add(food);
		notifyObservers();
	}
	
	// 5 was input
	/**
	 * Display the action that has occured in game to the user.
	 * Subtract a life from the user lives and restart the game
	 */
	public void collisionWithWall(){
		//lives - 1
		//if player still has lives left, reinitialize the game world

        if (sound)
        snakeCollidesWithWeaselSound.play();

		lives--;
		notifyObservers();
		restartGame();
	}
	
	
	public void collisionWithWeasel(){
		//lives - 1
		//if player still has lives left, reinitialize the game world

        if (sound)
        snakeCollidesWithWeaselSound.play();

		lives--;
		notifyObservers();
		restartGame();
		
	}
	
	
	// t was input
	/**
	 * Display the action that has occured in game to the user.
	 * Iterate through the game object collection and call the 
	 * move method from each object. If the object encountered
	 * is an instance of Fixed, increase its age by one.
	 * Increase the value of clock
	 */
	public void gameClockTick(){
		//All movable objects are told to update their position
		// according to their current heading and speed
		
		//System.out.println();
		//System.out.println("Increasing game clock");
        i++;
        if (i == (1000/ rate)){
            clock++;
            i = 0;
        }



		Iterator theGameObjects = gameObjectCollection.getIterator();
		while (theGameObjects.hasNext()){
			GameObject go = (GameObject)theGameObjects.next();

            if(go instanceof Moveable) {

                ((Moveable) go).move(rate);

            } else if (go instanceof Fixed){
                ((Fixed) go).incrementAge();
            }
        }

        theGameObjects = gameObjectCollection.getIterator();
        while (theGameObjects.hasNext()) {
            ICollider curObj = (ICollider) theGameObjects.next();

            Iterator theGameObjects2 = gameObjectCollection.getIterator();
            while (theGameObjects2.hasNext()){
                if (curObj instanceof Snake) {
                    if (((Snake) curObj).isBodyCollision()){
                        break;
                    }

                }
                ICollider otherObject = (ICollider) theGameObjects2.next();
                if (otherObject != curObj){
                    if (curObj.collidesWith((GameObject)otherObject)) {
                        curObj.handleCollision((GameObject) otherObject);

                    }
                }
            }

        }


		notifyObservers();
	}
	
	public void changeStrategies(){
//		System.out.println();
//		System.out.println("Changing Strategy");
		weasel1.setStrategy();
		weasel2.setStrategy();
		notifyObservers();
	}
	
	
	// m was input
	/**
	 * Iterate through the game object collection and call each objects
	 * toString method to display a map of the current state of each game
	 * object
	 */
	public void generateMap(){//NOT USED ANYMORE
		//Generate a map showing current world

//		System.out.println();
//		System.out.println("----------------------------Game Map-------------------------------");
		
		//Iterate through the objects to call their toString() method
		// and have them display their info
		Iterator theGameObjects = gameObjectCollection.getIterator();
		while (theGameObjects.hasNext()){
			GameObject go = (GameObject)theGameObjects.next();
			System.out.println(go.toString());
			}
	}
	
	// q was input
	/**
	 * Check if user really wants to quit the game. If they do, quit. 
	 */
	public void quit(){
		//Confirm intent to quit
		//Quit
		
		String quit;
		Scanner in = new Scanner(System.in);
		
		System.out.println("Are you sure you want to quit? Yes/No");
		quit = in.nextLine();
		
		if(quit.equals("Yes") || quit.equals("yes")){
			System.out.println("Quitting game. Thanks for playing.");
			System.exit(0);
		}
	}
	
	/**
	 * Check to see if the user has lives remaining. If they do
	 * restart the game by calling initLayout. If they don't,
	 * let the user know they have 0 lives remaining and exit the
	 * game.
	 */
	public void restartGame(){
		//Check if player has lives left
		livesRemaining = anyLivesRemaining();
		if (livesRemaining){
            initLayout();			//Restart game
            clock = 0;
            score = 0;
        } else {
        	
        	int result = JOptionPane.showConfirmDialog
    				(null, 
    				"New Game?",
    				"Game Over",
    				JOptionPane.YES_NO_CANCEL_OPTION,
    				JOptionPane.QUESTION_MESSAGE);
    		
    		if (result == JOptionPane.YES_OPTION){
    			initLayout();			//Restart game
                clock = 0;
                score = 0;
                lives = 3;
    		} else if (result == JOptionPane.NO_OPTION) {
    			System.exit(0);
    		} else {
    			System.exit(0);
    		}
		}
	}
	
	/**
	 * Check to see if lives are higher than 0. If it is, return true.
	 * Otherwise, return false.
	 * @return
	 */
	private boolean anyLivesRemaining (){
		//Return true of player has lives left
		// false otherwise
		if (lives > 0){
			return true;
		}
		return false;
	}

	@Override
	public void addObserver(IObserver obs) {
		// TODO Auto-generated method stub
		observerCollection.add(obs);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		GameWorldProxy proxy = new GameWorldProxy(this);
		Iterator theObservers = observerCollection.getIterator();
		while (theObservers.hasNext()){
			IObserver go = (IObserver)theObservers.next();
			go.update(proxy, null);
			}
	}



    public void playBackgroundMusic(){
        if(sound)
        backgroundSound.loop();
    }

    public void stopBackgroundMusic(){
            backgroundSound.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameClockTick();
    }

    public void deleteSelction(MapView mv){
        ArrayList<GameObject> objectsToBeDeleted = new ArrayList<GameObject>();

        Iterator theGameObjects = gameObjectCollection.getIterator();
        while (theGameObjects.hasNext()){
            GameObject go = (GameObject)theGameObjects.next();
            if (go instanceof ISelectable){
                if (((ISelectable) go).isSelected()){
                    objectsToBeDeleted.add(go);
                }
            }
        }

        for(GameObject obj : objectsToBeDeleted){
            gameObjectCollection.remove(obj);
        }

        mv.repaint();

    }
}
