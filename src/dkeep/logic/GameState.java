    package dkeep.logic;
    import java.util.HashSet;

import dkeep.logic.GameState.States;

import java.util.ArrayList;

    public class GameState {
        private Map map;
        
        private Hero hero;
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
         * Creates a new game with the given map, initializes all elements
         * @param map: map for the level
         */
        public GameState(Map map) {
            hero = null;
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

        /**
         * @return returns the ArrayList of walls
         */
        public ArrayList<Object> getWalls(){
        	return walls;
        }
        
        /**
         * Clears the ArrayList that stores walls, leaving it empty
         */
        public void removeAllWalls() {
        	walls.clear();
        }
        
        /**
         * Adds a wall to the ArrayList of walls
         * @param wall: wall to be added
         */
        public void addWall(Object wall) {
        	walls.add(wall);
        }
        
        /**
         * @return returns the guard
         */
        public Guard getGuard() {
            return guard;
        }

        /**
         * Sets guard to the given one
         * @param guard: new guard
         */
        public void setGuard(Guard guard) {
            this.guard = guard;
        }

        /**
         * Clears the HashSet that stores ogres, leaving it empty
         */
        public void removeAllOgres() {
        	if(ogres!=null) ogres.clear();
        }
        
        /**
         * Adds the given ogre to the HashSet of ogres
         * @param o: ogre to be added
         */
        public void addOgre(Ogre o){
            ogres.add(o);
            map.addChar(o);
            map.addObj(o.getOgre_club());
        }

        /**
         * @return Returns the HashSet of ogres
         */
        public HashSet<Ogre> getOgres() {
            return ogres;
        }

        /**
         * @return returns the key
         */
        public Object getKey() {
            return key;
        }

        /**
         * Sets the number of ogres for the level
         * @param nrOgres: number of ogres
         */
        public void setNrOgres(int nrOgres) {
            this.nrOgres = nrOgres;
        }

        /**
         * @return returns the lever
         */
        public Lever getLever() {
            return lever;
        }

        /**
         * Adds a new level to the game, which contains a key and a lever and a new map
         * @param map: level's map
         * @param key: level's key
         * @param lever: level's lever
         */
        public void addLevel(char[][] map, boolean key, boolean lever){
            levels.add(new Map(map, key, lever));
            level++;
        }


        /**
         * @return returns hero's club
         */
        public Club getClub() {
            return club;
        }

        /**
         * @return returns the hero
         */
        public Hero getHero() {
            return hero;
        }

        /**
         * @return returns the current map
         */
        public Map getMap(){
            return map;
        	}


        /**
         * @return returns the game's current state
         */
        public States getCurrent_state(){return current_state;}


        /**
         * Calls checkIfCaught and updates the game state if the hero was caught by the guard
         */
        public void checkCaughtByGuard(){
            if (guard != null) {
                if (hero.checkIfCaught(guard.getLine(), guard.getColumn()) && !guard.isAsleep()) {
                    current_state = States.GAME_OVER;
                }
            }
        }

        /**
         * Calls checkIfCaught and updates the game state if the hero was killed by any ogre
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
         * Calls checkCaughtByGuard and checkCaughtByOgres to update the game state
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
         * Calls updatePos to update all game elements' positions based on the user input
         * @param user_input: direction of the hero's movement
         */
        public void game(char user_input){
            updatePos(user_input);
            if(current_state == States.MAP_DONE) levelup();
        }


        /**
         * Given a GameElement's current position, checks if it is going to encounter the given obstacle (identified by its representative char) if it engages in a particular direction
         * @param character: game element to evaluate
         * @param obstacle: obstacle to check for
         * @param dir: direction to evaluate
         * @return returns true if the character is going to encounter the obstacle, false otherwise
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
         * Same as the other overload of checkObstacle but this time the obstacle itself is passed as an argument
         * @param object: game element to evaluate
         * @param obstacle: obstacle to check for
         * @param dir: direction to evaluate
         * @return returns true if the character is going to encounter the obstacle, false otherwise
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
         * Calls moveHero, Guard.moveChar and moveOgre to update their coordinates properly, having all constraints in mind
         * @param dir: direction in which the hero moves
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
         * Updates hero's coordinates, given the direction he wants to follow, checking for obstacles, picking up objects (like a key), affecting objects (lever) and checking if the hero exited the dungeon
         * @param dir: direction in which the hero moves
         * @return returns 0 if the hero is able to move, non zero otherwise
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
         * Checks if the hero moved onto a door and has the conditions to exit
         * @param dir: direction in which the hero moves
         * @return returns true if the hero exited, false otherwise
         */
        public boolean checkHeroExit(char dir ){
            if(checkObstacle(hero, 'S', dir) || (checkObstacle(hero, 'I', dir) && hero.HasKey())) {
                hero.moveChar(dir);
                current_state = States.MAP_DONE;
                if(level ==1) map.setMap(map2);
                map.setLever(false);
                map.setKey(true);
                return true;
            }
            return false;
        }
        
        /**
         * Moves an ogre's club, generating a possible direction for it
         * @param ogre: given ogre
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
         * Updates ogre's and his club's coordinates (calling moveClub), generating a random direction for him, checking if it is possible (obstacles etc)
         * @param ogre: ogre to update
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
         * Prepares map and its characters and objects according to the new level
         */
        public void levelup(){
            switch(this.level) {
                case 1:
                addLevel(map2, false, true);
                if(hero!=null) hero.setCoordinates(8, 1);
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
                break;
                default:
                    break;
            }
        }

        /**
         * @return returns true if the game is over, false otherwise
         */
        public boolean isGameOver(){
            checkEvents();
            return (current_state==States.GAME_OVER);
        }


        /**
         * Generates ogres and adds the to the map and the hashSet
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

		/**
		 * @return returns ArrayList of maps from the different levels
		 */
		public ArrayList<Map> getLevels() {
			return levels;
		}

		/**
		 * Sets the ArrayList of maps to the given one
		 * @param levels: given ArrayList
		 */
		public void setLevels(ArrayList<Map> levels) {
			this.levels = levels;
		}

		/**
		 * @return returns current level
		 */
		public int getLevel() {
			return level;
		}

		/**
		 * Sets current level
		 * @param level: new level
		 */
		public void setLevel(int level) {
			this.level = level;
		}

		/**
		 * @return returns number of ogres in game
		 */
		public int getNrOgres() {
			return nrOgres;
		}

		/**
		 * Sets the game map
		 * @param map: new map
		 */
		public void setMap(Map map) {
			this.map = map;
		}

		/**
		 * Sets the game hero
		 * @param hero: new hero
		 */
		public void setHero(Hero hero) {
            map.remChar(this.hero);
			this.hero = hero;
			map.addChar(hero);
		}

		/**
		 * Sets the HashSet of ogres to the given one
		 * @param ogres: new set of ogres
		 */
		public void setOgres(HashSet<Ogre> ogres) {
			this.ogres = ogres;
		}

		/**
		 * Sets the game key
		 * @param key: new key
		 */
		public void setKey(Object key) {
			this.key = key;
		}

		/**
		 * Sets the hero's club
		 * @param club: new club
		 */
		public void setClub(Club club) {
			this.club = club;
		}

		/**
		 * Sets the game's lever
		 * @param lever: new lever
		 */
		public void setLever(Lever lever) {
			this.lever = lever;
		}

		/**
		 * Sets the game's current state
		 * @param current_state: new current state
		 */
		public void setCurrent_state(States current_state) {
			this.current_state = current_state;
		}

		/**
		 * @return returns game's exit door
		 */
		public Object getExitDoor() {
			return exitDoor;
		}

		/**
		 * Sets game's exit door
		 * @param exitDoor: new exit door 
		 */
		public void setExitDoor(Object exitDoor) {
			this.exitDoor = exitDoor;
		}
    }
