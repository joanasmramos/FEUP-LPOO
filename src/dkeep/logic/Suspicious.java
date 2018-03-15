package dkeep.logic;

public class Suspicious extends Guard {

    public Suspicious(int line, int column, char charc) {
        super(line, column, charc);
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }
    

    @Override
    public void moveChar(){

        this.reverse = super.generateBool();

        if(reverse) {
            switch(reverseTraject[trajInd]) {
                case 'w':
                    this.line--;
                    break;
                case 's':
                    this.line++;
                    break;
                case 'd':
                    this.column++;
                    break;
                case 'a':
                    this.column--;
                    break;
            }
            decInd();
        }
        else {
            super.moveChar();
        }
    }
}
