package dkeep.logic;

import java.util.Random;

public abstract class Guard extends Character{

    protected boolean asleep;
    protected boolean reverse;

    protected char guardTraject[] = {'A','S','S','S','S','A','A','A','A','A', 'A', 'S','D','D','D','D','D','D','D',
            'W','W','W','W','W'};
    protected int trajInd = 0;
    protected static Random rand = new Random();
    protected char reverseTraject[] = {'s', 'd', 'w', 'w', 'w', 'w', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'a', 'a','a', 'a', 'a', 'a', 'a', 's', 's', 's', 's', 's'};


    public Guard(int line, int column, char charc) {
        super(line, column, charc);
        asleep = false;
        reverse = false;
    }

    public boolean isReverse() {
        return reverse;
    }

    public boolean isAsleep(){return this.asleep;}

    public void setAsleep(boolean asleep) {
        this.asleep = asleep;
    }


    public void incInd(){
        if(trajInd==guardTraject.length-1){
            trajInd = 0;
        }
        else trajInd++;
    }

    public void decInd() {
        if(trajInd == 0) {
            trajInd = guardTraject.length - 1;
        }
        else trajInd--;
    }



    public boolean generateBool() {
        int trigger = rand.nextInt(2);
        if(trigger == 0)
            return true;
        else return false;

    }

    public void moveChar(){
            switch(guardTraject[trajInd]) {
                case 'W':
                    this.line--;
                    break;
                case 'S':
                    this.line++;
                    break;
                case 'D':
                    this.column++;
                    break;
                case 'A':
                    this.column--;
                    break;
            }
            incInd();
        }
}