package dkeep.test;
import org.junit.Test;
import static org.junit.Assert.*;

import dkeep.logic.*;

public class TestDungeonGameLogic {
    char map[][] = {{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
    {'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
    {'X', 'X', 'X', ' ', 'X', ' ', ' ', ' ', ' ', 'X'},
    {'X', ' ', 'I', ' ', ' ', ' ', 'X', ' ', ' ', 'X'},
    {'X', 'X', ' ', ' ', 'X', ' ', ' ', ' ', ' ', 'X'},
    {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
    {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
    {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
    {'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'},
    {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}};

    @Test
    public void testMoveHeroIntoFreeCell(){
        GameState game = new GameState();
        game.getMap().setMap(2);
        int posHero[] = {1,1};
        assertArrayEquals(posHero, game.getHeroPos());
        game.moveHero('d');
        posHero[1] = 2;
        assertArrayEquals(posHero, game.getHeroPos());
    }


}