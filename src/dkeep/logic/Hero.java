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


    public boolean catchClub(Club club, char dir) {
        switch (dir){
            case 'w':
                if(this.getLine()==club.getLine()+1 && this.getColumn()==club.getColumn()) {
                	this.club=true;
                }
                break;
            case 's':
                if(this.getLine()==club.getLine()-1 && this.getColumn()==club.getColumn()) {
                	this.club=true;
                }
                break;
            case 'd':
                if(this.getLine()==club.getLine() && this.getColumn()==club.getColumn()-1) {
                	this.club=true;
                }
                break;
            case 'a':
                if(this.getLine()==club.getLine() && this.getColumn()==club.getColumn()+1) { 
                	this.club=true;
                }
                break;


        }
        return this.club;
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