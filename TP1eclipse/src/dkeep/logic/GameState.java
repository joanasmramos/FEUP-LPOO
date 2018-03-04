package dkeep.logic;
import java.util.HashSet;

import java.util.ArrayList;

public class GameState {
    public Map map;
    Hero hero = new Hero(1,1, 'H');
    Guard guard = new Guard(1, 8, 'G');
    Ogre ogre = new Ogre(5, 1, 'O');
    HashSet<Ogre> ogres = new HashSet<Ogre>(7);
    Key key = new Key(1,8,'k');
    Club club = new Club(8, 2, 'C');

    ArrayList<Character> chars = new ArrayList<Character>();

    private int level;

    public enum States { DONE, GAME_OVER, PLAYING;}
    public enum Events { EMPTY, EXIT;}

    private States current_state = States.PLAYING;
    private Events current_event=Events.EMPTY;

    public GameState() {
        level = 1;
        chars.add(hero);
        chars.add(guard);
        guard.setAsleep(false);
        guard.setReverse(false);
    }

    public int[] getHeroPos() {
        int pos[] = {hero.getLine(), hero.getColumn()};
        return pos;
    }

    public Map getMap(){return map;}

    public void setMap(Map map) {
        this.map = map;
        this.map.setChars(this.chars);
    }

    public States getCurrent_state(){return current_state;}

    public void promptMsg(String message) {
        System.out.println(message);
    }

    public void checkEvents(char dir){
        switch(level){
            case 1:
                if(hero.checkIfCaught(guard.getLine(), guard.getColumn())) {
                    current_state = States.GAME_OVER;
                    promptMsg("GAME OVER");
                }
                break;
            case 2:
            	
            	for(Ogre o: ogres) {
                if(hero.checkIfCaught(o.getLine(), o.getColumn())) {
                	if(hero.hasClub()) {
                		o.setStunned(true);
                		o.setTurns(2);
                	}
                	else {
                    current_state = States.GAME_OVER;
                    promptMsg("GAME OVER");
                	}
                }	
            	}
                
                
                
                if(hero.checkIfCaught(ogre.getOgre_club().getLine(), ogre.getOgre_club().getColumn())) {
                    current_state = States.GAME_OVER;
                    promptMsg("GAME OVER");
                }

                if(current_event == Events.EXIT) {
                    current_state = States.DONE;
                    promptMsg("YOU WIN");
                }
                break;
        }
    }



    public void game(char user_input){
        switch (level){
            case 1:
                updatePos(user_input);
                map.print();
                break;
            case 2:
                updatePos(user_input);
                map.print();
                break;

        }
    }


    public boolean checkObstacle(Character character, char obstacle, char dir) {
        switch (dir) {
            case 'w':
                return (map.getMap()[character.getLine()-1][character.getColumn()] == obstacle);
            case 's':
                return (map.getMap()[character.getLine()+1][character.getColumn()] == obstacle);
            case 'd':
                return (map.getMap()[character.getLine()][character.getColumn()+1] == obstacle);
            case 'a':
                return (map.getMap()[character.getLine()][character.getColumn()-1] == obstacle);

            default: return true;
        }
    }

    public boolean checkObstacle(Object object, char obstacle, char dir) {
        switch (dir) {
            case 'w':
                return (map.getMap()[object.getLine()-1][object.getColumn()] == obstacle);
            case 's':
                return (map.getMap()[object.getLine()+1][object.getColumn()] == obstacle);
            case 'd':
                return (map.getMap()[object.getLine()][object.getColumn()+1] == obstacle);
            case 'a':
                return (map.getMap()[object.getLine()][object.getColumn()-1] == obstacle);

            default: return true;
        }
    }


    //NOTE: FIND A WAY TO MAKE THIS LESS SPAGHETTI
    public  void updatePos(char dir) {
        switch (this.level) {
            case 1:
                if (moveHero(dir) != 1) {
                    guard.moveChar();
                }
                break;

            case 2:
                if (moveHero(dir) != 1) {
                    moveOgre(ogre);

                    if (moveHero(dir) != 1) {
                        for (Ogre o : ogres) {
                            moveOgre(o);
                        }
                    }
                    break;
                }
        }
    }

    public int moveHero(char dir){
        switch (this.level){
            case 1:
                if(!checkObstacle(hero, 'I',dir) && !checkObstacle(hero, 'X',dir)){

                    if(checkObstacle(hero, 'S', dir)) levelup();

                    else {
                        if (checkObstacle(hero, 'K', dir))
                            map.openDoors();
                        else hero.moveChar(dir);
                    }
                }else  {
                	promptMsg("Cannot move there.");
                	return 1;
                }

                break;

            case 2:
                if(!checkObstacle(hero, 'I',dir) && !checkObstacle(hero, 'X',dir)) {
                	if(checkObstacle(hero, 'C', dir)) {
                		hero.setClub(true);
                		//map.remObj(club);
                		
                	}
                    hero.moveChar(dir);
                }
                else if(checkObstacle(hero, 'I', dir) && hero.HasKey()) {
                    hero.moveChar(dir);
                    levelup();
                }
                else {
                    promptMsg("Cannot move there.");
                    return 1;
                }

                if(key.getLine() == hero.getLine() && key.getColumn() == hero.getColumn()) {
                    hero.setKey(true);
                    hero.setChar('K');
                    key.setPicked_up(true);
                }

                break;
        }
        return 0;
    }

    public void moveOgre(Ogre ogre){
    	
    	if(ogre.getStunned()) {
    		ogre.setTurns(ogre.getTurns()-1);
    	}
    	
        //move Ogre
        ogre.setOgreDir(ogre.generateDir());

        //generate a direction possible for ogre to move to
        while(checkObstacle(ogre, 'I',ogre.getDir()) || checkObstacle(ogre, 'X',ogre.getDir())){
            ogre.setOgreDir(ogre.generateDir());
        }

        ogre.moveChar(ogre.getDir());


        //move ogre's club
        ogre.getOgre_club().setDir(ogre.generateDir());

        //generate a direction possible for ogre's club to move to
        while(checkObstacle(ogre.getOgre_club(), 'I',ogre.getOgre_club().getDir()) || checkObstacle(ogre.getOgre_club(), 'X',ogre.getOgre_club().getDir())){
            ogre.getOgre_club().setDir(ogre.generateDir());
        }

        ogre.throwClub(ogre.getOgre_club().getDir());
    }


    public void levelup(){
        switch (this.level){
            case 1:
                level++;
                map.setMap(2);
                hero.setCoordinates(8 ,1);
                hero.setKey(false);
                ogres.add(ogre);
                int nr = Ogre.generateNr(0, 4);
                map.addObj(club);
                map.addObj(key);
                map.addObj(ogre.getOgre_club());
                map.remChar(guard);
                
                for(int i=0; i<nr; i++) {
                	Ogre anotherOgre = new Ogre(Ogre.generateNr(1, 6),
                			Ogre.generateNr(1, this.map.getColumns(0)), 'O');
                	ogres.add(anotherOgre);
                	map.addChar(anotherOgre);
                	map.addObj(anotherOgre.getOgre_club());
                }
                
                break;
            case 2:
                current_event = Events.EXIT;
        }

    }
}
