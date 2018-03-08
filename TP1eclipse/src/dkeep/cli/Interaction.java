package dkeep.cli;

import java.util.Scanner;
import java.lang.Character;
import dkeep.logic.*;
import dkeep.logic.Object;

public class Interaction {
	
	private static Scanner scanner = new Scanner(System.in);

    public static char map1[][] =  {
            { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
            { 'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
            { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
            { 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
            { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
            { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
            { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
            { 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' },
            { 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X' },
            { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
    };

    public static char map2[][] = {
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
    };


    public static void print(Map map) {
        int print_char = 0;
        int print_obj = 0;

        for (int i = 0; i < map.getN_lines(); i++) {
            for (int j = 0; j < map.getColumns(); j++) {

                for (dkeep.logic.Character charac : map.getChars()) {
                    if(i==charac.getLine() && j==charac.getColumn()) {
                        System.out.print(charac.getChar() + " ");
                        print_char++;
                        break;
                    }
                }

                for(Object obj : map.getObjs()) {
                    if(i==obj.getLine() && j==obj.getColumn()) {
                        if(obj.isVisible()) {
                            if(print_char>0) j++;
                            System.out.print(obj.getChar() + " ");
                            print_obj++;
                            break;
                        }
                    }
                }

                if(print_char==0 && print_obj==0) System.out.print(map.getMap()[i][j] + " ");

                print_char = 0;
                print_obj = 0;

            }
            System.out.println();
        }
    }

	
	public static char askCommand() {
		System.out.println("Enter a command: \n W - up \n S - down \n A - left \n D - right");
		
        char mov = scanner.next().charAt(0);
        mov=Character.toLowerCase(mov);

        System.out.println();
        return mov;
	}

    public static void promptMsg(String message) {
        System.out.println(message);
    }
	
	public static void main(String args[]) {

		char cmd;

        Map map = new Map(map1);
        GameState game = new GameState(map);
		print(game.map);

		while(game.getCurrent_state()!= GameState.States.DONE && game.getCurrent_state()!=GameState.States.GAME_OVER){
			cmd = askCommand();
			game.game(cmd);
			game.checkEvents();

			if(game.getCurrent_state() == GameState.States.MAP_DONE) game.getMap().setMap(map2);
		}
		
	}
}
