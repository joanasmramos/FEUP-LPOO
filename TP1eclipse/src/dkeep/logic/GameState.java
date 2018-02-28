package dkeep.logic;

public class GameState {
    Map map  = new Map(1);
    Hero hero = new Hero(1,7, 'H');
    Guard guard = new Guard(1, 8, 'G');
    Ogre ogre = new Ogre(5, 1, 'O');
    Object key = new Object(1,8,'k');

    private Character[][] characters = {{hero, guard}, {hero, ogre}};
    private Object[][] objects  = {{},{key, ogre.getOgre_club()}};

    private int level;

    public enum States { DONE, GAME_OVER, PLAYING;}
    public enum Events { EMPTY, EXIT;}

    private States current_state;
    private Events current_event;

    public GameState() {
        map.setMap(2);
        level = 2;
        current_state=States.PLAYING;
        current_event = Events.EMPTY;
        map.printMap(characters, objects);
    }

    public Map getMap(){return map;}

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
                if(hero.checkIfCaught(ogre.getLine(), ogre.getColumn())) {
                    current_state = States.GAME_OVER;
                    promptMsg("GAME OVER");
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
                updatePos(1, 1,user_input);
                map.printMap(characters,objects);
                break;
            case 2:
                updatePos(2, 2,user_input);
                map.printMap(characters,objects);
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
    public  void updatePos(int level, int id, char dir){
        switch (id){
            case 1:
                moveHero(level,dir);
                guard.moveChar();
                guard.incInd();
                break;

            case 2:
                moveHero(level,dir);
                moveOgre();
        }
    }

    public void moveHero(int level, char dir){
        switch (level){
            case 1:
                if(!checkObstacle(hero, 'I',dir) && !checkObstacle(hero, 'X',dir)){

                    if(checkObstacle(hero, 'S', dir)) levelup(1);

                    if(checkObstacle(hero, 'K',dir))
                        map.openDoors();
                    else hero.moveChar(dir);
                }else promptMsg("Cannot move there.");

                break;

            case 2:
                if(!checkObstacle(hero, 'I',dir) && !checkObstacle(hero, 'X',dir))
                    hero.moveChar(dir);
                else if(checkObstacle(hero, 'I', dir) && hero.HasKey()) {
                    hero.moveChar(dir);
                    levelup(2);
                }
                else
                    promptMsg("Cannot move there.");

                if(key.getLine() == hero.getLine() && key.getColumn() == hero.getColumn()) {
                    hero.setKey(true);
                    hero.setChar('K');
                }

                break;
        }
    }

    public void moveOgre(){
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


    public void levelup(int id){
        switch (id){
            case 1:
                level++;
                map.setMap(2);
                hero.setCoordinates(8 ,1);
                hero.setKey(false);
                break;
            case 2:
                current_event = Events.EXIT;
        }

    }
}
