package dkeep.logic;

import java.util.Random;

import dkeep.logic.GameState.States;

public class Ogre extends Character {
    private boolean stunned = false;
    private int turnsLeft = 2;
    private static Random rand = new Random();
    private char dir;
    private Club ogre_club = new Club(super.line,super.column,'*');

    public Ogre(int line, int column, char charc) {
        super(line, column, charc);
        stunned = false;
        turnsLeft = 2;
    }

    public void setStunned(boolean stun) {
        stunned = stun;
    }

    public boolean getStunned() {
        return stunned;
    }


    public static int generateNr(int n1, int n2) {
        return rand.nextInt(n2 - n1 + 1) + n1;
    }


    public void setTurns(int turns) {
        this.turnsLeft = turns;
    }

    public int getTurns() {
        return turnsLeft;
    }


    public char getChar() {
        if(stunned)
            return '8';
        else return 'O';
    }


    public char getDir() {
        return dir;
    }

    public Club getOgre_club() {
        return ogre_club;
    }

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

    public void setOgreDir(char dir){
        this.dir = dir;
    }

    public void moveChar(char dir) {
    	updateCoords(dir);

        this.getOgre_club().setCoordinates(this.line, this.column);
    }
    
}