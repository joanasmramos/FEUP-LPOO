package com.hatchrun.game.model;

import com.hatchrun.game.model.entities.CoinModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.HatchModel;
import com.hatchrun.game.model.entities.ObstacleModel;
import com.hatchrun.game.model.entities.PowerUpModel;

import java.util.ArrayList;

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


    public static GameModel getInstance() {
        return instance;
    }

    public HatchModel getHatch() {
        return hatch;
    }

    public void setHatch(HatchModel hatch) {
        this.hatch = hatch;
    }


    public void addObstacle(ObstacleModel o) {
        obstacles.add(o);
    }

    public void addCoin(CoinModel coin) {
        coins.add(coin);
    }


    public ArrayList<ObstacleModel> getObstacles() {
        return obstacles;
    }

    public ArrayList<CoinModel> getCoins() {
        return coins;
    }

    public void setSpeedFixed (boolean speedFixed) {
        this.speedFixed = speedFixed;
    }
}
