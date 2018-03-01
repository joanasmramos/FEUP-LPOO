package dkeep.test;
import org.junit.Test;
import static org.junit.Assert.*;

import dkeep.logic.*;

import java.lang.Object;

public class TestDungeonGameLogic {
    char map1[][] = {{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
    {'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
    {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
    {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
    {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
    {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
    {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
    {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
    {'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'},
    {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}};

    @Test
    public void testMoveHeroIntoFreeCell(){
        Map map = new Map(map1);
        GameState game = new GameState();
        int posHero[] = {1,1};
        assertArrayEquals(posHero, game.getHeroPos());
        game.moveHero('d');
        posHero[1] = 2;
        assertArrayEquals(posHero, game.getHeroPos());
    }

}