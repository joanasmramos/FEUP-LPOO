package com.hatchrun.game.model;

import com.badlogic.gdx.audio.Sound;
import com.hatchrun.game.model.entities.CoinModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.HatchModel;
import com.hatchrun.game.model.entities.ObstacleModel;
import com.hatchrun.game.model.entities.PowerUpModel;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;

public class GameModel {
    private HatchModel hatch;
    private ArrayList<ObstacleModel> obstacles;

    private ArrayList<CoinModel> coins;
    private int coinsCatched;
    private int coinValue;
    private int score;

    private ArrayList<PowerUpModel> powerUps;
    private static GameModel instance;
    public final int DEFAULT_SPEED = 150;
    public final int ACCELERATION = 50;
    public boolean speedFixed;
    public int goalSpeed = DEFAULT_SPEED;
    public int speed = 0;
    public final int GOAL_REACH_ACCELERATION = 200;

    Sound catchCoin = Gdx.audio.newSound(Gdx.files.internal("soundEffects/catchcoin.mp3"));


    public GameModel(int hatchX, int hatchY){
        instance = this;
        hatch = new HatchModel(EntityModel.ElementLane.MIDDLE,hatchX,hatchY);
        obstacles = new ArrayList<ObstacleModel>();
        coins = new ArrayList<CoinModel>();
        powerUps = new ArrayList<PowerUpModel>();
        speedFixed = false;
        coinsCatched = 0;
        coinValue = 1;
        score = 0;
    }


    /**
     * Returns instance of game model
     * @return model
     */
    public static GameModel getInstance() {
        return instance;
    }

    /**
     * Returns hatch object
     * @return hatch
     */
    public HatchModel getHatch() {
        return hatch;
    }

    /**
     * Adds obstacle to game obstacles
     * @param o obstacle
     */
    public void addObstacle(ObstacleModel o) {
        obstacles.add(o);
    }

    /**
     * Adds coin to game coins
     * @param coin coin
     */
    public void addCoin(CoinModel coin) {
        coins.add(coin);
    }

    /**
     * Returns game obstacles
     * @return ArrayList of obstacles
     */
    public ArrayList<ObstacleModel> getObstacles() {
        return obstacles;
    }

    /**
     * Returns game coins
     * @return ArrayList of coins
     */
    public ArrayList<CoinModel> getCoins() {
        return coins;
    }


    public void setSpeedFixed (boolean speedFixed) {
        this.speedFixed = speedFixed;
    }


    /**
     * Increment score and coinsCatched
     */
    public void addCoinCatched() {
         coinsCatched++;
         score++;
    }

    /**
     * Returns game score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets game score
     * @param score game score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns sound effect for catching coin
     * @return sound effect for catching coin
     */
    public Sound getCatchCoin() {
        return catchCoin;
    }
}
