package dkeep.cli;

import java.util.Scanner;
import java.lang.Character;
import dkeep.logic.*;
import dkeep.logic.Object;
import dkeep.gui.DungeonKeep;

public class Interaction {
	
	private static Scanner scanner = new Scanner(System.in);
	private int nrOgres = 1;
	private int guardType = 0;
	
	public int getNrOgres() {
		return nrOgres;
	}

	public void setNrOgres(int nrOgres) {
		this.nrOgres = nrOgres;
	}

	public int getGuardType() {
		return guardType;
	}

	public void setGuardType(int guardType) {
		this.guardType = guardType;
	}

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

    public String printToString(Map map) {
    	String mapstr = "";
        int print_char = 0;
        int print_obj = 0;

        for (int i = 0; i < map.getN_lines(); i++) {
            for (int j = 0; j < map.getColumns(); j++) {

                for (dkeep.logic.Character charac : map.getChars()) {
                    if(i==charac.getLine() && j==charac.getColumn()) {
                        mapstr += charac.getChar() + " ";
                        print_char++;
                        break;
                    }
                }

                for(Object obj : map.getObjs()) {
                    if(i==obj.getLine() && j==obj.getColumn()) {
                        if(obj.isVisible()) {
                            if(print_char>0) j++;
                            mapstr += obj.getChar() + " ";
                            print_obj++;
                            break;
                        }
                    }
                }

                if(print_char==0 && print_obj==0) mapstr += map.getMap()[i][j] + " ";

                print_char = 0;
                print_obj = 0;

            }
            mapstr += "\n";
        }
        
        return mapstr;
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
    
    public Interaction(String nrOgres, int guardType) {
    	this.nrOgres = Integer.parseInt(nrOgres);
    	this.guardType = guardType;
    }
    
    public GameState Dungeon() {
        Map map = new Map(map1);
        GameState game = new GameState(map);
        game.setNrOgres(nrOgres);
		print(game.getMap());
		
		return game;
    }
	
	public static void main(String args[]) {

		char cmd;

        Map map = new Map(map1);
        GameState game = new GameState(map);
		print(game.getMap());

		while(game.getCurrent_state()!= GameState.States.DONE && game.getCurrent_state()!=GameState.States.GAME_OVER){
			cmd = askCommand();
			game.game(cmd);
			print(game.getMap());
            game.checkEvents();

            if(game.getCurrent_state() == GameState.States.MAP_DONE) game.getMap().setMap(map2);

            else if(game.getCurrent_state() == GameState.States.GAME_OVER) promptMsg(" \n GAME OVER");

            else if(game.getCurrent_state() == GameState.States.DONE) promptMsg("\n GAME OVER");

        }
		
	}

}
