import java.util.Scanner;
import java.lang.Character;

public class DungeonKeep {
	
	public static void printGrid(char grid[][]) {
		for(int i=0;i<10;i++) {
			for(int j=0; j<10;j++) {
				System.out.print(grid[i][j]);
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
	
	public static int move(char grid[][], Scanner scanner, int hero[]) {
		System.out.println("Enter a command: \n W - up \n S - down \n A - left \n D - right");
		char mov = scanner.next().charAt(0);
		mov=Character.toLowerCase(mov);
		
		
		switch(mov) {
		case 'w':
			if(grid[hero[0]-1][hero[1]] == ' ') {
				grid[hero[0]-1][hero[1]]= 'H';
				grid[hero[0]][hero[1]] = ' ';
				hero[0]--;
			}
			
			else if(grid[hero[0]-1][hero[1]] == 'K') {
				System.out.println("You opened all doors");
				openDoors(grid);
			}
			
			else if(grid[hero[0]-1][hero[1]] == 'S') {
				System.out.println("Congratulations, you escaped!");
				return 1;
			}
			
			else if(grid[hero[0]-1][hero[1]] == 'I')
				System.out.println("The door is closed");
			
			else if(grid[hero[0]-1][hero[1]] == 'X')
				System.out.println("That's a wall");
			
			break;
		
		case 's':
			if(grid[hero[0]+1][hero[1]] == ' ') {
				grid[hero[0]+1][hero[1]]= 'H';
				grid[hero[0]][hero[1]] = ' ';
				hero[0]++;
			}
			
			else if(grid[hero[0]+1][hero[1]] == 'K') {
				System.out.println("You opened all doors");
				openDoors(grid);
			}
			
			else if(grid[hero[0]+1][hero[1]] == 'S') {
				System.out.println("Congratulations, you escaped!");
				return 1;
			}
			
			else if(grid[hero[0]+1][hero[1]] == 'I')
				System.out.println("The door is closed");
			
			else if(grid[hero[0]+1][hero[1]] == 'X')
				System.out.println("That's a wall");
			
			break;
		
		case 'a':
			if(grid[hero[0]][hero[1]-1]==' ') {
				grid[hero[0]][hero[1]-1] = 'H';
				grid[hero[0]][hero[1]] = ' ';
				hero[1]--;
			}
			
			else if(grid[hero[0]][hero[1]-1] == 'K') {
				System.out.println("You opened all doors");
				openDoors(grid);
			}
			
			else if(grid[hero[0]][hero[1]-1] == 'S') {
				System.out.println("Congratulations, you escaped!");
				return 1;
			}
			
			else if(grid[hero[0]][hero[1]-1] == 'I')
				System.out.println("The door is closed");
			
			else if(grid[hero[0]][hero[1]-1] == 'X')
				System.out.println("That's a wall");
			
			break;
			
		case 'd':
			if(grid[hero[0]][hero[1]+1]==' ') {
				grid[hero[0]][hero[1]+1] = 'H';
				grid[hero[0]][hero[1]] = ' ';
				hero[1]++;
			}
			
			else if(grid[hero[0]][hero[1]+1] == 'K') {
				System.out.println("You opened all doors");
				openDoors(grid);
			}
			
			else if(grid[hero[0]][hero[1]+1] == 'S') {
				System.out.println("Congratulations, you escaped!");
				return 1;
			}
			
			else if(grid[hero[0]][hero[1]+1] == 'I')
				System.out.println("The door is closed");
			
			else if(grid[hero[0]][hero[1]+1]== 'X')
				System.out.println("That's a wall");
			
			break;
	
			default: 
				break;
	}
		
		printGrid(grid);
		
		return 0;
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		char grid[][] = {
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'},
				{'X', 'X', 'X',' ', 'X', 'X', 'X', ' ', ' ', 'X'},
				{'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
				{'X', 'X', 'X',' ', 'X', 'X', 'X', ' ', ' ', 'X'},
				{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
				{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ','X'},
				{'X', 'X', 'X',' ', 'X', 'X', 'X', 'X', ' ', 'X'},
				{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		};
		int hero[]= {1,1};
		
		printGrid(grid);
		
		while(true) {
		if(move(grid, scanner, hero) == 1)
			break;
		}
	
	} 
}
