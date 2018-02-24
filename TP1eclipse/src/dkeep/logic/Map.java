    package dkeep.logic;

    public class Map {

        private char map[][];
        int id;

        private static char map1[][] = {
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
                {'X', 'X', 'X',' ', 'X', 'X', 'X', ' ', ' ', 'X'},
                {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
                {'X', 'X', 'X',' ', 'X', 'X', 'X', ' ', ' ', 'X'},
                {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', 'X', 'X',' ', 'X', 'X', 'X', 'X', ' ', 'X'},
                {'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };

        private static char map2[][] = {
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };

        public Map(int id){
            this.id = id;

            switch(id){
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

        public void setMap(int id){
            this.id= id;

            switch(id){
                case 1:
                    map = map1;
                    break;
                case 2:
                    map = map2;
                    break;
            }
        }

        public void printMap(Character[][] characters){
            for (int i = 0; i<10;i++){
                for (int j = 0; j< 10; j++){

                    for (int l  = 0 ; l< characters[id-1].length; l++){
                        if(i == characters[id-1][l].getLine() && j == characters[id-1][l].getColumn()){
                            System.out.print(characters[id-1][l].getChar() + " ");
                            break;
                        }
                        else if(l == characters[id-1].length-1) {
                            System.out.print(this.map[i][j] + " ");
                            break;
                        }
                    }
                }
                System.out.println();
            }
        }
    }
