package dkeep.logic;

public class Lever extends Object{
    boolean up;

    Lever(int line, int column){
        super(line, column, 'K');
        up = true;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    @Override
    public char getChar() {
        if(up) return super.getChar();
        else return 'L';
    }
}
