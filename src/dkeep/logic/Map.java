    package dkeep.logic;
    import org.hamcrest.core.IsInstanceOf;

    import java.util.ArrayList;

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
         * Constructs a new map, abstracted from the 2D array of chars, initializes data members
         * @param newmap: 2D array of chars, map itself
         * @param lever: indicates weather the map has lever(s)
         * @param key: indicates weather the map has keys
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

        /**
         * Clears the ArrayList of the map's Characters, leaving it empty
         */
        public void removeAllChars() {
        	chars.clear();
        }
        
        /**
         * Clears the ArrayList of the map's Objects, leaving it empty
         */
        public void removeAllObjs() {
        	objects.clear();
        }
        
        /**
         * @return true if the map has lever(s), false otherwise
         */
        public boolean isLever() {
            return lever;
        }

        /**
         * @return true if the map has key(s), false otherwise
         */
        public boolean isKey() {
            return key;
        }

        /**
         * Sets lever
         * @param lever: true if map has lever(s), false otherwise
         */
        public void setLever(boolean lever) {
            this.lever = lever;
        }

        /**
         * Sets key
         * @param key: true if map has key(s), false otherwise
         */
        public void setKey(boolean key) {
            this.key = key;
        }

        /**
         * @return returns the 2D Array map
         */
        public char[][] getMap() {
            return map;
        }


        /**
         * @return returns map's number of columns
         */
        public int getColumns() {
            return n_columns;
        }

        /**
         * @return returns ArrayList of map's Characters
         */
        public ArrayList<Character> getChars(){
            return this.chars;
        }

        /**
         * Sets ArrayList of map's Characters
         * @param chars: new ArrayList
         */
        public void setChars(ArrayList<Character> chars) {
        	this.chars = chars;
        }
        
        /**
         * Adds a Character to the map's ArrayList of Characters
         * @param charac: character to add
         */
        public void addChar(Character charac) {
        	this.chars.add(charac);
        }
        
        /**
         * Removes character from the map's ArrayList of Characters
         * @param charac
         */
        public void remChar(Character charac) {
        	this.chars.remove(charac);
        }
        
        /**
         * Sets map's Objects ArrayList
         * @param objs: new ArrayList
         */
        public void setObjs(ArrayList<Object> objs) {
        	this.objects = objs;
        }
        
        /**
         * @return returns the map's Objects ArrayList
         */
        public ArrayList<Object> getObjs(){
        	return this.objects;
        }
        
        /**
         * Adds an object to the map's Objects ArrayList
         * @param obj: object to add
         */
        public void addObj(Object obj) {
        	this.objects.add(obj);
        }
        
        /**
         * Removes an object from the map's Objects ArrayList
         * @param obj: object to be removed
         */
        public void remObj(Object obj) {
        	this.objects.remove(obj);
        }

        /**
         * Opens the first 2 contiguous doors on the left wall of the map by changing their char in the map array from I to S
         * Sets a flag indicating that exit doors are open
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
         * Opens all doors of the map by changing their char in the map array from I to S
         * Sets a flag indicating doors are open
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
         * Closes all open doors of the map
         * Resets the flag that indicates that doors are open
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
         * @return returns a flag: true if doors are open, false otherwise
         */
        public boolean areDoorsOpen(){ return this.opendoors; }


        /**
         * Sets the 2D array
         * @param newmap: new array
         */
        public void setMap(char[][] newmap) {
            this.map = newmap;
        }

        /**
         * @return returns map's number of lines 
         */
        public int getN_lines() {
            return n_lines;
        }
        

    }
