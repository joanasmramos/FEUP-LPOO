package dkeep.cli;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.Character;
import dkeep.logic.*;
import dkeep.logic.Object;
import dkeep.gui.DungeonKeep;

public class Interaction {
	
	private static Scanner scanner = new Scanner(System.in);
	private static Random rand = new Random();
	private int nrOgres;
	private int guardType;
    static int print_char = 0;
    static int print_obj = 0;
	
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
            { 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
            { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
    };


    public static void print(Map map) {
        print_char = 0;
        print_obj = 0;

        for (int i = 0; i < map.getN_lines(); i++) {
            for (int j = 0; j < map.getColumns(); j++) {

                printCharacters(map, i,j);

                if(print_char==0 && print_obj==0) System.out.print(map.getMap()[i][j] + " ");

                print_char = 0;
                print_obj = 0;

            }
            System.out.println();
        }
    }

    public static void printCharacters(Map map, int i, int j){
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
    }


    public static char[][] printToString(Map map) {
    	char[][] mapstr = new char[map.getMap().length][map.getMap()[0].length];
        print_char = 0;
         print_obj = 0;

        for (int i = 0; i < map.getN_lines(); i++) {
            for (int j = 0; j < map.getColumns(); j++) {

                printGameElementsToString(map, mapstr, i, j);

                if(print_char==0 && print_obj==0) mapstr[i][j] =  map.getMap()[i][j];

                print_char = 0;
                print_obj = 0;

            }
        }
        
        return mapstr;
    }

    public static void printGameElementsToString(Map map, char[][] mapstr, int i, int j){
        for (dkeep.logic.Character charac : map.getChars()) {
            if(i==charac.getLine() && j==charac.getColumn()) {
                if(charac instanceof Hero && charac.getChar()=='K')
                    mapstr[i][j] = 'A';
                else mapstr[i][j] = charac.getChar();
                print_char++;
                break;
            }
        }

        for(Object obj : map.getObjs()) {
            if(i==obj.getLine() && j==obj.getColumn()) {
                if(obj.isVisible()) {
                    if(print_char>0) j++;
                    mapstr[i][j] =  obj.getChar();
                    print_obj++;
                    break;
                }
            }
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
    
    
    
    public Interaction(String nrOgres, int guardType) {
    	this.nrOgres = Integer.parseInt(nrOgres);
    	this.guardType = guardType;
    }
    
    public GameState Dungeon() {
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.getMap().remChar(game.getGuard());
        game.setNrOgres(nrOgres);

        setGuard(game);

		print(game.getMap());
		
		
		return game;
    }

    public void setGuard(GameState game){
        switch(this.guardType) {
            case 0:
                game.setGuard(new Rookie(1, 8, 'G'));
                break;
            case 1:
                game.setGuard(new Drunken(1, 8, 'G'));
                break;
            case 2:
                game.setGuard(new Suspicious(1, 8, 'G'));
                break;
        }
        game.getMap().addChar(game.getGuard());
    }

	
	public static void main(String args[]) {

		char cmd;

        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.setNrOgres(rand.nextInt(4)+1);

		print(game.getMap());

		while(game.getCurrent_state()!= GameState.States.DONE && game.getCurrent_state()!=GameState.States.GAME_OVER){
			cmd = askCommand();
			game.game(cmd);
			print(game.getMap());
            game.checkEvents();

            if(game.getCurrent_state() == GameState.States.GAME_OVER) promptMsg(" \n GAME OVER");

            else if(game.getCurrent_state() == GameState.States.DONE) promptMsg("\n GAME DONE");

        }
		
	}

}
