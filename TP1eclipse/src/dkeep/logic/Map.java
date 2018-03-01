    package dkeep.logic;
    import java.util.ArrayList;

    public class Map {

        private char map[][];
        int id;
        
        ArrayList<Character> chars;
        ArrayList<Object> objects;

        private static char map1[][] = {
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
                {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
                {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
                {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
                {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
                {'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };

        private static char map2[][] = {
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

        public Map(int id) {
            this.id = id;
            this.chars = new ArrayList<Character>();
            this.objects = new ArrayList<Object>();

            switch (id) {
                case 1:
                    map = map1;
                    break;
                case 2:
                    map = map2;
                    break;
            }
        }

        public void setChars(ArrayList<Character> chars) {
        	this.chars = chars;
        }
        
        public ArrayList<Character> getChars(){
        	return this.chars;
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
        
        
        public char[][] getMap() {
            return map;
        }

        public int getLines() {
        	return this.map.length;
        }
        
        public int getColumns(int line) {
        	return this.map[line].length;
        }

        public void openDoors() {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (this.map[i][j] == 'I')
                        this.map[i][j] = 'S';
                }
            }
        }

        public int getId() {
            return id;
        }

        public void setMap(int id) {
            this.id = id;

            switch (id) {
                case 1:
                    map = map1;
                    break;
                case 2:
                    map = map2;
                    break;
            }
        }
        
        public void print() {
            boolean print_char = false;
            boolean print_obj = false;

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                	
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

        public void printMap(Character[][] characters, Object[][] objects) {
            boolean print_char = false;
            boolean print_obj = false;

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {

                    for (int l = 0; l < characters[id - 1].length; l++) {
                        if (i == characters[id - 1][l].getLine() && j == characters[id - 1][l].getColumn()) {
                            System.out.print(characters[id - 1][l].getChar() + " ");
                            print_char = true;
                            break;
                        }
                    }

                    for (int l = 0; l < objects[id - 1].length; l++) {
                        if (i == objects[id - 1][l].getLine() && j == objects[id - 1][l].getColumn()) {

                            if(objects[id - 1][l].isVisible()) {
                                if(print_char) j++;
                                System.out.print(objects[id - 1][l].getChar() + " ");
                                print_obj = true;
                            }
                            break;
                        }

                    }

                    if(!print_char && !print_obj) System.out.print(this.map[i][j] + " ");

                    print_char = false;
                    print_obj = false;
                }
                System.out.println();
            }
        }
    }
