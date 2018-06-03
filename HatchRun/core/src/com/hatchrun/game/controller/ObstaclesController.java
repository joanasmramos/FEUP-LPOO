package com.hatchrun.game.controller;

import com.badlogic.gdx.Gdx;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.CoinModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.ObstacleModel;
import com.hatchrun.game.model.entities.PowerUpModel;

public class ObstaclesController {

    private long lastTimeRegisteredObstacles = 0;

    /**
     * Deals with obstacles:
     */
    public void update(){
        if(System.currentTimeMillis() - lastTimeRegisteredObstacles >= GameModel.getInstance().OBSTACLE_TIME){
            generateObstacle();
            lastTimeRegisteredObstacles = System.currentTimeMillis();
        }
    }


    /**
     * Generates new obstacle.
     */
    private ObstacleModel generateObstacleAux() {
        ObstacleModel tempObs = null;


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
        return tempObs;
    }



    /**
     * Generates new obstacle.
     */
    public void generateObstacle() {
        ObstacleModel temp = generateObstacleAux();


       /* while(checkCollisionOtherObjetcs(temp)){
            temp = generateObstacleAux();
        }*/

       if(!checkCollisionOtherObjetcs(temp))
        GameModel.getInstance().getObstacles().add(temp);
    }


    /**
     * Checks collision between the given obstacle and other objects
     * @param tempObs Obstacle
     * @return True if collision, false otherwise
     */
    private boolean checkCollisionOtherObjetcs(ObstacleModel tempObs){


        for(CoinModel coin: GameModel.getInstance().getCoins()){
            if (GameController.isOverlapped(tempObs, new CoinModel(coin.getLane(), coin.getX(), coin.getY() + coin.getHeight() / 2))
                    || GameController.isOverlapped(tempObs, new CoinModel(coin.getLane(), coin.getX(), coin.getY() - coin.getHeight() / 2))
                    || GameController.isOverlapped(tempObs, new CoinModel(coin.getLane(), coin.getX(), coin.getY()))) {
                return true;
            }
        }


        for(PowerUpModel p: GameModel.getInstance().getPowerUps()){
            if (GameController.isOverlapped(tempObs, new CoinModel(p.getLane(), p.getX(), p.getY() + p.getHeight() / 2))
                    || GameController.isOverlapped(tempObs, new CoinModel(p.getLane(), p.getX(), p.getY() - p.getHeight() / 2))
                    || GameController.isOverlapped(tempObs, new CoinModel(p.getLane(), p.getX(), p.getY()))) {
                return true;
            }
        }

        for (ObstacleModel temp2 : GameModel.getInstance().getObstacles()) {

            if (GameController.isOverlapped(tempObs, new CoinModel(temp2.getLane(), temp2.getX(), temp2.getY() + 2*temp2.getHeight()))
                    || GameController.isOverlapped(tempObs, new CoinModel(temp2.getLane(), temp2.getX(), temp2.getY() - 2*temp2.getHeight()))
                    || GameController.isOverlapped(tempObs, new CoinModel(temp2.getLane(), temp2.getX(), temp2.getY()))) {
                return true;
            }
        }
        return false;
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
