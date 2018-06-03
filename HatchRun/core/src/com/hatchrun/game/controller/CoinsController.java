package com.hatchrun.game.controller;

import com.badlogic.gdx.Gdx;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.CoinModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.ObstacleModel;
import com.hatchrun.game.model.entities.PowerUpModel;

import java.util.ArrayList;

public class CoinsController {

    private long lastTimeRegisteredCoins = 0;

    /**
     * Deals with coins: generates and removes them
     */
    public void update(){
        if(System.currentTimeMillis() - lastTimeRegisteredCoins >= GameModel.getInstance().COIN_TIME){
            generateCoins();
            lastTimeRegisteredCoins = System.currentTimeMillis();
        }

        catchCoins();

    }


    /**
     * Generates new coin.
     * @return generated coin
     */
    private CoinModel generateCoin() {

        CoinModel coin = null;

        switch (GameController.rand.nextInt(3)){
            case 0:
                coin = new CoinModel(EntityModel.ElementLane.LEFT,GameController.leftX + 40, Gdx.graphics.getHeight());
                break;
            case 1:
                coin = new CoinModel(EntityModel.ElementLane.MIDDLE,GameController.centerX+40,Gdx.graphics.getHeight());
                break;
            case 2:
                coin = new CoinModel(EntityModel.ElementLane.RIGHT,GameController.rightX+40,Gdx.graphics.getHeight());
                break;
            default:
                break;
        }
        return coin;
    }

    /**
     * Generates multiple coins in a row.
     */
    private void generateCoins() {


        ArrayList<CoinModel> coinsToAdd = new ArrayList<CoinModel>();
        CoinModel temp = generateCoin();
        int j = 0;
        int i = GameController.rand.nextInt(5);

        while (j < i) {
            coinsToAdd.add(new CoinModel(temp.getLane(), temp.getX(), temp.getY()));
            temp.setY(temp.getY() + 3 * temp.getHeight() / 2);
            j++;
        }

        for (CoinModel c : coinsToAdd) {

            if(!checkCollisionOtherObjetcs(c))
                GameModel.getInstance().addCoin(c);
        }

    }


    private boolean checkCollisionOtherObjetcs(CoinModel coin){

        for (ObstacleModel o : GameModel.getInstance().getObstacles()) {
            if (GameController.isOverlapped(o, new CoinModel(coin.getLane(), coin.getX(), coin.getY() + coin.getHeight() / 2))
                    || GameController.isOverlapped(o, new CoinModel(coin.getLane(), coin.getX(), coin.getY() - coin.getHeight() / 2))
                    || GameController.isOverlapped(o, new CoinModel(coin.getLane(), coin.getX(), coin.getY()))) {
                return true;
            }
        }

        for (PowerUpModel o : GameModel.getInstance().getPowerUps()) {
            if (GameController.isOverlapped(o, new CoinModel(coin.getLane(), coin.getX(), coin.getY() + coin.getHeight() / 2))
                    || GameController.isOverlapped(o, new CoinModel(coin.getLane(), coin.getX(), coin.getY() - coin.getHeight() / 2))
                    || GameController.isOverlapped(o, new CoinModel(coin.getLane(), coin.getX(), coin.getY()))) {
                return true;
            }
        }


        for (CoinModel c : GameModel.getInstance().getCoins()) {
            if (GameController.isOverlapped(c, new CoinModel(c.getLane(), coin.getX(), coin.getY() + coin.getHeight() / 4))
                    || GameController.isOverlapped(c, new CoinModel(c.getLane(), coin.getX(), coin.getY() - coin.getHeight() / 4))
                    || GameController.isOverlapped(c, new CoinModel(c.getLane(), coin.getX(), coin.getY()))) {
                return true;
            }
        }

        return false;
    }


    /**
     * Removes catched coins
     */
    private void catchCoins(){
        CoinModel tempCoin;

        ArrayList<CoinModel> coinsToRemove= new ArrayList<CoinModel>();

        for(CoinModel coin : GameModel.getInstance().getCoins()){
            tempCoin = new CoinModel(coin.getLane(), coin.getX(), coin.getY()+coin.getHeight()/4);
            if(GameController.isOverlapped(GameModel.getInstance().getHatch(), tempCoin)) {
                coinsToRemove.add(coin);
            }
        }


        for(CoinModel coin : coinsToRemove){
            GameModel.getInstance().playCoinSound();
            GameModel.getInstance().getCoins().remove(coin);
            GameModel.getInstance().addCoinCatched();
        }
    }

}
