package a1;
import java.util.Scanner;


/**
 * @author Javier G
 *
 */
public class Game {
	private GameWorld gw;
	
	/**
	 * Instantiate the Game World Object and call initLayout
	 * to set up the layout of the game
	 */
	public Game() {
		//Instantiate a GameWorld object
		gw = new GameWorld();
		
		//Call
		gw.initLayout();
		play();
	}
	
	
	
	/**
	 * Displays instructions on the commands for the game and then 
	 * calls getCommand to accept input from user
	 */
	private void play(){
		int n = 0;
		System.out.println("Welcome to SNAKE");
		System.out.println("Directins to turn the snake-'s'=south, 'n'=north, 'e'=east, or 'w'=west");
		System.out.println("Commands from 1-5:" );
		System.out.println("  1-Snake has collided with its body.");
		System.out.println("  2-Snake has collided with a bird.");
		System.out.println("  3-Snake has collided with money.");
		System.out.println("  4-Snake has collided with food.");
		System.out.println("  5-Snake has collided with a wall.");
		System.out.println("Other commands:");
		System.out.println("  t-Game clock ticks once");
		System.out.println("  d-Generate game value display");
		System.out.println("  m-Generate game map");
		System.out.println("  q-Quit the game");
		while (n < 1)
			getCommand();
	}
	
	/**
	 * Accept input from the user. Call appropriate method to handle
	 * the type of input we receive
	 */
	private void getCommand(){
		String line;
		int aValue;
		Scanner in = new Scanner(System.in);
		System.out.println();
		System.out.println("Enter a command: ");
		
      if (in.hasNextInt()) {
    	aValue = in.nextInt();
    	userEnteredInt(aValue);
      } else {
    	line = in.nextLine();
    	userEnteredLetter(line);
      }
	}
	
	/**
	 * Call the correct method depending on the integer the user
	 * entered. Also account for invalid input and report it
	 * @param aValue
	 */
	private void userEnteredInt(int aValue){
		if (aValue > 6){
			System.out.println(aValue + " is not a valid command.");
			System.out.println("Number commands must be between 1 and 5");
			aValue = 0;
		}
		
		switch (aValue){
		case 0: //Do nothing
			break;
		case 1: //Pretend snake head has collided with body segment
			gw.collisionWithBody();
			break;
		case 2: //Pretend snake head has collided with a bird
			gw.collisionWithBird();
			break;
		case 3: //Pretend snake head has collided with money
			gw.collisionWithMoney();
			break;
		case 4: //Pretend snake head has collided with food
			gw.collisionWithFood();
			break;
		case 5: //Pretend snake head has collided with a wall
			gw.collisionWithWall();
			break;
		default: //Error saying input doesn't match
			break;
		}
    }
	
	/**
	 * Call the correct method depending on the character the user 
	 * entered. Also account for invalid input and report it
	 * @param line
	 */
	private void userEnteredLetter(String line){
		if (line.length()>1){
			System.out.println(line + " is not a valid command.");
			System.out.println("Commmands can only be 1 character long.");
			System.out.println();
			line = null;
		}
		
		if (line != null){
			switch (line.charAt(0)){
			case 'n': //Change snake head direction to north
				gw.changeSnakeHeading('n');
				break;
			case 's': //Change snake head direction to south
				gw.changeSnakeHeading('s');
				break;
			case 'e': //Change snake head direction to east
				gw.changeSnakeHeading('e');
				break;
			case 'w': //Change snake head direction to west
				gw.changeSnakeHeading('w');
				break;
			case 't': //Game clock has ticked
				gw.gameClockTick();
				break;
			case 'd': //Generate display of game world
				gw.generateDisplay();
				break;
			case 'm': //Output map
				gw.generateMap();
				break;
			case 'q': //Quit program. System.exit(0)
				gw.quit();
				break;
			default: //Error saying input doesn't match
				System.out.println(line + " is not a valid command.");
				break;
			}
		}
	}
}
