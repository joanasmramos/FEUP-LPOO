package com.hatchrun.game.model;

import com.badlogic.gdx.Gdx;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.HatchModel;
import com.hatchrun.game.model.entities.PowerUpModel;

import java.util.ArrayList;

public class GameModel {
    private HatchModel hatch;
    private ArrayList<Object> obstacles;
    private ArrayList<PowerUpModel> powerUps;
    private static GameModel instance;


    public GameModel(int hatchX, int hatchY){
        instance = this;
        hatch = new HatchModel(EntityModel.ElementLane.MIDDLE,hatchX,hatchY);
        obstacles = new ArrayList<Object>();
        powerUps = new ArrayList<PowerUpModel>();
    }


    public static GameModel getInstance() {
        return instance;
    }

    public HatchModel getHatch() {
        return hatch;
    }

    public void setHatch(HatchModel hatch) {
        this.hatch = hatch;
    }

}
