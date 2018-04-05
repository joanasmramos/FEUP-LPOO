package dkeep.logic;

public class Lever extends Object{
    private boolean up;

    Lever(int line, int column){
        super(line, column, 'K');
        up = true;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isUp(){return up;}

    @Override
    public char getChar() {
        return super.getChar();
    }
}
