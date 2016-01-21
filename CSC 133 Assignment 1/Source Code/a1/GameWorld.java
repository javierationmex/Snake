package a1;

import java.util.Iterator;
import java.util.Scanner;

import a1.gameObjects.Bird;
import a1.gameObjects.Fixed;
import a1.gameObjects.Food;
import a1.gameObjects.GameObject;
import a1.gameObjects.GameObjectCollection;
import a1.gameObjects.Money;
import a1.gameObjects.Snake;
import a1.gameObjects.Wall;

/**
 * @author Javier Garrido
 *
 */
public class GameWorld {

	private int clock = 0;
	private int lives = 3;
	private int score = 0;
	private boolean livesRemaining = true;
	private Food food;
	private Money money;
	private Wall southWall, northWall, eastWall, westWall;
	private Snake snake;
	private Bird bird;
	private GameObjectCollection gameObjectCollection;
	
	/**
	 * Empty constructor
	 */
	public GameWorld(){
		//Empty
	}

	/**
	 * Initialize the Game World Objects and add them all to the
	 * Game Object Collection
	 */
	public void initLayout(){
		//Initialize game objects
		snake = new Snake();
		bird = new Bird();
		food = new Food();
		money = new Money();
		westWall = new Wall(3, 500, 6, 100);
		northWall = new Wall(500, 997, 988, 6);
		eastWall = new Wall(997, 500, 6, 1000);
		southWall = new Wall(500, 3, 988, 6);
		
		//Add game objects to the collection
		gameObjectCollection = new GameObjectCollection();
		gameObjectCollection.add(snake);
		gameObjectCollection.add(bird);
		gameObjectCollection.add(food);
		gameObjectCollection.add(money);
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
		System.out.println("Snake collided with a bird");
		lives--;
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
				snake.setNewSegCount(snake.getNewSegCount() + food.getAmount());
				gameObjectCollection.remove(go);
				break;
			}
		}
		
		//add a new random Food object
		//and one or more random Money objects
		money = new Money();
		food = new Food();
		gameObjectCollection.add(money);
		gameObjectCollection.add(food);
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
	}
	
	// d was input
	/**
	 * Display the current game values: lives, clock and score
	 */
	public void generateDisplay(){
		//Generate a display outputting current game state values
		
		System.out.println();
		System.out.println("Current game values");
		System.out.println("Lives: " + lives);
		System.out.println("Clock: " + clock);
		System.out.println("Score: " + score);
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
	public boolean anyLivesRemaining (){
		//Return true of player has lives left
		// false otherwise
		if (lives > 0){
			return true;
		}
		return false;
	}
}
