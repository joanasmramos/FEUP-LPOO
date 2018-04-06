package dkeep.logic;

public class Object implements GameElement, java.io.Serializable{
    protected int line;
    protected int column;
    protected char charo;
    protected boolean visible;

    /**
     * Constructs a visible object
     */
    public Object(){
    	this.visible=true;
    	}

    /**
     * Constructs an object in the given position, represented internally by the given char
     * Sets visible to true
     * @param line: object's line
     * @param column: object's column
     * @param charo: object's representative char
     */
    public Object(int line, int column, char charo){
        this.line = line;
        this.column = column;
        this.charo = charo;
        this.visible = true;
    }

    /**
     * @return true if object is visible, false otherwise
     */
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

    /**
     * @return returns object's representative char
     */
    public char getChar(){
    	if(visible)
    		return charo;
    	else return ' ';
    }

    /**
     * Sets the object's representative char
     * @param c: new char
     */
    public void setChar(char c){
        this.charo = c;
    }
    
    /**
     * Sets visible
     * @param visible: true if object's visible, false otherwise
     */
    public void setVisible(boolean visible) {
    	this.visible = visible;
    }

}
