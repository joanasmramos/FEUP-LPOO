package dkeep.logic;

public class Object {
    protected int line;
    protected int column;
    protected char type;

    public Object(){};

    public Object(int line, int column, char type){
        this.line = line;
        this.column = column;
        this.type = type;
    }

    public int getLine(){
        return line;
    }

    public int getColumn(){
        return column;
    }

    public void setCoordinates(int line, int column){
        this.line = line;
        this.column = column;
    }

    public char getChar(){
        return type;
    }
}

class Club  extends Object{
    private char dir;

    public Club(int line, int column, char type){
        super.line = line;
        super.type = type;
        super.column = column;
    }

    public void setDir(char dir) {
        this.dir = dir;
    }

    public char getDir() {
        return dir;
    }
}
