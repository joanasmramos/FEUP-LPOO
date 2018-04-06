package dkeep.logic;

import dkeep.cli.Interaction;

public class Hero extends Character{

    private boolean key;
    public char charc;
    private boolean club;
    private boolean movedOntoDoor;

    public Hero(int line, int column, char charc) {
        super(line, column, charc);
        key = false;
        movedOntoDoor = false;
    }
    
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

    public void setChar(char symbol) {
        this.charc = symbol;
    }

    public void setKey(boolean key){
        this.key = key;
    }


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

    public void catchClub(int line, int column){
        if(this.getLine()==line && this.getColumn()==column) {
            this.club=true; }
    }

    public boolean HasCub(){
        return this.club;
    }

    public boolean HasKey(){
        return this.key;
    }

    public void setClub(boolean club) {
        this.club = club;
    }

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