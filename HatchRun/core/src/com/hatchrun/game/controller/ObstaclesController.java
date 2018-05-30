package com.hatchrun.game.controller;

import com.badlogic.gdx.Gdx;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.CoinModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.ObstacleModel;

public class ObstaclesController {

    private long lastTimeRegisteredObstacles = 0;

    public void update(){
        if(System.currentTimeMillis() - lastTimeRegisteredObstacles >= GameModel.getInstance().OBSTACLE_TIME){
            generateObstacle();
            lastTimeRegisteredObstacles = System.currentTimeMillis();
        }
    }


    /**
     * Generates new obstacle.
     */
    private void generateObstacle() {
        ObstacleModel tempObs = null;
        ObstacleModel temp2;


        switch (GameController.rand.nextInt(3)){
            case 0:
                tempObs = new ObstacleModel(EntityModel.ElementLane.LEFT,GameController.leftX, Gdx.graphics.getHeight(), generateColour());
                break;
            case 1:
                tempObs = new ObstacleModel(EntityModel.ElementLane.MIDDLE,GameController.centerX,Gdx.graphics.getHeight(), generateColour());
                break;
            case 2:
                tempObs = new ObstacleModel(EntityModel.ElementLane.RIGHT,GameController.rightX,Gdx.graphics.getHeight(), generateColour());
                break;
            default:
                break;
        }


        for(CoinModel coin: GameModel.getInstance().getCoins()){
            if(GameController.checkOverlap(tempObs,coin)) return;
        }

        if(GameModel.getInstance().getObstacles().size()!=0) {
            temp2 = GameModel.getInstance().getObstacles().get(GameModel.getInstance().getObstacles().size() - 1);
            temp2.setHeigth(GameModel.getInstance().getHatch().getHeight() + GameModel.getInstance().getHatch().getHeight() );
            if (GameController.checkOverlap(temp2, tempObs)) return;
        }
        GameModel.getInstance().getObstacles().add(tempObs);
    }


    /**
     * Generates an obstacle colour.
     * @return generated colour
     */
    private ObstacleModel.Colour generateColour(){
        switch (GameController.rand.nextInt(3)){
            case 0:
                return ObstacleModel.Colour.PINK;
            case 1:
                return ObstacleModel.Colour.BLUE;
            case 2:
                return ObstacleModel.Colour.YELLOW;
            default:
                return ObstacleModel.Colour.PINK;
        }
    }
}
