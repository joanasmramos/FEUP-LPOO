package com.hatchrun.game.controller;

import com.badlogic.gdx.Gdx;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.CoinModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.ObstacleModel;
import com.hatchrun.game.model.entities.PowerUpModel;

import java.util.ArrayList;

public class PowerUpController {

    private long lastTimeRegistered = 0;
    private long doubleCoinsPickedUpTime = 0;
    private long shieldPickedUpTime = 0;
    private final int COIN_VALUE = 10;


    public void update() {
        if (System.currentTimeMillis() - lastTimeRegistered >= GameModel.getInstance().POWER_TIME) {
            generatePowerUp();
            lastTimeRegistered = System.currentTimeMillis();
        }


        if(GameModel.getInstance().getCoinValue()!=1) {
            if (System.currentTimeMillis() - doubleCoinsPickedUpTime >= 10000) {
                GameModel.getInstance().setCoinValue(COIN_VALUE);
                doubleCoinsPickedUpTime = System.currentTimeMillis();
            }
        }


        if(GameModel.getInstance().getHatch().isShielded()) {

            if(System.currentTimeMillis() - shieldPickedUpTime >= 10000) {
                GameModel.getInstance().getHatch().setShielded(false);
                shieldPickedUpTime = System.currentTimeMillis();
            }
        }

        catchPowerUp();
    }

    private PowerUpModel generatePowerUpAux() {
        PowerUpModel temp = null;


        switch (GameController.rand.nextInt(3)) {
            case 0:
                temp = new PowerUpModel(EntityModel.ElementLane.LEFT, GameController.leftX + 30, Gdx.graphics.getHeight(), generateType());
                break;
            case 1:
                temp = new PowerUpModel(EntityModel.ElementLane.MIDDLE, GameController.leftX + 30, Gdx.graphics.getHeight(), generateType());
                break;
            case 2:
                temp = new PowerUpModel(EntityModel.ElementLane.RIGHT, GameController.leftX + 30, Gdx.graphics.getHeight(), generateType());
                break;
            default:
                break;
        }
        return temp;
    }

    public void generatePowerUp() {


        PowerUpModel temp = generatePowerUpAux();
        if (!checkCollisionOtherObjetcs(temp))
            GameModel.getInstance().addPowerUp(temp);


    }


    /**
     * Generates an obstacle colour.
     *
     * @return generated colour
     */
    private PowerUpModel.PowerUpType generateType() {
        switch (GameController.rand.nextInt(2)) {
            case 0:
                return PowerUpModel.PowerUpType.DOUBLECOINS;
            case 1:
                return PowerUpModel.PowerUpType.SHIELD;
            default:
                return PowerUpModel.PowerUpType.DOUBLECOINS;
        }
    }


    private boolean checkCollisionOtherObjetcs(PowerUpModel tempP) {
        ObstacleModel temp2;

        for(CoinModel coin: GameModel.getInstance().getCoins()){
            if (GameController.isOverlapped(tempP, new CoinModel(coin.getLane(), coin.getX(), coin.getY() + coin.getHeight() / 2))
                    || GameController.isOverlapped(new CoinModel(coin.getLane(), coin.getX(), coin.getY() - coin.getHeight() / 2),tempP)
                    || GameController.isOverlapped( new CoinModel(coin.getLane(), coin.getX(), coin.getY()),tempP)) {
                return true;
            }
        }

        if(GameModel.getInstance().getObstacles().size()!=0) {
            temp2 = GameModel.getInstance().getObstacles().get(GameModel.getInstance().getObstacles().size() - 1);

            if (GameController.isOverlapped(new ObstacleModel(temp2.getLane(), temp2.getX(), temp2.getY() + 2*temp2.getHeight(),temp2.getColour()),tempP)
                    || GameController.isOverlapped(new ObstacleModel(temp2.getLane(), temp2.getX(), temp2.getY() - 2*temp2.getHeight(),temp2.getColour()),tempP)
                    || GameController.isOverlapped(new ObstacleModel(temp2.getLane(), temp2.getX(), temp2.getY(),temp2.getColour()),tempP)) {
                return true;
            }
        }
        return false;

    }

    private void catchPowerUp(){
        PowerUpModel temp;

        ArrayList<PowerUpModel> pToRemove= new ArrayList<PowerUpModel>();

        for(PowerUpModel p : GameModel.getInstance().getPowerUps()){
            temp = new PowerUpModel(p.getLane(), p.getX(), p.getY()+p.getHeight()/4,  p.getType());
            if(GameController.isOverlapped(GameModel.getInstance().getHatch(), temp))
                pToRemove.add(p);
        }


        for(PowerUpModel p : pToRemove){
            GameModel.getInstance().getPowerUps().remove(p);

            if(p.getType()== PowerUpModel.PowerUpType.DOUBLECOINS) {
                doubleCoinsPickedUpTime = System.currentTimeMillis();
                GameModel.getInstance().setCoinValue(COIN_VALUE*2);
            }

            else if(p.getType()== PowerUpModel.PowerUpType.SHIELD) {
                shieldPickedUpTime = System.currentTimeMillis();
                GameModel.getInstance().getHatch().setShielded(true);
            }

        }

        }

}