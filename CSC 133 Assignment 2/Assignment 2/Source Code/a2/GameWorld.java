package a2;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import a2.gameObjects.Bird;
import a2.gameObjects.BoundaryMovement;
import a2.gameObjects.Fixed;
import a2.gameObjects.Food;
import a2.gameObjects.GameObject;
import a2.gameObjects.GameObjectCollection;
import a2.gameObjects.Money;
import a2.gameObjects.ObserversCollection;
import a2.gameObjects.Snake;
import a2.gameObjects.Wall;
import a2.gameObjects.Weasel;
import a2.interfaces.IGameWorld;
import a2.interfaces.IObservable;
import a2.interfaces.IObserver;
import a2.interfaces.IStrategy;

/**
 * @author Javier Garrido
 *
 */
public class GameWorld implements IObservable, IGameWorld{

	private int clock = 0;
	private int lives = 3;
	private int score = 0;
	private boolean sound = false;
	private boolean livesRemaining = true;
	private Food food;
	private Money money;
	private Weasel weasel1, weasel2;
	private Snake snake;
	private Bird bird;
	private Wall southWall, northWall, eastWall, westWall;
	private GameObjectCollection gameObjectCollection;
	private ObserversCollection observerCollection = new ObserversCollection();
	
	/**
	 * Empty constructor
	 */
	public GameWorld(){
		initLayout();
		//Empty
	}
	
	//Factory Methods
	private Snake makeSnake() {return new Snake();}
	private Bird makeBird() {return new Bird();}
	private Food makeFood() {return new Food();}
	private Money makeMoney() {return new Money();}
	private Weasel makeWeasel() {return new Weasel();}
	private Wall makeWall(float x, float y, int w, int h) {return new Wall(x, y, w, h);}

	public void setSound(boolean sound) {
		this.sound = sound;
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
		weasel1 = makeWeasel();
		weasel2 = makeWeasel();
		westWall = makeWall(3, 500, 6, 100);
		northWall = makeWall(500, 997, 988, 6);
		eastWall = makeWall(997, 500, 6, 1000);
		southWall = makeWall(500, 3, 988, 6);
		
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

	}
	
	// n, s, e, w were input
	/**
	 * Display the action that has occured in game to the user.
	 * Change the snake head direction based on the user input
	 * @param direction
	 */
	public void changeSnakeHeading(char direction){
		//tell game world the snake's head has changed heading and is pointed to input
		System.out.println();
		System.out.println("Changing snake heading to " + direction);
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
	
		System.out.println();
		System.out.println("Snake collided with its body");
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
		
		System.out.println();
		System.out.println("Bird ate the snake");
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
		
		System.out.println();
		System.out.println("Snake collided with money");
		Iterator theGameObjects = gameObjectCollection.getIterator();
		while (theGameObjects.hasNext()){
			GameObject go = (GameObject)theGameObjects.next();
			if (go instanceof Money){
				score = score + ((Money) go).getValue();
				gameObjectCollection.remove(go);
				break;
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
		
		System.out.println();
		System.out.println("Snake collided with a food");
		
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
		
		System.out.println();
		System.out.println("Snake collided with a wall");
		lives--;
		notifyObservers();
		restartGame();
	}
	
	
	public void collisionWithWeasel(){
		//lives - 1
		//if player still has lives left, reinitialize the game world
	
		System.out.println();
		System.out.println("Weasel ate the snake");
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
		
		System.out.println();
		System.out.println("Increasing game clock");
		Iterator theGameObjects = gameObjectCollection.getIterator();
		while (theGameObjects.hasNext()){
			GameObject go = (GameObject)theGameObjects.next();
			go.move();
				if (go instanceof Fixed){
					((Fixed) go).incrementAge();
				}
			}
		
		//clock++
		clock++;
		notifyObservers();
	}
	
	public void changeStrategies(){
		System.out.println();
		System.out.println("Changing Strategy");
		weasel1.setStrategy(snake);
		weasel2.setStrategy(snake);
		notifyObservers();
	}
	
	
	// m was input
	/**
	 * Iterate through the game object collection and call each objects
	 * toString method to display a map of the current state of each game
	 * object
	 */
	public void generateMap(){
		//Generate a map showing current world

		System.out.println();
		System.out.println("----------------------------Game Map-------------------------------");
		
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
		if (livesRemaining)
			initLayout();			//Restart game
		else {
			System.out.println();	//Game over
			System.out.println("You have " + lives + " lives remaining.");
			System.out.println("Game Over.");
			System.exit(0);
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

}
