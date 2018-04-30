package com.hatchrun.game.model;

import com.hatchrun.game.model.entities.EntitieModel;
import com.hatchrun.game.model.entities.HatchModel;
import com.hatchrun.game.model.entities.PowerUpModel;

import java.util.ArrayList;

public class GameModel {
    private HatchModel hatch;
    private ArrayList<Object> obstacles;
    private ArrayList<PowerUpModel> powerUps;
    private GameModel instance;

    public GameModel(EntitieModel.ElementLane lane, float x, float y){
        instance = this;
        hatch = new HatchModel(EntitieModel.ElementLane.MIDDLE,100,100);
        obstacles = null;
        powerUps = null;
    }
}
