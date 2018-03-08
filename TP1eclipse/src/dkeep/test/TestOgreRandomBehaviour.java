package dkeep.test;
import org.junit.Test;
import static org.junit.Assert.*;

import dkeep.logic.*;

public class TestOgreRandomBehaviour {

    char[][] map1 = {

            { 'X', 'I', 'X', 'X', 'X' },
            { 'X', ' ', ' ', ' ', 'X' },
            { 'X', ' ', ' ', ' ', 'X' },
            { 'X', ' ', ' ', ' ', 'X' },
            { 'X', 'X', 'X', 'X', 'X' }
    };

    @Test(timeout = 1000)
    public void testRandomBehaviour(){
        Map map = new Map(map1);
        GameState game = new GameState(map);
        game.levelup();
        game.ogre.setCoordinates(1,4);
        game.hero.setCoordinates(1,1);
        game.key.setCoordinates(3,1);
    }

}
