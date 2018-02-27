package dkeep.logic;
import java.util.Random;

public class Character {

    protected int line;
    protected int column;
    protected char charc;

   public Character() {
   }

   public Character(int line, int column, char charc) {
      this.line = line;
      this.column = column;
      this.charc = charc;
   }

   public int getLine(){
      return line;
   }

   public int getColumn(){
      return column;
   }

   public void setCoordinates(int line, int column){
      this.line = line;
      this.column = column;
   }

    public char getChar() {
        return charc;
    }
}

class Hero extends Character{

   private boolean key;
   public char charc;



   public Hero(int line, int column, char charc) {
       super(line, column, charc);
      key = false;
   }


    public char getChar() {
        if(key) return 'K';
        else return 'H';
    }

    public void setChar(char symbol) {
    	this.charc = symbol;
    }
    
   public void setKey(boolean key){
      this.key = key;
   }

   public boolean HasKey(){
      return this.key;
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
            if(this.column+1 == col || this.column-1 == col)
                return true;

        if(this.column == col)
            if(this.line+1 == line || this.line-1 == line)
                return true;

        return false;
    }

}

class Ogre extends Character {
    private int line_club;
    private int column_club;
    private Random rand = new Random();


    public Ogre(int line, int column, char charc) {
        super(line, column, charc);
    }

    public void setClubCoordinates(int line, int column) {
        this.line_club = line;
        this.column_club = column;
    }

    public int getClubLine() {
        return line_club;
    }

    public int getClubColumn() {
        return column_club;
    }

    public Random getRand() {
        return rand;
    }


    public void throwClub() {
        switch (rand.nextInt()) {
            case 'w':
                this.line_club = this.line - 1;
                this.column_club = this.column;
                return;
            case 's':
                this.line_club = this.line + 1;
                this.column_club = this.column;
                return;
            case 'd':
                this.line_club = this.line;
                this.column_club = this.column + 1;
                return;
            case 'a':
                this.line_club = this.line;
                this.column_club = this.column - 1;
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
    		default: return 'z';
    	}
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
                return;

                default: return;
        }
    }
}


class Guard extends Character{

   private char guardTraject[] = {'A','S','S','S','S','A','A','A','A','A', 'A', 'S','D','D','D','D','D','D','D',
           'W','W','W','W','W'};
   private int trajInd = 0;


   public Guard(int line, int column, char charc) {super(line, column, charc);}

   public int getInd(){return trajInd;}

   public void incInd(){if(trajInd==guardTraject.length-1){
       trajInd = 0;
   }else trajInd++;;}

    public char getDir(int i){return guardTraject[i];}

    public void moveChar(){

       switch (guardTraject[trajInd]){
           case 'W':
               this.line--;
               return;
           case 'S':
               this.line++;
               return;
           case 'D':
               this.column++;
               return;
           case 'A':
               this.column--;

           default:
       }
    }
}