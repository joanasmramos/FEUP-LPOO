package dkeep.cli;

import java.util.Scanner;
import java.lang.Character;
import dkeep.logic.*;

public class Interaction {
	
	private static Scanner scanner = new Scanner(System.in);

	
	public static char askCommand() {
		System.out.println("Enter a command: \n W - up \n S - down \n A - left \n D - right");
		
        char mov = scanner.next().charAt(0);
        mov=Character.toLowerCase(mov);

        System.out.println();
        return mov;
	}
	
	public static void main(String args[]) {

		char cmd;

		GameState game = new GameState();
		game.map.print();

		while(game.getCurrent_state()!= GameState.States.DONE && game.getCurrent_state()!=GameState.States.GAME_OVER){
			cmd = askCommand();
			game.game(cmd);
			game.checkEvents();
		}
		
	}
}
