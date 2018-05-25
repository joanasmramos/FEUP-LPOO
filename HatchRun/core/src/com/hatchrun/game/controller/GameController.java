package com.hatchrun.game.controller;

import com.badlogic.gdx.Gdx;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.HatchModel;
import com.hatchrun.game.model.entities.ObstacleModel;
import com.hatchrun.game.view.GameView;

import java.util.ArrayList;

public class GameController {
    private static GameController instance;
    private ArrayList<ObstacleModel> obstaclesToAdd = new ArrayList<ObstacleModel>();
    public static final int playableWidth = (Gdx.graphics.getWidth()-(int)(2*Gdx.graphics.getWidth()*0.09));
    public static final int startX =(int)( Gdx.graphics.getWidth()*0.09);
    public static final int leftX = startX;
    public static final int centerX =(int)( startX+1*(9+playableWidth/3));
    public static final int rightX =(int)( startX+2*(9+playableWidth/3));

    public static final int startY =  (int) (0.03*Gdx.graphics.getHeight());




    public GameController(){

    }

    public static GameController getInstance() {
        if(instance == null) instance = new GameController();

        return instance;
    }


    public void moveHatch(boolean side){
        //if side = 1 then move right
        //else move left
        if(side) {
            switch (GameModel.getInstance().getHatch().getLane()) {
                case LEFT:
                    GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.MIDDLE);
                    GameModel.getInstance().getHatch().setX(centerX);
                    break;
                case MIDDLE:
                    GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.RIGHT);
                    GameModel.getInstance().getHatch().setX(rightX);
                    break;
                 default:
                     break;
            }
        }else{
            switch (GameModel.getInstance().getHatch().getLane()) {
                case RIGHT:
                    GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.MIDDLE);
                    GameModel.getInstance().getHatch().setX(centerX);
                    break;
                case MIDDLE:
                    GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.LEFT);
                    GameModel.getInstance().getHatch().setX(leftX);
                    break;
                default:
                    break;
            }
        }

    }

}
