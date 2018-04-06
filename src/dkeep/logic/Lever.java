package dkeep.logic;

public class Lever extends Object{
    private boolean up;

    /**
     * Constructs a new lever in the given position
     * @param line: lever's line
     * @param column: lever's column
     */
    Lever(int line, int column){
        super(line, column, 'K');
        up = true;
    }

    /**
     * Sets the lever up/down
     * @param up: true:up, false:down
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * @return true if the lever is up, false if the lever is down
     */
    public boolean isUp(){return up;}

}
