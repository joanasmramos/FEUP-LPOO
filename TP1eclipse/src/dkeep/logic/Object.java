package dkeep.logic;

public class Object {
    private int line;
    private int column;
    private char type;

    public Object(int line, int column){
        this.line = line;
        this.column = column;
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

    public char getType(){
        return type;
    }
}

