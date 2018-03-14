package dkeep.logic;

public class Drunken extends Guard{

    public Drunken(int line, int column, char charc) {
        super(line, column, charc);
    }

    public void moveChar(){

        asleep = super.generateBool();

        if(asleep) {
            charc = 'g';
            return;
        }

        else {
            charc = 'G';
            super.moveChar();
        }
    }
}
