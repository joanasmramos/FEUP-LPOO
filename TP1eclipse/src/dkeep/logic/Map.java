    package dkeep.logic;
    import java.util.ArrayList;

    public class Map {

        private char map[][];
        private int n_lines;
        private int n_columns;
        
        ArrayList<Character> chars;
        ArrayList<Object> objects;


        public  Map(char[][] newmap) {
            this.map = newmap;
            this.n_lines = newmap.length;
            this.n_columns = newmap[0].length;
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
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (this.map[i][j] == 'I')
                        this.map[i][j] = 'S';
                }
            }
        }

        
        public void print() {
            boolean print_char = false;
            boolean print_obj = false;

            for (int i = 0; i < n_lines; i++) {
                for (int j = 0; j < n_columns; j++) {
                	
                	for (Character charac : chars) {
                		if(i==charac.getLine() && j==charac.getColumn()) {
                			System.out.print(charac.getChar() + " ");
                			print_char = true;
                		}
                	}
                	
                	for(Object obj : objects) {
                		if(i==obj.getLine() && j==obj.getColumn()) {
                			if(obj.isVisible()) {
                				if(print_char) j++;
                                System.out.print(obj.getChar() + " ");
                                print_obj = true;
                			}
                		}
                	}
                	
                	if(!print_char && !print_obj) System.out.print(this.map[i][j] + " ");

                    print_char = false;
                    print_obj = false;
                	
                }
                System.out.println();
            }
        }

        public void setMap(char[][] newmap) {
            this.map = newmap;
        }

    }
