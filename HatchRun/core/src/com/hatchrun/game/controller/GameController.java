package com.hatchrun.game.controller;

import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.ObstacleModel;
import java.util.ArrayList;

public class GameController {
    private static GameController instance;
    private ArrayList<ObstacleModel> obstaclesToAdd = new ArrayList<ObstacleModel>();
    public static final int WORLD_WIDTH = 100;
    public static final int WORLD_HEIGHT = 50;

    public GameController(){

    }

    public static GameController getInstance() {
        if(instance == null) instance = new GameController();

        return instance;
    }

    public void update(float delta){
        GameModel.getInstance().update(delta);
    }
}
