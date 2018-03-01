    package dkeep.logic;

    public class Map {

        private char map[][];
        int id;

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

            switch (id) {
                case 1:
                    map = map1;
                    break;
                case 2:
                    map = map2;
                    break;
            }
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
