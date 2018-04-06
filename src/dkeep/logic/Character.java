package dkeep.logic;
import java.util.Random;
import java.lang.String;

public class Character implements GameElement, java.io.Serializable{

    protected int line;
    protected int column;
    protected char charc;

    
   /**
    * Constructs a character in a certain position of the map
    * @param line: map line of the character
    * @param column: map column of the character
    * @param charc: char that represents the character internally
    */
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

    /**
     * @return returns the char that represents the character internally
     */
    public char getChar() {
        return charc;
    }
    
    /**
     * This function will update the coordinates of the character given his current position and the direction of his movement (up, down, left or right)
     * @param dir: direction of the movement. w: up, s: down, a: left, d: right
     */
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







	