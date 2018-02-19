import java.util.Scanner;
import java.lang.Character;

public class DungeonKeep {

    public static void printGrid(char grid[][], int hero[], int guard[]) {
        for(int i=0;i<10;i++) {
            for(int j=0; j<10;j++) {
                if(i==hero[0] && j == hero[1])
                    System.out.print("H ");

                else if(i==guard[0] && j== guard[1])
                    System.out.print("G ");

                else System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }


    public static void openDoors(char grid[][]) {
        for(int i=0;i<10;i++) {
            for(int j=0; j<10;j++) {
                if(grid[i][j]=='I')
                    grid[i][j] = 'S';
            }
        }
    }

    public static int checkIfCaught(int hero[], int villain[]) {
        if(hero[0] == villain[0])
            if(hero[1]+1 == villain[1] || hero[1]-1 == villain[1])
                return 1;

        if(hero[1] == villain[1])
            if(hero[0]+1 == villain[0] || hero[0]-1 == villain[0])
                return 1;

        return 0;
    }

    public static int moveChar(char mov, char grid[][], int character[]) {

        switch(mov) {
            case 'w':
                if(grid[character[0]-1][character[1]] == ' ') {
                    character[0]--;
                }

                else if(grid[character[0]-1][character[1]] == 'K') {
                    System.out.println("You opened all doors");
                    openDoors(grid);
                }

                else if(grid[character[0]-1][character[1]] == 'S') {
                    return 1;
                }

                else if(grid[character[0]-1][character[1]] == 'I')
                    System.out.println("The door is closed");

                else if(grid[character[0]-1][character[1]] == 'X')
                    System.out.println("That's a wall");

                break;

            case 's':
                if(grid[character[0]+1][character[1]] == ' ') {
                    character[0]++;
                }

                else if(grid[character[0]+1][character[1]] == 'K') {
                    System.out.println("You opened all doors");
                    openDoors(grid);
                }

                else if(grid[character[0]+1][character[1]] == 'S') {
                    return 1;
                }

                else if(grid[character[0]+1][character[1]] == 'I')
                    System.out.println("The door is closed");

                else if(grid[character[0]+1][character[1]] == 'X')
                    System.out.println("That's a wall");

                break;

            case 'a':
                if(grid[character[0]][character[1]-1]==' ') {
                    character[1]--;
                }

                else if(grid[character[0]][character[1]-1] == 'K') {
                    System.out.println("You opened all doors");
                    openDoors(grid);
                }

                else if(grid[character[0]][character[1]-1] == 'S') {
                    return 1;
                }

                else if(grid[character[0]][character[1]-1] == 'I')
                    System.out.println("The door is closed");

                else if(grid[character[0]][character[1]-1] == 'X')
                    System.out.println("That's a wall");

                break;

            case 'd':
                if(grid[character[0]][character[1]+1]==' ') {
                    character[1]++;
                }

                else if(grid[character[0]][character[1]+1] == 'K') {
                    System.out.println("You opened all doors");
                    openDoors(grid);
                }

                else if(grid[character[0]][character[1]+1] == 'S') {
                    return 1;
                }

                else if(grid[character[0]][character[1]+1] == 'I')
                    System.out.println("The door is closed");

                else if(grid[character[0]][character[1]+1]== 'X')
                    System.out.println("That's a wall");

                break;

            default:
                break;
        }
        return 0;
    }



    public static int move(char grid[][], Scanner scanner, int hero[], int guard[], char guardTraject[], int guardInd[]) {
        System.out.println("Enter a command: \n W - up \n S - down \n A - left \n D - right");

        char mov = scanner.next().charAt(0);
        mov=Character.toLowerCase(mov);

        if(moveChar(mov, grid, hero) == 1) {
            System.out.println(hero[0] + " " + hero[1]);

            moveChar(mov, grid, guard);

            if(guardInd[0] == 23)
                guardInd[0] = 0;
            else guardInd[0]++;

            printGrid(grid, hero, guard);

            return 1;
        }

        else {
            mov=Character.toLowerCase(guardTraject[guardInd[0]]);

            moveChar(mov, grid, guard);

            if(guardInd[0] == 23)
                guardInd[0] = 0;
            else guardInd[0]++;
        }


        printGrid(grid, hero, guard);

        return 0;
    }

    public static int level1(int hero[], Scanner scanner){
        char l1_grid[][] = {
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

        int guard[] = {1, 8};
        char guardTraject[] = {'A','S','S','S','S','A','A','A','A','A', 'A', 'S','D','D','D','D','D','D','D',
                'W','W','W','W','W'};
        int guardInd[] = {0};


        printGrid(l1_grid, hero, guard);

        while(true) {
            if(move(l1_grid, scanner, hero, guard, guardTraject, guardInd) == 1) {
                level2(hero, scanner);
                break;
            }
            if(checkIfCaught(hero, guard) == 1) {
                System.out.println("The guard caught you! You lost");
                break;
            }
        }
        return 1;
    }

    public static int level2(int hero[], Scanner scanner){
        char l2_grid[][] = {
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };

        hero[0] = 1;
        hero[1] = 8;
        int ogre[] = {5,1};
        System.out.println("\n \n Level 2");
        return 1;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int hero[] = {1,1};

        level1(hero, scanner);

    }
}
