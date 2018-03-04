package dkeep.test;
import org.junit.Test;
import static org.junit.Assert.*;

import dkeep.logic.*;

public class TestDungeonGameLogic {
    char map1[][] = {{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
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
        Map map = new Map(map1);
        GameState game = new GameState();
        game.setMap(map);

        int posHero[] = {1,1};
        assertEquals(1, game.getHero().getLine());
        assertEquals(1, game.getHero().getColumn());
        game.moveHero('d');
        assertEquals(1, game.getHero().getLine());
        assertEquals(2, game.getHero().getColumn());
    }

    @Test
    public void testHeroIsCapturedByGuard(){
        Map map = new Map(map1);
        GameState game = new GameState();
        game.setMap(map);
        assertFalse(game.getCurrent_state()== GameState.States.GAME_OVER);
    }


}