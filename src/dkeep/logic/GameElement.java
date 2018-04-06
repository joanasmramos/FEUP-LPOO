package dkeep.logic;

/**
 *
 */
public interface GameElement {

    /**
     * @return line line index of character in map
     */
     int getLine();

    /**
     *
     * @return column index of character in map
     */
     int getColumn();
     
     void setCoordinates(int line, int column);
}
