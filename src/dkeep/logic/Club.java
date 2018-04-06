package dkeep.logic;

public class Club extends Object{
    private char dir;

    /** Constructs a club in a given position
     * @param line: map line of the club
     * @param column: map column of the club
     * @param charo: char that represents the object internally
     */
    public Club(int line, int column, char charo){
        super(line, column, charo);
    }
    
    /**
     * Constructs a club represented by the given char
     * @param type: representative char
     */
    public Club(char type){
        super(1, 1, type);
        this.setVisible(true);
    }

    /**
     * Sets the direction for the next movement of the club
     * @param dir: direction
     */
    public void setDir(char dir) {
        this.dir = dir;
    }

    /**
     * Returns the direction of the movement of the club
     * @return dir: direction
     */
    public char getDir() {
        return dir;
    }

    /**
     * Checks if the club is in the same position as the key and changes its representative char accordingly
     * @param line: map line where the key stands
     * @param column: map column where the key stands
     * @return true if the club is in the key position, false otherwise
     */
    public boolean isInKeyPos(int line, int column) {
        if (this.getLine() == line && this.column == column) {setChar('$'); return true; }
        else {
            setChar('*');
            return false;
        }
    }

}