package dkeep.logic;

public class Object implements GameElement{
    protected int line;
    protected int column;
    protected char type;
    protected boolean visible;

    public Object(){this.visible=true;};

    public Object(int line, int column, char type){
        this.line = line;
        this.column = column;
        this.type = type;
        this.visible = true;
    }

    public boolean isVisible() {
        return visible;
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
    	if(visible)
    		return type;
    	else return ' ';
    }
    
    public void setVisible(boolean visible) {
    	this.visible = visible;
    }
}

class Club  extends Object{
    private char dir;
    
    public Club(int line, int column, char type){
        super.line = line;
        super.type = type;
        super.column = column;
        this.setVisible(false);
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


    public void setPicked_up(boolean picked_up) {
        this.picked_up = picked_up;
        super.visible = false;
    }
}