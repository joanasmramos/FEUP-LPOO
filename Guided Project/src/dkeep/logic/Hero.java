package dkeep.logic;

import dkeep.cli.Interaction;

public class Hero extends Character{

    private boolean key;
    public char charc;
    private boolean club;
    private boolean movedOntoDoor;

    /**
     * Constructs a new hero in the given position, represented internally by the given char; initially the hero doesn't have the key
     * @param line: hero's map line
     * @param column: hero's column line
     * @param charc: representative char
     */
    public Hero(int line, int column, char charc) {
        super(line, column, charc);
        key = false;
        movedOntoDoor = false;
    }
    
    /**
     * Constructs a new hero in the position (1,1), represented internally by the given char
     * @param charc: representative char
     */
    public Hero(char charc) {
    	super(1, 1, charc);
    	key = false;
    	movedOntoDoor = false;
    }

    public char getChar() {
        if(key) return 'K';
        else if(club) return 'A';
        else return 'H';
    }

    /**
     * Sets representative char to the given one
     * @param symbol: given char
     */
    public void setChar(char symbol) {
        this.charc = symbol;
    }

    /**
     * Sets key to true/false
     * @param key: true: hero has the key, false: hero doesn't have the key
     */
    public void setKey(boolean key){
        this.key = key;
    }

    /**
     * Calls catchClub to check if the hero will catch his club if he moves in the given direction
     * @param club: hero's pick up club
     * @param dir: direction to evaluate
     * @return true if caught, false otherwise
     */
    public boolean checkIfCatchClub(Club club, char dir) {
        switch (dir){
            case 'w':
                catchClub(club.getLine()+1,club.getColumn());
                break;
            case 's':
                catchClub(club.getLine()-1, club.getColumn());
                break;
            case 'd':
                catchClub(club.getLine(), club.getColumn()-1);
                break;
            case 'a':
                catchClub(club.getLine(), club.getColumn()+1);
                break;
        }return this.club;
    }

    /**
     * Checks if the hero is in the club's position, updates this.club accordingly
     * @param line: club's line
     * @param column: club's column
     */
    public void catchClub(int line, int column){
        if(this.getLine()==line && this.getColumn()==column) {
            this.club=true; }
    }

    /**
     * @return true if the hero has the club, false otherwise
     */
    public boolean HasCub(){
        return this.club;
    }

    /**
     * @return true if the hero has the key, false otherwise
     */
    public boolean HasKey(){
        return this.key;
    }

    /**
     * Sets club
     * @param club: true if hero has the club, false otherwise
     */
    public void setClub(boolean club) {
        this.club = club;
    }

    /**
     * Updates hero's coordinates after moving it in the given direction
     * @param dir: direction of movement
     */
    public void moveChar(char dir) {
        switch (dir) {
            case 'w':
                this.line--;
                return;
            case 's':
                this.line++;
                return;
            case 'd':
                this.column++;
                return;
            case 'a':
                this.column--;

            default:
        }
    }


    /**
     * Checks if the hero is in the given position
     * @param line: line to check
     * @param col: column to check
     * @return returns true if hero stands in the given position, false otherwise
     */
    public boolean checkIfCaught(int line, int col) {
        if(this.line == line)
            if(this.column+1 == col || this.column-1 == col || this.column == col)
                return true;

        if(this.column == col)
            if(this.line+1 == line || this.line-1 == line   )
                return true;

        return false;
    }

}