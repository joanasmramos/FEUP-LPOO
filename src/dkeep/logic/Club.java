package dkeep.logic;

public class Club extends Object{
    private char dir;

    public Club(int line, int column, char type){
        super(line, column, type);
        this.setVisible(false);
    }

    public void setDir(char dir) {
        this.dir = dir;
    }

    public char getDir() {
        return dir;
    }

    public boolean isInKeyPos(int line, int column) {
        if (this.getLine() == line && this.column == column) {setChar('$'); return true; }
        else {
            setChar('*');
            return false;
        }
    }

}