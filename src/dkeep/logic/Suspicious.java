package dkeep.logic;

public class Suspicious extends Guard {

    public Suspicious(int line, int column, char charc) {
        super(line, column, charc);
    }

    @Override
    public void moveChar(){

        this.reverse = super.generateBool();

        if(reverse) {
            updateCoords(reverseTraject[trajInd]);
            decInd();
        }
        else {
            super.moveChar();
        }
    }
}
