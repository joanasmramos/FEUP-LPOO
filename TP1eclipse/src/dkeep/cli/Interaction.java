package dkeep.cli;

import java.util.Scanner;
import java.lang.Character;
import dkeep.logic.*;

public class Interaction {
	
	public enum Level {ONE, TWO;}
	public enum Event {VICTORY, LOSS, LVLUP;}
	<>
	private Level level;
	private Scanner scanner;
	
	public Interaction(Level level) {
		this.level = level;
		scanner = new Scanner(System.in);
	}
	
	public Level getLevel() {
		return this.level;
	}

	public char askCommand() {
		System.out.println("Enter a command: \n W - up \n S - down \n A - left \n D - right");
		
        char mov = this.scanner.next().charAt(0);
        mov=Character.toLowerCase(mov);
        
        return mov;
	}
	
	public static void promptMsg(String message) {
		System.out.println(message);
	}
	
	public void printMap() {
		
	}
	
	public static Event runGame() {
	}
	
	public static void main(String args[]) {
		if(runGame() == Event.LOSS){
			promptMsg("You lost, good luck next time!");
		}
		
		if(runGame() == Event.VICTORY) {
			promptMsg("You won, congratulations!");
		}
		
		if(runGame() == Event.LVLUP) {
			promptMsg("On to the next level, good luck!");
		}
	}
}
