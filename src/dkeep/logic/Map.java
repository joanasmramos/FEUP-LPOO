    package dkeep.logic;
    import org.hamcrest.core.IsInstanceOf;

    import java.util.ArrayList;

    /**
     *
     */
    public class Map {

        private char map[][];
        private int n_lines;
        private int n_columns;
        private boolean opendoors;
        boolean lever = false;
        boolean key = false;
        
        ArrayList<Character> chars;
        ArrayList<Object> objects;

        /**
         * Constructor for class Map
         * @param newmap bidimensional char array with map characters' chars
         * @param lever boolean value relating if it's a map with lever
         * @param key boolean value relating if it's a map with key
         */
        public  Map(char[][] newmap, boolean lever, boolean key) {
            this.map = newmap;
            this.n_lines = newmap.length;
            this.n_columns = newmap[0].length;
            this.opendoors = false;
            this.lever = lever;
            this.key = key;
            this.chars = new ArrayList<Character>();
            this.objects = new ArrayList<Object>();
        }

        public void removeAllChars() {
        	chars.clear();
        }
        
        public void removeAllObjs() {
        	objects.clear();
        }
        
        /**
         *
         * @return boolean value relating if it's a map with lever
         */
        public boolean isLever() {
            return lever;
        }

        /**
         *
         * @return boolean value relating if it's a map with key
         */
        public boolean isKey() {
            return key;
        }

        /**
         *
         * @param lever boolean value relating if it's a map with lever
         */
        public void setLever(boolean lever) {
            this.lever = lever;
        }

        /**
         *
         * @param key boolean value relating if it's a map with key
         */
        public void setKey(boolean key) {
            this.key = key;
        }

        /**
         *
         * @return map's characters' chars array
         */
        public char[][] getMap() {
            return map;
        }

        /**
         *
         * @return number of columns in map
         */
        public int getColumns() {
            return n_columns;
        }

        /**
         *
         * @return number of lines in map
         */
        public int getN_lines() {
            return n_lines;
        }

        /**
         *
         * @return arraylist of game characters
         */
        public ArrayList<Character> getChars(){
            return this.chars;
        }

        /**
         *
         * @param chars arraylist of game characters
         */
        public void setChars(ArrayList<Character> chars) {
        	this.chars = chars;
        }

        /**
         * Adds a character to map
         * @param charac game character
         */
        public void addChar(Character charac) {
        	this.chars.add(charac);
        }

        /**
         * Removes a character from map
         * @param charac game characters
         */
        public void remChar(Character charac) {
        	this.chars.remove(charac);
        }

        /**
         *
         * @param objs arraylist of game objects
         */
        public void setObjs(ArrayList<Object> objs) {
        	this.objects = objs;
        }

        /**
         *
         * @return arraylist of game objects
         */
        public ArrayList<Object> getObjs(){
        	return this.objects;
        }

        /**
         * Adds an object to map
         * @param obj game object
         */
        public void addObj(Object obj) {
        	this.objects.add(obj);
        }

        /**
         * Removes an object from map
         * @param obj game object
         */
        public void remObj(Object obj) {
        	this.objects.remove(obj);
        }


        /**
         * Opens map's left consecutive doors
         */
        public void openDoors() {
        	boolean found1 = false;
        	int index = 0, n = 0;
        	
        	for (char [] line : map) {
        		if(line[0] == 'I')
        			if(found1 == true) {
        				line[0] = 'S';
        				map[index][0] ='S';
        				continue; }
        			else {
        				found1 = true;
        				index = n;
        				continue; }
        		found1=false;
        		n++;
        	}
        	this.opendoors=true;
        }

        /**
         * Open all map's doors
         */
        public void openAllDoors() {
        	for(int i=0;i<map.length;i++) {
        		for(int j=0;j<map[i].length;j++) {
        			if(map[i][j] == 'I')
        				map[i][j] = 'S';
        		}
        	}
        }

        /**
         * Close all map's doors
         */
        public void resetMap() {
            for (int i = 0; i < this.n_lines; i++) {
                for (int j = 0; j < this.n_columns; j++) {
                    if (this.map[i][j] == 'S')
                        this.map[i][j] = 'I';
                }
            }

            this.opendoors=false;
        }

        /**
         * Checks if map has any open doors
         * @return true if map has open doors, false if else
         */
        public boolean areDoorsOpen(){ return this.opendoors; }

        /**
         * @param newmap  bidimensional char array with map characters' chars
         */
        public void setMap(char[][] newmap) {
            this.map = newmap;
        }


        

    }
