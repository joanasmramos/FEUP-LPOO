package dkeep.cli;

import java.util.Scanner;

public class Interaction {
	
	public enum Level {ONE, TWO;}
	public enum Events {VICTORY, LOSS, LVLUP;}
	
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
	
	public void promptMsg(String message) {
		System.out.println(message);
	}
	
	public void printMap() {
		
	}
	
	public int game() {
		return 1;
	}
	
	public static void main(String args[]) {
		
	}
}
