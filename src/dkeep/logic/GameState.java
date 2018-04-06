    package dkeep.logic;
    import java.util.HashSet;

import dkeep.logic.GameState.States;

import java.util.ArrayList;

    /**
     * Game Loop
     */
    public class GameState {
        private Map map;
        
        private Hero hero = new Hero(1,1, 'H');
        private Guard guard = new Rookie(1, 8, 'G');
        private HashSet<Ogre> ogres = new HashSet<Ogre>(7);
        private Object key = new Object(1,8,'k');
        private Club club = new Club(8, 2, 'C');
        private Lever lever = new Lever(8,7);
        private Object exitDoor;
        private int nrOgres;
        private ArrayList<Map> levels = new ArrayList<>();
        private int level = 1;
        private ArrayList<Object> walls;


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

        /**
         * Constructor for game
         * @param map map to use in game
         */
        public GameState(Map map) {
            ArrayList<Character> chars = new ArrayList<>();
            ArrayList<Object> objects = new ArrayList<>();
            walls = new ArrayList<Object>();
            ogres = new HashSet<Ogre>(7);
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

        public void removeAllWalls() {
            walls.clear();
        }

        public void addWall(Object wall) {
            walls.add(wall);
        }


        /**
         *
         * @param guard game guard
         */
        public void setGuard(Guard guard) {
            this.guard = guard;
        }


        public void addOgre(Ogre o){
            ogres.add(o);
            map.addChar(o);
            map.addObj(o.getOgre_club());
        }

        /**
         *
         * @return HashSet of ogres in the level
         */
        public HashSet<Ogre> getOgres() {
            return ogres;
        }

        /**
         *
         * @return key in the level
         */
        public Object getKey() {
            return key;
        }

        /**
         * Sets number of ogres in level
         * @param nrOgres number of ogres
         */
        public void setNrOgres(int nrOgres) {
            this.nrOgres = nrOgres;
        }

        /**
         *
         * @return lever in level
         */
        public Lever getLever() {
            return lever;
        }

        /**
         * Add level to game
         * @param map
         * @param key
         * @param lever
         */
        public void addLevel(char[][] map, boolean key, boolean lever){
            levels.add(new Map(map, key, lever));
            level++;
        }

        /**
         *
         * @return club in level
         */
        public Club getClub() {
            return club;
        }

        /**
         *
         * @return hero in game
         */
        public Hero getHero() {
            return hero;
        }

        /**
         *
         * @return map used in level
         */
        public Map getMap(){
            return map;
        	}

        /**
         *
         * @return current game state
         */
        public States getCurrent_state(){return current_state;}

        /**
         * Check if hero has been caught by the guard
         */
        public void checkCaughtByGuard(){
            if (guard != null) {
                if (hero.checkIfCaught(guard.getLine(), guard.getColumn()) && !guard.isAsleep()) {
                    current_state = States.GAME_OVER;
                }
            }
        }

        /**
         * Check if hero has been caught by an ogre
         */
        public void checkCaughtByOgres() {
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
        }


        /**
         * Updates game state
         */
        public void checkEvents() {

            checkCaughtByGuard();
            checkCaughtByOgres();


            if (level == levels.size()) {
                if (current_state == States.MAP_DONE)
                    current_state = States.DONE;
            }
        }


        /**
         * Updates game states and elements
         * @param user_input direction of hero's movement
         */
        public void game(char user_input){
            updatePos(user_input);
            if(current_state == States.MAP_DONE) levelup();
        }


        /**
         * Checks if a game element collides with an obstacle
         * @param character game element
         * @param obstacle char of obstacle in map
         * @param dir direction of game element's movement
         * @return true if character collides, false if else
         */
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

        /**
         * Checks if a game element collides with an obstacle
         * @param object game element
         * @param obstacle character game element
         * @param dir direction of game element's movement
         * @return true if character collides, false if else
         */
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


        /**
         * Updates game elements
         * @param dir direction of hero's movement
         */
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

        /**
         * Moves hero
         * @param dir direction of hero's movement
         * @return 0 if movement is successful, 1 if else
         */
        public int moveHero(char dir) {

            if(checkHeroExit(dir)) current_state = States.MAP_DONE;

                    if(!checkObstacle(hero, 'I',dir) && !checkObstacle(hero, 'X',dir)){

                        if (hero.checkIfCatchClub(club, dir)) {
                            map.remObj(club);
                        }


                        if(map.isLever()) {
                            if (checkObstacle(hero, lever, dir)) {

                                if(lever.isUp()) {lever.setUp(false); map.openDoors();}
                                else {lever.setUp(true); map.resetMap();}
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

        /**
         * Checks if hero went through any door
         * @param dir direction of hero's movement
         * @return true if hero exited a door, false if else
         */
        public boolean checkHeroExit(char dir ){
            if(checkObstacle(hero, 'S', dir) || (checkObstacle(hero, 'I', dir) && hero.HasKey())) {
                hero.moveChar(dir);
                current_state = States.MAP_DONE;
                map.setMap(map2);
                map.setLever(false);
                map.setKey(true);
                return true;
            }
            return false;
        }

        /**
         * Moves club
         * @param ogre ogre which club belongs to
         */
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

        /**
         * Moves Ogre
         * @param ogre
         */
        public void moveOgre(Ogre ogre){

            if(ogre.getStunned()) {
            	
            	if(ogre.manageTurns()==1) return;
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


        /**
         * Level Up
         */
        public void levelup(){
                    addLevel(map2, false, true);
                    hero.setCoordinates(8 ,1);
                    club.setVisible(true);
                    map.addObj(club);
                    map.addObj(key);
                    map.remChar(guard);
                    guard = null;
                    map.remObj(lever);
                    map.setLever(false);
                    map.setKey(true);

                    generateOgres();

                    current_state = States.PLAYING;
        }

        /**
         * Checks if game is over
         * @return true if game is over, false if else
         */
        public boolean isGameOver(){
            checkEvents();
            return (current_state==States.GAME_OVER);
        }

        /**
         * Generates random ogres and adds them to level
         */
        public void generateOgres(){
            for(int i=0; i<this.nrOgres ; i++) {
                Ogre anotherOgre = new Ogre(Ogre.generateNr(1, 5),
                        Ogre.generateNr(1, 8), 'O');
                ogres.add(anotherOgre);
                map.addChar(anotherOgre);
                map.addObj(anotherOgre.getOgre_club());
            }
        }

		public ArrayList<Map> getLevels() {
			return levels;
		}

		public void setLevels(ArrayList<Map> levels) {
			this.levels = levels;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public int getNrOgres() {
			return nrOgres;
		}

		public void setMap(Map map) {
			this.map = map;
		}

		public void setHero(Hero hero) {
			this.hero = hero;
		}

		public void setOgres(HashSet<Ogre> ogres) {
			this.ogres = ogres;
		}

		public void setKey(Object key) {
			this.key = key;
		}

		public void setClub(Club club) {
			this.club = club;
		}

		public void setLever(Lever lever) {
			this.lever = lever;
		}

		public void setCurrent_state(States current_state) {
			this.current_state = current_state;
		}

		public Object getExitDoor() {
			return exitDoor;
		}

		public void setExitDoor(Object exitDoor) {
			this.exitDoor = exitDoor;
		}
    }
