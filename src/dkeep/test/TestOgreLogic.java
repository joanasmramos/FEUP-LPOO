package dkeep.test;
import org.junit.Test;
import static org.junit.Assert.*;

import dkeep.logic.*;

public class TestOgreLogic {

    char[][] map1 = {

            { 'X', 'I', 'X', 'X', 'X' },
            { 'X', ' ', ' ', ' ', 'X' },
            { 'X', ' ', ' ', ' ', 'X' },
            { 'X', ' ', ' ', ' ', 'X' },
            { 'X', 'X', 'X', 'X', 'X' }
    };



    @Test
    public void testClubNotVisable(){
        Map map = new Map(map1);
        GameState game = new GameState(map);
        assertFalse(game.ogre.getOgre_club().isVisible());
    }

    @Test
    public void testOgreChar(){
        Map map = new Map(map1);
        GameState game = new GameState(map);
        game.levelup();
        assertTrue('8' == game.ogre.getChar() || 'O' == game.ogre.getChar());
    }

    @Test
    public void testHeroIsCapturedByOgre(){
        Map map = new Map(map1);
        GameState game = new GameState(map);
        game.levelup();
        game.ogre.setCoordinates(1,4);

        game.hero.setCoordinates(1,1);
        game.key.setCoordinates(3,1);

        game.moveHero('d');
        game.moveHero('d');

        assertTrue(game.isGameOver());
    }

    @Test
    public void testHeroCapturesKey(){
        Map map = new Map(map1);
        GameState game = new GameState(map);
        game.levelup();
        game.ogre.setCoordinates(1,4);
        game.hero.setCoordinates(1,1);
        game.key.setCoordinates(3,1);

        game.moveHero('s');
        game.moveHero('s');

        assertEquals('K', game.hero.getChar());
    }

    @Test
    public void testHeroFailExit(){
        Map map = new Map(map1);
        GameState game = new GameState(map);
        game.levelup();
        game.ogre.setCoordinates(1,4);
        game.hero.setCoordinates(1,1);
        game.key.setCoordinates(3,1);

        game.moveHero('w');

        assertEquals(GameState.States.PLAYING, game.getCurrent_state());
    }


    @Test
    public void testHeroExit(){
        Map map = new Map(map1);
        GameState game = new GameState(map);
        game.levelup();
        game.ogre.setCoordinates(1,4);
        game.hero.setCoordinates(1,1);
        game.key.setCoordinates(3,1);

        //get key
        game.moveHero('s');
        game.moveHero('s');

        //go to door
        game.moveHero('w');
        game.moveHero('w');
        game.moveHero('w');

        assertEquals(GameState.States.MAP_DONE, game.getCurrent_state());
    }

    @Test
    public void testHeroVictory(){
        Map map = new Map(map1);
        GameState game = new GameState(map);
        game.levelup();
        game.ogre.setCoordinates(1,3);
        game.hero.setCoordinates(1,1);
        game.key.setCoordinates(3,1);

        //get key
        game.moveHero('s');
        game.moveHero('s');

        //go to door
        game.moveHero('w');
        game.moveHero('w');
        game.moveHero('w');

        game.checkEvents();
        assertEquals(GameState.States.DONE, game.getCurrent_state());
    }

    @Test
    public void testOgreStunned(){
        Map map = new Map(map1);
        GameState game = new GameState(map);
        game.levelup();
        game.ogre.setCoordinates(1,3);
        game.hero.setCoordinates(1,1);
        game.club.setCoordinates(2,1);

        //get key
        game.moveHero('s');
        game.moveHero('w');
        game.moveHero('d');

        game.checkEvents();
        assertEquals('8', game.ogre.getChar());
    }

}