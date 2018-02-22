package dkeep.logic;
import java.util.Random;

public class Character {

   private int line;
   private int column;

   public Character() {
   }

   public Character(int line, int column) {
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
}

class Hero extends Character{

   private boolean key;

   public Hero(int line, int column) {
      key = false;
   }

   public void setKey(boolean key){
      this.key = key;
   }

   public boolean getKey(){
      return this.key;
   }

}

class Ogre extends Character{
   private int line_club;
   private int column_club;
   Random rand = new Random();


   public Ogre() { }

   public void setClubCoordinates(int line, int column){
      this.line_club = line;
      this.column_club = column;
   }

   public int getClubLine(){
      return line_club;
   }

   public int getClubColumn(){
      return column_club;
   }

   public Random getRand() {
      return rand;
   }
}