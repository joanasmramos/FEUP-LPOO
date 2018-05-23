package com.hatchrun.game.controller;

import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.EntityModel;
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

    public void moveHatch(boolean side){
        //if side = 1 then move right
        //else move left
        if(side) {
            switch (GameModel.getInstance().getHatch().getLane()) {
                case LEFT:
                    GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.MIDDLE);
                    break;
                case MIDDLE:
                    GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.RIGHT);
                    break;
                 default:
                     break;
            }
        }else{
            switch (GameModel.getInstance().getHatch().getLane()) {
                case RIGHT:
                    GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.MIDDLE);
                    break;
                case MIDDLE:
                    GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.LEFT);
                    break;
                default:
                    break;
            }
        }

    }

}
