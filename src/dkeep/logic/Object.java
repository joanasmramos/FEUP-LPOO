package dkeep.logic;

public class Object implements GameElement{
    protected int line;
    protected int column;
    protected char charo;
    protected boolean visible;

    public Object(){
    	this.visible=true;
    	}

    public Object(int line, int column, char charo){
        this.line = line;
        this.column = column;
        this.charo = charo;
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
    		return charo;
    	else return ' ';
    }

    public void setChar(char c){
        this.charo = c;
    }
    
    public void setVisible(boolean visible) {
    	this.visible = visible;
    }

}
