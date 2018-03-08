    package dkeep.logic;
    import java.util.ArrayList;

    public class Map {

        private char map[][];
        private int n_lines;
        private int n_columns;
        private boolean opendoors;
        
        ArrayList<Character> chars;
        ArrayList<Object> objects;


        public  Map(char[][] newmap) {
            this.map = newmap;
            this.n_lines = newmap.length;
            this.n_columns = newmap[0].length;
            this.opendoors = false;
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
        
        public void clearChars() {
        	this.chars.clear();
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
        
        public void clearObjs() {
        	this.objects.clear();
        }


        public void openDoors() {
            for (int i = 0; i < this.n_lines; i++) {
                for (int j = 0; j < this.n_columns; j++) {
                    if (this.map[i][j] == 'I')
                        this.map[i][j] = 'S';
                }
            }

            this.opendoors=true;
        }

        public boolean areDoorsOpen(){ return this.opendoors; }

        
        public void print() {
            int print_char = 0;
            int print_obj = 0;

            for (int i = 0; i < n_lines; i++) {
                for (int j = 0; j < n_columns; j++) {
                	
                	for (Character charac : chars) {
                		if(i==charac.getLine() && j==charac.getColumn()) {
                			System.out.print(charac.getChar() + " ");
                			print_char++;
                		}
                	}
                	
                	for(Object obj : objects) {
                		if(i==obj.getLine() && j==obj.getColumn()) {
                			if(obj.isVisible()) {
                                System.out.print(obj.getChar() + " ");
                                print_obj++;
                			}
                		}
                	}
                	
                	if(print_char==0 && print_obj==0) System.out.print(this.map[i][j] + " ");

                    print_char = 0;
                    print_obj = 0;
                	
                }
                System.out.println();
            }
        }

        public void setMap(char[][] newmap) {
            this.map = newmap;
        }

    }
