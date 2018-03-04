package dkeep.test;
import org.junit.Test;
import static org.junit.Assert.*;

import dkeep.logic.*;

public class TestDungeonGameLogic {
    char map1[][] = {{ 'X', 'I', 'X', 'X', 'X' },
    { 'X', ' ', ' ', 'G', 'X' },
    { 'X', ' ', ' ', ' ', 'X' },
    { 'X', 'k', ' ', ' ', 'X' },
    { 'X', 'X', 'X', 'X', 'X' } };

    @Test
    public void testMoveHeroIntoFreeCell(){
        Map map = new Map(map1);
        GameState game = new GameState();
        game.setMap(map);
        game.guard.setCoordinates(1,3);

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
        game.guard.setCoordinates(1,3);

        assertFalse(game.isGameOver());
        game.moveHero('d');
        assertTrue(game.isGameOver());
    }


}