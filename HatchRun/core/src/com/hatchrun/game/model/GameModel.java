package com.hatchrun.game.model;

import com.badlogic.gdx.audio.Sound;
import com.hatchrun.game.controller.GameController;
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
    public int OBSTACLE_TIME = 2000;
    public int COIN_TIME = 2500;
    public int POWER_TIME = 7000;
    public final double ACCELERATION = 1.005;
    public boolean speedFixed;
    public int speed = 300;

    private static ArrayList<HatchModel.HatchType> hatchOrder = new ArrayList<HatchModel.HatchType>();
    private static int hatchOrderIndex = 1;

    Sound catchCoin = Gdx.audio.newSound(Gdx.files.internal("soundEffects/catchcoin.mp3"));


    public GameModel(){
        instance = this;
        hatch = new HatchModel(EntityModel.ElementLane.MIDDLE, GameController.centerX,GameController.startY);
        obstacles = new ArrayList<ObstacleModel>();
        coins = new ArrayList<CoinModel>();
        powerUps = new ArrayList<PowerUpModel>();
        speedFixed = false;
        coinsCatched = 0;
        coinValue = 1;
        score = 0;
        initHatchOrder();
    }




    /**
     * Returns instance of game model
     * @return model
     */
    public static GameModel getInstance() {
        if (instance == null)
            new GameModel();
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

    public ArrayList<PowerUpModel> getPowerUps() {
        return powerUps;
    }

    public void addPowerUp(PowerUpModel p) {
        powerUps.add(p);
    }

    public static ArrayList<HatchModel.HatchType> getHatchOrder() {
        return hatchOrder;
    }

    public static int getHatchOrderIndex() {
        return hatchOrderIndex;
    }

    public static void setHatchOrderIndex(int index) {
        hatchOrderIndex = index;
    }

    public static void initHatchOrder(){
        hatchOrder.add(HatchModel.HatchType.BLUE);
        hatchOrder.add(HatchModel.HatchType.PURPLE);
        hatchOrder.add(HatchModel.HatchType.YELLOW);


    }
}
