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
        
        public boolean isLever() {
            return lever;
        }

        public boolean isKey() {
            return key;
        }

        public void setLever(boolean lever) {
            this.lever = lever;
        }

        public void setKey(boolean key) {
            this.key = key;
        }

        public char[][] getMap() {
            return map;
        }


        public int getColumns() {
            return n_columns;
        }

        public ArrayList<Character> getChars(){
            return this.chars;
        }

        public void setChars(ArrayList<Character> chars) {
        	this.chars = chars;
        }
        
        public void addChar(Character charac) {
        	this.chars.add(charac);
        }
        
        public void remChar(Character charac) {
        	this.chars.remove(charac);
        }
        
        public void setObjs(ArrayList<Object> objs) {
        	this.objects = objs;
        }
        
        public ArrayList<Object> getObjs(){
        	return this.objects;
        }
        
        public void addObj(Object obj) {
        	this.objects.add(obj);
        }
        
        public void remObj(Object obj) {
        	this.objects.remove(obj);
        }



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
        
        public void openAllDoors() {
        	for(int i=0;i<map.length;i++) {
        		for(int j=0;j<map[i].length;j++) {
        			if(map[i][j] == 'I')
        				map[i][j] = 'S';
        		}
        	}
        }

        public void resetMap() {
            for (int i = 0; i < this.n_lines; i++) {
                for (int j = 0; j < this.n_columns; j++) {
                    if (this.map[i][j] == 'S')
                        this.map[i][j] = 'I';
                }
            }

            this.opendoors=false;
        }

        public boolean areDoorsOpen(){ return this.opendoors; }


        public void setMap(char[][] newmap) {
            this.map = newmap;
        }

        public int getN_lines() {
            return n_lines;
        }
        

    }
