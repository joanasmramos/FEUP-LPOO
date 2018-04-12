package dkeep.logic;

public interface GameElement {

     /**
     * @return returns the line number where the GameElement stands
     */
    int getLine();
    
     /**
     * @return returns the column number where the GameElement stands
     */
    int getColumn();
     
     /**
      * Sets the map coordinates of the GameElement
     * @param line: map line
     * @param column: map column
     */
    void setCoordinates(int line, int column);
}
