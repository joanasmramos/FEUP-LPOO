package dkeep.logic;
import java.util.Random;
import java.lang.String;

/**
 *
 */
public class Character implements GameElement{

    protected int line;
    protected int column;
    protected char charc;


    /**
     * Constructor for class Character
     * @param line line index of character in map
     * @param column column index of character in map
     * @param charc char of character in map
     */
   public Character(int line, int column, char charc) {
      this.line = line;
      this.column = column;
      this.charc = charc;
   }

    /**
     * @return line index of character in map
     */
   public int getLine(){
      return line;
   }

    /**
     * @return column index of character in map
     */
   public int getColumn(){
      return column;
   }

    /**
     * Changes character's coordinates according to parameters
     * @param line line index of character in map
     * @param column column index of character in map
     */
   public void setCoordinates(int line, int column){
      this.line = line;
      this.column = column;
   }

    /**
     *
     * @return char of character in map
     */
    public char getChar() {
        return charc;
    }

    /**
     * Updates character's coordinates according to which direction it moves in
     * @param dir direction of movement
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







	