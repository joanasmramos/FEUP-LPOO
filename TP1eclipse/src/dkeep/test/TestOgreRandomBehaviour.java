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

    @Test (timeout = 1000)
    public void testRandomBehaviour(){
        Map map = new Map(map1);
        GameState game = new GameState(map);
        game.levelup();
        game.ogre.setCoordinates(1,3);
        game.hero.setCoordinates(1,1);
        game.key.setCoordinates(3,1);

        boolean up_up = false;
        boolean up_down = false;
        boolean up_right = false;
        boolean up_left= false;
        boolean down_down = false;
        boolean down_up = false;
        boolean down_right = false;
        boolean down_left = false;
        boolean left_left = false;
        boolean left_right = false;
        boolean left_up = false;
        boolean left_down = false;
        boolean right_right = false;
        boolean right_left = false;
        boolean right_down = false;
        boolean right_up = false;

        while(!up_up || !up_down || !up_right || !up_left
                || !down_down || !down_up || !down_left || !down_right
                || !right_down || !right_left || !right_right || !right_up
                || !left_down || !left_left || !left_right || !left_up)
        {

            game.moveOgre(game.ogre);


           if(game.ogre.getDir() == 'w'){

                if(game.ogre.getOgre_club().getDir() == 'w')
                    up_up = true;
                else if(game.ogre.getOgre_club().getDir() == 's')
                    up_down = true;
                else if(game.ogre.getOgre_club().getDir() == 'd')
                    up_right = true;
                else if(game.ogre.getOgre_club().getDir() == 'a')
                    up_left = true;


            }else if(game.ogre.getDir() == 's'){

                if(game.ogre.getOgre_club().getDir() == 'w')
                    down_up = true;
                else if(game.ogre.getOgre_club().getDir() == 's')
                    down_down = true;
                else if(game.ogre.getOgre_club().getDir() == 'd')
                    down_right = true;
                else if(game.ogre.getOgre_club().getDir() == 'a')
                    down_left = true;


            }else if(game.ogre.getDir() == 'd'){

                if(game.ogre.getOgre_club().getDir() == 'w')
                    right_up = true;
                else if(game.ogre.getOgre_club().getDir() == 's')
                    right_down = true;
                else if(game.ogre.getOgre_club().getDir() == 'd')
                    right_right = true;
                else if(game.ogre.getOgre_club().getDir() == 'a')
                    right_left = true;

           }else if(game.ogre.getDir() == 'a'){

                if(game.ogre.getOgre_club().getDir() == 'w')
                    left_up = true;
                else if(game.ogre.getOgre_club().getDir() == 's')
                    left_down = true;
                else if(game.ogre.getOgre_club().getDir() == 'd')
                    left_right = true;
                else if(game.ogre.getOgre_club().getDir() == 'a')
                    left_left = true;

            }else fail();
        }


    }

}
