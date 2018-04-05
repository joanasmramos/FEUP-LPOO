    package dkeep.logic;
    import java.util.HashSet;

import dkeep.logic.GameState.States;

import java.util.ArrayList;

    public class GameState {
        private Map map;
        
        private Hero hero = new Hero(1,1, 'H');
        private Guard guard = new Rookie(1, 8, 'G');
        private HashSet<Ogre> ogres = new HashSet<Ogre>(7);
        private Object key = new Object(1,8,'k');
        private Club club = new Club(8, 2, 'C');
        private Lever lever = new Lever(8,7);
        private int nrOgres;
        private ArrayList<Map> levels = new ArrayList<>();
        private int level = 1;


        public static char map2[][] = {
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };


        public enum States { DONE, GAME_OVER, PLAYING, MAP_DONE}

        private States current_state = States.PLAYING;

        public GameState(Map map) {
            ArrayList<Character> chars = new ArrayList<>();
            ArrayList<Object> objects = new ArrayList<>();
            this.map = map;
            level = 1;
            levels.add(map);
            chars.add(hero);
            chars.add(guard);
            objects.add(lever);
            map.setChars(chars);
            map.setObjs(objects);
        }

        public Guard getGuard() {
            return guard;
        }

        public void setGuard(Guard guard) {
            this.guard = guard;
        }


        public void addOgre(Ogre o){
            ogres.add(o);
            map.addChar(o);
            map.addObj(o.getOgre_club());
        }


        public Object getKey() {
            return key;
        }

        public void setNrOgres(int nrOgres) {
            this.nrOgres = nrOgres;
        }

        public Lever getLever() {
            return lever;
        }

        public void addLevel(char[][] map, boolean key, boolean lever){
            levels.add(new Map(map, key, lever));
            level++;
        }


        public Club getClub() {
            return club;
        }

        public Hero getHero() {
            return hero;
        }

        public Map getMap(){
            return map;
        	}


        public States getCurrent_state(){return current_state;}


        public void checkEvents() {
            if (guard != null) {
                if (hero.checkIfCaught(guard.getLine(), guard.getColumn()) && !guard.isAsleep()) {
                    current_state = States.GAME_OVER;
                }
            }

            if (ogres != null) {

                for (Ogre o : ogres) {
                    if (hero.checkIfCaught(o.getLine(), o.getColumn())) {
                        if (hero.HasCub()) {
                            o.setStunned(true);
                            o.setTurns(2);
                        } else current_state = States.GAME_OVER;
                    } else if (hero.checkIfCaught(o.getOgre_club().getLine(), o.getOgre_club().getColumn())) {
                        current_state = States.GAME_OVER;
                    }
                }
            }

            if(level==levels.size()){
                if(current_state == States.MAP_DONE)
                    current_state = States.DONE;
            }
        }



        public void game(char user_input){
            updatePos(user_input);
            if(current_state == States.MAP_DONE) levelup();
        }


        public boolean checkObstacle(GameElement character, char obstacle, char dir) {
            switch (dir) {
                case 'w':
                    if(character.getLine()-1 >= 0)
                        return (map.getMap()[character.getLine()-1][character.getColumn()] == obstacle);
                    else return false;
                case 's':
                    if(character.getLine()+1 < this.getMap().getN_lines())
                        return (map.getMap()[character.getLine()+1][character.getColumn()] == obstacle);
                   else return false;
                case 'd':
                    if(character.getColumn()+1 < this.getMap().getColumns())
                    return (map.getMap()[character.getLine()][character.getColumn()+1] == obstacle);
                    else return false;
                case 'a':
                    if(character.getColumn()-1 >= 0)
                    return (map.getMap()[character.getLine()][character.getColumn()-1] == obstacle);
                    else return false;

                default: return true;
            }
        }

        public boolean checkObstacle(GameElement object, GameElement obstacle, char dir) {
            switch (dir) {
                case 'w':
                    return (object.getLine()-1 == obstacle.getLine() && object.getColumn() == obstacle.getColumn());
                case 's':
                    return (object.getLine()+1 == obstacle.getLine() && object.getColumn() == obstacle.getColumn());
                case 'd':
                    return (object.getLine() == obstacle.getLine() && object.getColumn()+1 == obstacle.getColumn());
                case 'a':
                    return (object.getLine() == obstacle.getLine() && object.getColumn()-1 == obstacle.getColumn());

                default: return true;
            }
        }





        public  void updatePos(char dir) {
                if (moveHero(dir) != 1) {
                    if(guard!=null)
                        guard.moveChar();
                    if(ogres!=null){
                        for (Ogre o : ogres) {
                            moveOgre(o);
                        }
                }
            }

        }

        public int moveHero(char dir) {
            if(checkHeroExit(dir)) current_state = States.MAP_DONE;

                    if(!checkObstacle(hero, 'I',dir) && !checkObstacle(hero, 'X',dir)){

                        if (hero.catchClub(club, dir)) {
                            map.remObj(club);
                        }


                        if(map.isLever()) {
                            if (checkObstacle(hero, lever, dir)) {
                                map.openDoors();
                                lever.setUp(false);
                                return 0;
                            }
                        }

                         hero.moveChar(dir);

                        if(hero.getLine()==key.getLine() && key.getColumn()==hero.getColumn()) {
                            hero.setKey(true);
                            hero.setChar('K');
                            key.visible = false;
                        }

                    }
                    else return 1;

            return 0;
        }

        public boolean checkHeroExit(char dir ){
            if(checkObstacle(hero, 'S', dir) || (checkObstacle(hero, 'I', dir) && hero.HasKey())) {
                //map.openAllDoors();
                hero.moveChar(dir);
                current_state = States.MAP_DONE;
                map.setMap(map2);
                map.setLever(false);
                map.setKey(true);
                return true;
            }
            return false;
        }
        
        public void moveClub(Ogre ogre) {
            ogre.getOgre_club().setDir(ogre.generateDir());

            //generate a direction possible for ogre's club to move to
            while(checkObstacle(ogre.getOgre_club(), 'I',ogre.getOgre_club().getDir()) ||
                    checkObstacle(ogre.getOgre_club(), 'X',ogre.getOgre_club().getDir())||
                    checkObstacle(ogre.getOgre_club(), hero,ogre.getOgre_club().getDir())){


                    ogre.getOgre_club().setDir(ogre.generateDir());
            }

            if(checkObstacle(ogre.getOgre_club(), key, ogre.getOgre_club().getDir()))
                ogre.getOgre_club().isInKeyPos(key.getLine(), key.getColumn());


                ogre.throwClub(ogre.getOgre_club().getDir());
        }

        public void moveOgre(Ogre ogre){

            if(ogre.getStunned()) {
            	
            	moveClub(ogre);
            	
                if(ogre.getTurns() != 0) {
                    ogre.setTurns(ogre.getTurns()-1);
                    
                    return;
                }
                else {
                    ogre.setStunned(false);
                    ogre.setTurns(2);
                }
            }

            //move Ogre
            ogre.setOgreDir(ogre.generateDir());

            //generate a direction possible for ogre to move to
            while(checkObstacle(ogre, 'I',ogre.getDir()) || checkObstacle(ogre, 'X',ogre.getDir()) ||
                    checkObstacle(ogre, hero, ogre.getDir()) || checkObstacle(ogre, key, ogre.getDir())){
                ogre.setOgreDir(ogre.generateDir());
            }

            ogre.moveChar(ogre.getDir());

            moveClub(ogre);
        }


        public void levelup(){
            switch (this.level){
                case 1:
                    addLevel(map2, false, true);
                    hero.setCoordinates(8 ,1);
                    hero.setKey(false);
                    club.setVisible(true);
                    map.addObj(club);
                    map.addObj(key);
                    map.remChar(guard);
                    guard = null;
                    map.remObj(lever);
                    map.setLever(false);
                    map.setKey(true);


                    for(int i=0; i<this.nrOgres ; i++) {
                        Ogre anotherOgre = new Ogre(Ogre.generateNr(1, 5),
                                Ogre.generateNr(1, 8), 'O');
                        ogres.add(anotherOgre);
                        map.addChar(anotherOgre);
                        map.addObj(anotherOgre.getOgre_club());
                    }

                    current_state = States.PLAYING;

                    break;
                case 2:
                    break;
            }

        }

        public boolean isGameOver(){
            checkEvents();
            return (current_state==States.GAME_OVER);
        }
    }
