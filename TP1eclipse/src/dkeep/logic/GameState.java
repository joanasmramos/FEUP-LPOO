package dkeep.logic;


import java.util.Random;

public class GameState {
    Map map  = new Map(1);
    Hero hero = new Hero(1,1, 'H');
    Guard guard = new Guard(1, 8, 'G');
    Ogre ogre = new Ogre(2, 1, 'O');

    private Character[][] characters = {{hero, guard}, {hero, ogre}};

    private int level;

    public enum Events {CAUGHT, OPEN_DOORS;}
    public enum States {VICTORY, DONE, GAME_OVER, PLAYING;}

    private Events current_event;
    private States current_state;

    public GameState() {
        map.setMap(1);
        level = 1;
        current_state=States.PLAYING;
        map.printMap(characters);
    }

    public Map getMap(){return map;}

    public States getCurrent_state(){return current_state;}

    public void promptMsg(String message) {
        System.out.println(message);
    }

    public void checkEvents(){
        switch(level){
            case 1:
                if(hero.checkIfCaught(guard)) {
                    current_state = States.GAME_OVER;
                    promptMsg("GAME OVER");
                }
                break;
            case 2:
                if(hero.checkIfCaught(ogre)) {
                    current_state = States.GAME_OVER;
                    promptMsg("GAME OVER");
                }
                if(current_event == Events.OPEN_DOORS){ current_state = States.DONE;}
                break;
        }
    }



    public void game(char user_input){
        switch (level){
            case 1:
                updatePos(1,user_input);
                map.printMap(characters);
                break;
            case 2:
                updatePos(2,user_input);
                map.printMap(characters);
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


    public  void updatePos(int id, char dir){
        switch (id){
            case 1:
                if(!checkObstacle(hero, 'I',dir) && !checkObstacle(hero, 'X',dir)){
                    if(checkObstacle(hero, 'S', dir))
                        levelup(1);

                    if(checkObstacle(hero, 'K',dir))
                        map.openDoors();
                    else hero.moveChar(dir);
                }else promptMsg("Cannot move there.");

                guard.moveChar();
                guard.incInd();
                break;
            case 2:
                if(!checkObstacle(hero, 'I',dir) && !checkObstacle(hero, 'X',dir))
                hero.moveChar(dir);
                else promptMsg("Cannot move there.");



        }
    }

    public void levelup(int id){
        switch (id){
            case 1:
                level++;
                map.setMap(2);
                hero.setCoordinates(8 ,1);
                break;
        }

    }
}
