package dkeep.logic;

import java.util.Random;

public class Guard extends Character{

    private char guardTraject[] = {'A','S','S','S','S','A','A','A','A','A', 'A', 'S','D','D','D','D','D','D','D',
            'W','W','W','W','W'};
    private int trajInd = 0;
    private boolean asleep;
    private boolean reverse;
    private static Random rand = new Random();
    private char reverseTraject[] = {'s', 'd', 'w', 'w', 'w', 'w', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'a', 'a','a', 'a', 'a', 'a', 'a', 's', 's', 's', 's', 's'};


    public Guard(int line, int column, char charc) {
        super(line, column, charc);
        asleep = false;
        reverse = false;
    }

    public int getInd(){return trajInd;}

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

    public char getDir(int i){return guardTraject[i];}

    public void setAsleep(boolean asleep) {
        this.asleep = asleep;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public boolean generateBool() {
        int trigger = rand.nextInt(1);
        if(trigger == 0)
            return true;
        else return false;
    }

    public void moveChar(){
        if(asleep) {
            charc = 'g';
            return;
        }
        else if(reverse) {
            switch(reverseTraject[trajInd]) {
                case 'w':
                    this.line--;
                    break;
                case 's':
                    this.line++;
                    break;
                case 'd':
                    this.column++;
                    break;
                case 'a':
                    this.column--;
                    break;
            }
            decInd();
        }
        else {
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
}