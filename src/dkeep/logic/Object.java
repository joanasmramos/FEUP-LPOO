package dkeep.logic;

public class Object implements GameElement{
    protected int line;
    protected int column;
    protected char type;
    protected boolean visible;

    public Object(){
    	this.visible=true;
    	}

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

    public void setChar(char c){
        this.type = c;
    }
    
    public void setVisible(boolean visible) {
    	this.visible = visible;
    }

}
