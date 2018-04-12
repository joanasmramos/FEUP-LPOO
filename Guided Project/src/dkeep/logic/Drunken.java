package dkeep.logic;

public class Drunken extends Guard{

    /**
     * Constructs a drunken guard in a given position
     * @param line: map line of the guard
     * @param column: map column of the guard
     * @param charc: char that represents the guard internally
     */
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
