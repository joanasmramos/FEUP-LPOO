package com.hatchrun.game.test;

import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.CoinModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.HatchModel;
import com.hatchrun.game.model.entities.ObstacleModel;
import com.hatchrun.game.model.entities.PowerUpModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ModelTest {

    //Game Model
    @Test
    public void testOver(){
        GameModel model = new GameModel(0,0);
        assertFalse(model.isOver());
        model.setOver(true);
        assertTrue(model.isOver());
    }


    @Test
    public void testAddCoins(){
        GameModel model = new GameModel(0,0);
        assertEquals(0,model.getCoins().size());
        model.addCoin(new CoinModel(EntityModel.ElementLane.LEFT,0,0));
        assertEquals(1,model.getCoins().size());
    }

    @Test
    public void testAddPowerUps(){
        GameModel model = new GameModel(0,0);
        assertEquals(0,model.getPowerUps().size());
        model.addPowerUp(new PowerUpModel(EntityModel.ElementLane.LEFT,0,0, PowerUpModel.PowerUpType.SHIELD));
        assertEquals(1,model.getPowerUps().size());
    }


    @Test
    public void testAddObstacles(){
        GameModel model = new GameModel(0,0);
        assertEquals(0,model.getObstacles().size());
        model.getObstacles().add(new ObstacleModel(EntityModel.ElementLane.LEFT,0,0, ObstacleModel.Colour.PINK));
        assertEquals(1,model.getObstacles().size());

    }


    //Entity Model
    @Test
    public void testLane(){
        HatchModel hModel = new HatchModel();
        assertEquals(hModel.getLane(), EntityModel.ElementLane.MIDDLE);
        hModel.setLane(EntityModel.ElementLane.LEFT);
        assertEquals(hModel.getLane(), EntityModel.ElementLane.LEFT);
    }

    @Test
    public void testHeight(){
        HatchModel hModel = new HatchModel();
        assertEquals(hModel.getHeight(), 301);
        hModel.setHeigth(400);
        assertEquals(400,hModel.getHeight());
    }

    @Test
    public void testWidth(){
        HatchModel hModel = new HatchModel();
        assertEquals(hModel.getWidth(), 200);
        hModel.setWidth(400);
        assertEquals(400,hModel.getWidth());
    }

    //Test Hatch
    @Test
    public void testShielded(){
        HatchModel hModel = new HatchModel();
        hModel.setShielded(true);
        assertTrue(hModel.isShielded());
    }

    @Test
    public void testState(){
        HatchModel hModel = new HatchModel();
        assertEquals(hModel.getCurrentState(), HatchModel.HatchState.STILL);
        hModel.setCurrentState(HatchModel.HatchState.RUNNING);
        assertEquals(hModel.getCurrentState(), HatchModel.HatchState.RUNNING);
    }

    //Test Obstacle
    @Test
    public void testColour(){
        ObstacleModel o = new ObstacleModel(EntityModel.ElementLane.LEFT,0,0, ObstacleModel.Colour.PINK);
        assertEquals(o.getColour(), ObstacleModel.Colour.PINK);
    }

    //Test Power Ups
    @Test
    public void testType(){
        PowerUpModel u = new PowerUpModel(EntityModel.ElementLane.LEFT,0,0, PowerUpModel.PowerUpType.SHIELD);
        assertEquals(PowerUpModel.PowerUpType.SHIELD,u.getType());
        u.setType(PowerUpModel.PowerUpType.DOUBLECOINS);
        assertEquals(PowerUpModel.PowerUpType.DOUBLECOINS,u.getType());

    }
}
