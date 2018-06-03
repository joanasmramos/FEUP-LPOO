package com.hatchrun.game.test;

import com.hatchrun.game.controller.GameController;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.HatchModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ControllerTest {


    @Test
    public void testAddObstacle(){
        assertEquals(0, GameModel.getInstance().getObstacles().size());
        GameController.getObstaclesController().generateObstacle();
        assertEquals(1, GameModel.getInstance().getObstacles().size());
    }

    @Test
    public void testAddCoins(){
        assertEquals(0, GameModel.getInstance().getCoins().size());
        GameController.getCoinsController().generateCoins();
        assertEquals(1, GameModel.getInstance().getCoins().size());
    }

    @Test
    public void testAddPowerUps(){
        assertEquals(0, GameModel.getInstance().getPowerUps().size());
        GameController.getPowerUpController().generatePowerUp();
        assertEquals(1, GameModel.getInstance().getPowerUps().size());
    }


    @Test
    public void testHatchCanMove(){
        GameController g = new GameController();

        float xOld = GameModel.getInstance().getHatch().getX();

        g.moveHatch(true);

        assertNotEquals(xOld,GameModel.getInstance().getHatch().getX());

    }
}
