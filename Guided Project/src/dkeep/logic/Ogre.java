package dkeep.logic;

import java.util.Random;
import dkeep.logic.*;

import dkeep.logic.GameState.States;

public class Ogre extends Character {
    private boolean stunned = false;
    private int turnsLeft = 2;
    private static Random rand = new Random();
    private char dir;
    private Club ogre_club = new Club(super.line,super.column,'*');
    
    /**
     * Constructs an ogre in the given position, represented by the given char
     * Sets stunned to false (ogre's not stunned)
     * Sets turnsLeft to 2 (if the ogre gets stunned, he will remain stunned for two turns)
     * @param line: ogre's line
     * @param column: ogre's column
     * @param charc: ogre's representative char
     */
    public Ogre(int line, int column, char charc) {
        super(line, column, charc);
        stunned = false;
        turnsLeft = 2;
    }

    /**
     * Constructs an ogre represented by the given char
     * Sets stunned to false (ogre's not stunned)
     * Sets turnsLeft to 2 (if the ogre gets stunned, he will remain stunned for two turns)
     * @param charc: ogre's representative char
     */
    public Ogre(char charc) {
		super(1, 3, charc);
		stunned = false;
		turnsLeft = 2;
	}

	/**
	 * Sets stunned
	 * @param stun: true if stunned, false otherwise
	 */
	public void setStunned(boolean stun) {
        stunned = stun;
    }

    /**
     * @return returns flag stunned
     */
    public boolean getStunned() {
        return stunned;
    }


    /**
     * Generates an integer in the given interval
     * @param n1: lower bound
     * @param n2: upper bound
     * @return
     */
    public static int generateNr(int n1, int n2) {
        return rand.nextInt(n2 - n1 + 1) + n1;
    }


    /**
     * Sets number of turns left
     * @param turns: turns left
     */
    public void setTurns(int turns) {
        this.turnsLeft = turns;
    }

    /**
     * @return returns number of turns left
     */
    public int getTurns() {
        return turnsLeft;
    }

    public char getChar() {
        if(stunned)
            return '8';
        else return 'O';
    }


    /**
     * @return returns direction of the ogre's movement
     */
    public char getDir() {
        return dir;
    }

    /**
     * @return returns ogre's club
     */
    public Club getOgre_club() {
        return ogre_club;
    }

    /**
     * Throws ogre's club in a given direction and updates its coordinates
     * @param dir: direction for the club's movement
     */
    public void throwClub(char dir) {
        this.getOgre_club().setVisible(true);

        switch (java.lang.Character.toLowerCase(dir)) {
            case 'w':
                this.ogre_club.setCoordinates(this.line - 1,this.column);
                return;
            case 's':
                this.ogre_club.setCoordinates(this.line + 1,this.column);
                return;
            case 'd':
                this.ogre_club.setCoordinates(this.line,this.column+1);
                return;
            case 'a':
                this.ogre_club.setCoordinates(this.line ,this.column-1);
        }
    }

    /**
     * Generates a direction
     * @return returns generated direction
     */
    public char generateDir() {
        switch(rand.nextInt(4)){
            case 0:
                return 'w';
            case 1:
                return 's';
            case 2:
                return 'd';
            case 3:
                return 'a';
            default: return ' ';
        }
    }

    /**
     * Sets ogre's direction of movement
     * @param dir: new direction
     */
    public void setOgreDir(char dir){
        this.dir = dir;
    }

    /**
     * Updates ogre's coordinates according to its movement, updates club
     * @param dir: direction of the movement
     */
    public void moveChar(char dir) {
    	updateCoords(dir);

        this.getOgre_club().setCoordinates(this.line, this.column);
    }


    /**
     * Updates turns left according to the stunned flag
     * @return
     */
    public int manageTurns(){

            if(getTurns() != 0) {
                setTurns(getTurns()-1);
                return 1;
            }
            else {
                setStunned(false);
                setTurns(2);
            }
            return 0;
    }
}