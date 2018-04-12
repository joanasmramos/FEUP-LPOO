package dkeep.logic;

import java.util.Random;

public abstract class Guard extends Character{

    protected boolean asleep;
    protected boolean reverse;

    protected char guardTraject[] = {'a','s','s','s','s','a','a','a','a','a', 'a', 's','d','d','d','d','d','d','d',
            'w','w','w','w','w'};
    protected int trajInd = 0;
    protected static Random rand = new Random();
    protected char reverseTraject[] = {'s', 'd', 'w', 'w', 'w', 'w', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'a', 'a','a', 'a', 'a', 'a', 'a', 's', 's', 's', 's', 's'};


    /**
     * Constructs a guard in the given position, represented by the given char
     * @param line: map line of the guard
     * @param column: map column of the guard
     * @param charc: representative char
     */
    public Guard(int line, int column, char charc) {
        super(line, column, charc);
        asleep = false;
        reverse = false;
    }

    /**
     * @return returns true if the guard is reverse walking, false otherwise
     */
    public boolean isReverse() {
        return reverse;
    }

    /**
     * @return returns true if the guard is asleep, false otherwise
     */
    public boolean isAsleep(){
    	return this.asleep;
    }

    /**
     * Sets member asleep (is asleep = true, is awake = false)
     * @param asleep
     */
    public void setAsleep(boolean asleep) {
        this.asleep = asleep;
    }


    /**
     * Increments index of the trajectory
     */
    public void incInd(){
        if(trajInd==guardTraject.length-1){
            trajInd = 0;
        }
        else trajInd++;
    }

    /**
     * Decrements index of the trajectory
     */
    public void decInd() {
        if(trajInd == 0) {
            trajInd = guardTraject.length - 1;
        }
        else trajInd--;
    }

    /**
     * Generates a random boolean
     * @return returns the generated boolean;
     */
    public boolean generateBool() {
        int trigger = rand.nextInt(2);
        if(trigger == 0)
            return true;
        else return false;

    }

    /**
     * Updates guard's coordinates with the direction stored in traject Array and the current index; updates index afterwards
     */
    public void moveChar(){
            updateCoords(guardTraject[trajInd]);
            incInd();
        }
}