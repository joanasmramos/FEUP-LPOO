package dkeep.logic;
import java.util.Random;
import java.lang.String;

public class Character implements GameElement{

    protected int line;
    protected int column;
    protected char charc;



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
    
    public void updateCoords(char dir) {
    	String direc = String.valueOf(dir);
    	direc.toLowerCase();
    	
        switch (direc) {
        case "w":
            this.line--;
            break;
        case "s":
            this.line++;
            break;
        case "d":
            this.column++;
            break;
        case "a":
            this.column--;
            break;
    }
    }


}







	