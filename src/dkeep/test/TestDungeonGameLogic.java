package dkeep.test;
import org.junit.Test;
import static org.junit.Assert.*;

import dkeep.logic.*;

public class TestDungeonGameLogic {
    char map1[][] = {{ 'X', 'I', 'X', 'X', 'X' },
    { 'I', ' ', ' ', ' ', 'X' },
    { 'I', ' ', ' ', ' ', 'X' },
    { 'X', ' ', ' ', ' ', 'X' },
    { 'X', 'X', 'X', 'X', 'X' } };



    @Test
    public void testHerChar(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        assertTrue('H' == game.getHero().getChar());

        game.getClub().setCoordinates(2,1);
        game.getHero().setCoordinates(1,1);
        game.moveHero('s');
        assertEquals('A', game.getHero().getChar());
    }

    @Test
    public void testHeroCatchClub(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.getHero().setCoordinates(2,2);

        game.getClub().setCoordinates(3,2);
        assertTrue(game.getHero().catchClub(game.getClub(),'s'));

        game.getHero().setClub(false);

        game.getClub().setCoordinates(1,2);
        assertTrue(game.getHero().catchClub(game.getClub(),'w'));

        game.getHero().setClub(false);

        game.getClub().setCoordinates(2,3);
        assertTrue(game.getHero().catchClub(game.getClub(),'d'));

        game.getHero().setClub(false);

        game.getClub().setCoordinates(2,1);
        assertTrue(game.getHero().catchClub(game.getClub(),'a'));
    }

    @Test
    public void testGuardChar(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        assertTrue('G' == game.getGuard().getChar()|| 'g' == game.getGuard().getChar());
    }

    @Test
    public void testLeverChar(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        assertTrue('K' == game.getLever().getChar());
    }


    @Test
    public void testKeyChar(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        assertTrue('k' == game.getKey().getChar()|| '$' == game.getKey().getChar());
        game.getKey().setVisible(false);
        assertTrue(' ' == game.getKey().getChar());
    }

    @Test
    public void testGuardAsleep(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.getGuard().setCoordinates(1,3);
        game.moveHero('d');

        int pos_old[] = new int[2];
        int pos_new[] = new int[2];
        pos_old[0] = game.getGuard().getLine();
        pos_old[1] = game.getGuard().getColumn();

        game.moveHero('d');

        pos_new[0] = game.getGuard().getLine();
        pos_new[1] = game.getGuard().getColumn();

        assertNotEquals(pos_new, pos_old);

    }

    @Test
    public void testMoveHeroIntoFreeCell(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.getGuard().setCoordinates(1,3);

        assertEquals(1, game.getHero().getLine());
        assertEquals(1, game.getHero().getColumn());
        game.moveHero('d');
        assertEquals(1, game.getHero().getLine());
        assertEquals(2, game.getHero().getColumn());
    }

    @Test
    public void testHeroMovesIntoWall(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.getGuard().setCoordinates(1,3);

        game.moveHero('a');
        assertEquals(1, game.getHero().getLine());
        assertEquals(1, game.getHero().getColumn());

        game.moveHero('w');
        assertEquals(1, game.getHero().getLine());
        assertEquals(1, game.getHero().getColumn());
    }


    @Test
    public void testHeroIsCapturedByGuard(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.getGuard().setCoordinates(1,3);

        assertFalse(game.isGameOver());
        game.moveHero('d');
        assertTrue(game.isGameOver());
    }

    @Test
    public void testHeroLeavesClosedDoor(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.getGuard().setCoordinates(1,3);

        assertEquals(1, game.getHero().getLine());
        assertEquals(1, game.getHero().getColumn());

        assertFalse(game.getMap().areDoorsOpen());
    }


   @Test
    public void testHeroOpensDoor(){
       Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.getLever().setCoordinates(3,1);
        game.getGuard().setCoordinates(1,1);

        game.moveHero('s');
        game.moveHero('s');

        assertTrue(game.getMap().areDoorsOpen());
    }

    @Test
    public void testHeroGoThroughDoor(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.getGuard().setCoordinates(1,3);
        game.getLever().setCoordinates(3,1);

        game.moveHero('s');
        game.moveHero('s');

        game.moveHero('w');
        game.moveHero('a');


        assertTrue(game.getCurrent_state()== GameState.States.MAP_DONE);

    }

    @Test(timeout = 1000)
    public void testDrunkenGuardisAsleep(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.setGuard(new Drunken(1,3,'G'));

        boolean asleep = false;
        boolean awake = false;
        int line, column;

        while(!asleep || !awake){
            line = game.getGuard().getLine();
            column = game.getGuard().getColumn();
            game.getGuard().moveChar();

            if(game.getGuard().isAsleep()) {
                asleep = true;
                assertEquals(line, game.getGuard().getLine());
                assertEquals(column, game.getGuard().getColumn());
            }
            else awake = true;
        }
    }

    @Test(timeout = 1000)
    public void testSuspiciousGuardisReverse(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.setGuard( new Suspicious(1,3,'G'));

        assertFalse(game.getGuard().isReverse());

        boolean reverse = false;
        boolean normal = false;

        while(!normal || !reverse){
            game.getGuard().moveChar();

            if(game.getGuard().isReverse())
                normal = true;
            else reverse = true;
        }
    }

    @Test
    public void testHeroLosesWithGuardAsleep(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.setGuard(new Drunken(1,3,'G'));

        game.getGuard().setAsleep(true);
        assertTrue(game.getGuard().isAsleep());
        game.moveHero('d');

        game.checkEvents();
        assertTrue(GameState.States.PLAYING == game.getCurrent_state());
    }

    @Test
    public void openDoors(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.getMap().openAllDoors();

        boolean closed = false;

        for (int i = 0; i < game.getMap().getN_lines();i++){
            for(int j = 0; j<game.getMap().getColumns();j++){
                if(game.getMap().getMap()[i][j] == 'I')
                    closed = true;
            }
        }

        assertFalse(closed);
    }

    @Test
    public void closedDoors(){
        Map map = new Map(map1, true, false);
        GameState game = new GameState(map);
        game.getMap().resetMap();

        boolean open = false;

        for (int i = 0; i < game.getMap().getN_lines();i++){
            for(int j = 0; j<game.getMap().getColumns();j++){
                if(game.getMap().getMap()[i][j] == 'S')
                    open = true;
            }
        }

        assertFalse(open);
    }
}