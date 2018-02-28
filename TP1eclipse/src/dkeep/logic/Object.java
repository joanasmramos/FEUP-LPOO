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

class Key extends Object{
    private boolean picked_up;

    public Key(int line, int column, char type){
        super(line, column, type);
        this.picked_up = false;
    }

    @Override
    public char getChar() {
        if(picked_up) return ' ';
        else return super.type;
    }

    public void setPicked_up(boolean picked_up) {
        this.picked_up = picked_up;
    }
}