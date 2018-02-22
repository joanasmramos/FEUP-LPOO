package dkeep.cli;

import java.util.Scanner;
import java.lang.Character;
import dkeep.logic.*;

public class Interaction {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static char askCommand() {
		System.out.println("Enter a command: \n W - up \n S - down \n A - left \n D - right");
		
        char mov = scanner.next().charAt(0);
        mov=Character.toLowerCase(mov);
        
        return mov;
	}
	
	public static void promptMsg(String message) {
		System.out.println(message);
	}
	
	public void printMap(char map[][]) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j]);
			}
		}
	}
	
	public static void main(String args[]) {
		
		
		
		
		
	}
}
