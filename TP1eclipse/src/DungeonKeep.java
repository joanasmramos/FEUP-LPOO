import java.util.Scanner;
import java.lang.Character;
import java.util.Random;

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
    
    public static void printGridlv2(char grid[][], int hero[], int ogre[],int club[]) {
        for(int i=0;i<10;i++) {
            for(int j=0; j<10;j++) {
                if(i==hero[0] && j == hero[1])
                	if(hero[0]==1 && hero[1]==7) { // hero got the key
                		System.out.print("K ");
                		grid[i][j]=' ';
                	}
                	else System.out.print("H ");

                else if(i==ogre[0] && j== ogre[1]) 
                    if(ogre[0]==1 && ogre[1]==7)  // ogre is in the key position
                    	System.out.print("$ ");
                    else System.out.print("O ");

                else if(i==club[0] && j==club[1])
                    if(club[0]==1 && club[1]==7)  // club// is in the key position
                        System.out.print("$ ");
                    else System.out.print("* ");

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
    
    public static boolean checkObstacle(char grid[][], int character[], char obstacle, char dir) {
    	switch (dir) {
    	case 'w':
        	return (grid[character[0]-1][character[1]] == obstacle);
    	case 's':
    		return (grid[character[0]+1][character[1]] == obstacle);
    	case 'd':
    		return (grid[character[0]][character[1]+1] == obstacle);
    	case 'a':
    		return (grid[character[0]][character[1]-1] == obstacle);

    		default: return false;
    	}
    }
    
    public static char ogreDirection(Random rand) {
    	int dir = rand.nextInt(4);
    	
    	if(dir==0)
    		return 'w';
    	if(dir==1)
    		return 's';
    	if(dir==2)
    		return 'a';
    	if(dir==3)
    		return 'd';
    	
    	return 'z'; // error
    }
   
    public static void updateChar(int character[], char dir) {
    	switch (dir) {
    	case 'w':
    		character[0]--;
    		return;
    	case 's':
    		character[0]++;
    		return;
    	case 'd':
    		character[1]++;
    		return;
    	case 'a':
    		character[1]--;
    		
    	default: return;
    	}
    }
    
    public static int moveHeroLv2(char grid[][], int character[], char dir) {
    	if(checkObstacle(grid, character, ' ', dir) || checkObstacle(grid, character, 'k', dir)) {
    		updateChar(character, dir);
    		character[2]=1;
    		return 0;
    	}
    	
    	if(checkObstacle(grid, character, 'X', dir)) {
    		System.out.println("That's a wall");
    		return 0;
    	}
    	
    	if(checkObstacle(grid, character, 'I', dir)) {
    		if(character[2]==1)
    			return 1; // victory
    		else {
    			System.out.println("You don't have the key to open the door!");
    			return 0;
    		}
    	}
    		
    	return -1;
    }

    public static void throwClub(char grid[][], int ogre[], int club[], Random rand){
        switch (ogreDirection(rand)){
            case 'w':
                club[0] = ogre[0]-1;
                club[1] = ogre[1];
                return;
            case 's':
                club[0] = ogre[0]+1;
                club[1] = ogre[1];
                return;
            case 'd':
                club[0] = ogre[0];
                club[1] = ogre[1]+1;
                return;
            case 'a':
                club[0] = ogre[0];
                club[1] = ogre[1]-1;

            default: return;

        }
    }
    
    public static int moveOgreLv2(char grid[][], int character[], char dir) {
    	if(checkObstacle(grid, character, ' ', dir) || checkObstacle(grid, character, 'k', dir)) {
    		updateChar(character, dir);
    		return 0;
    	}
    	
    	if(checkObstacle(grid, character, 'X', dir) || checkObstacle(grid, character, 'I', dir)) {
    		return 1;
    	}
    	
    	return -1;
    }
    
    public static int moveCharsLv2(char grid[][], int hero[], int ogre[], int club[], char dir, Random rand) {
    	if (moveHeroLv2(grid, hero, dir) == 1)
    		return 1;
    	
    	char ogreDir=ogreDirection(rand);
    	
    	while(moveOgreLv2(grid, ogre, ogreDir)==1)
    		ogreDir=ogreDirection(rand);
        throwClub(grid, ogre, club, rand);
    		
    	return 0;
    
    }
    
    public static int moveLv2(char grid[][], Scanner scanner, Random rand, int hero[], int ogre[], int club[]) {
        System.out.println("Enter a command: \n W - up \n S - down \n A - left \n D - right");

        char dir = scanner.next().charAt(0);
        dir=Character.toLowerCase(dir);
        
        if(moveCharsLv2(grid,hero,ogre,club, dir,rand)==1) {
        	printGridlv2(grid, hero, ogre, club);
        	return 1;
        }
        else {
        	printGridlv2(grid, hero, ogre, club);
        	return 0;
        }
        
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
                level2(scanner);
                break;
            }
            if(checkIfCaught(hero, guard) == 1) {
                System.out.println("The guard caught you! You lost");
                break;
            }
        }
        return 1;
    }

    public static int level2(Scanner scanner){
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

        int hero[] = {8, 1, 0}; // third element indicates if the hero has the key
        int ogre[] = {5,1};
        int club[] = {-1,-1};
        
        Random rand = new Random();
        
        System.out.println("\n \nLevel 2");
        printGridlv2(l2_grid, hero, ogre, club);
        
        while(true) {
        	if(moveLv2(l2_grid, scanner, rand, hero, ogre, club)==1) {
        		System.out.println("Congratulations, you escaped!");
        		break;
        	}
        	
            if(checkIfCaught(hero, ogre) == 1) {
                System.out.println("The ogre destroyed you! You lost");
                break;
            }
        	
        }
        
        return 1;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int hero[] = {1,1};

        level1(hero, scanner);

    }
}
