package com.hatchrun.game.test;

import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.HatchModel;

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


    //Hatch Model
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
    public void testShielded(){
        HatchModel hModel = new HatchModel();
        hModel.setShielded(true);
        assertTrue(hModel.isShielded());
    }

}
