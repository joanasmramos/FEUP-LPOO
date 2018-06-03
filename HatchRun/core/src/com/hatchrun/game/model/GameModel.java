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

    private int coinValue;
    private int score;

    private ArrayList<PowerUpModel> powerUps;
    private static GameModel instance;
    public int OBSTACLE_TIME = 2000;
    public int COIN_TIME = 3000;
    public int POWER_TIME = 7000;
    public final double ACCELERATION = 1.005;
    public int speed = 300;

    private static ArrayList<HatchModel.HatchType> hatchOrder = new ArrayList<HatchModel.HatchType>();
    private static int hatchOrderIndex = 1;

    public Sound getCatchCoin() {
        return catchCoin;
    }

    public void setCatchCoin(Sound catchCoin) {
        this.catchCoin = catchCoin;
    }

    private Sound catchCoin;
    private boolean muted = false;
    private boolean over;

    /**
     * Constructs a Game Model
     */
    public GameModel(){
        instance = this;
        hatch = new HatchModel(EntityModel.ElementLane.MIDDLE, GameController.centerX,GameController.startY);
        obstacles = new ArrayList<ObstacleModel>();
        coins = new ArrayList<CoinModel>();
        powerUps = new ArrayList<PowerUpModel>();
        coinValue = 10;
        score = 0;
        over =  false;
        initHatchOrder();
    }


    public GameModel(float x, float y){
        instance = this;
        hatch = new HatchModel(EntityModel.ElementLane.MIDDLE, x,y);
        obstacles = new ArrayList<ObstacleModel>();
        coins = new ArrayList<CoinModel>();
        powerUps = new ArrayList<PowerUpModel>();
        coinValue = 10;
        score = 0;
        over =  false;
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

    public  boolean isOver() {
        return over;
    }

    public  void setOver(boolean over) {
        this.over = over;
    }


    /**
     * Returns hatch object
     * @return hatch
     */
    public HatchModel getHatch() {
        return hatch;
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




    /**
     * Increment score and coinsCatched
     */
    public void addCoinCatched() {
         score += coinValue;
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
     * Returns array list of game power ups
     * @return array list of game power ups
     */
    public ArrayList<PowerUpModel> getPowerUps() {
        return powerUps;
    }

    /**
     * Adds a power up to the game
     * @param p power up
     */
    public void addPowerUp(PowerUpModel p) {
        powerUps.add(p);
    }

    /**
     * Returns hatch order for the menu
     * @return hatch order
     */
    public  ArrayList<HatchModel.HatchType> getHatchOrder() {
        return hatchOrder;
    }

    /**
     * Returns hatch order index
     * @return hatch order index
     */
    public int getHatchOrderIndex() {
        return hatchOrderIndex;
    }

    /**
     * Sets hatch order index
     * @param index hatch order index
     */
    public void setHatchOrderIndex(int index) {
        hatchOrderIndex = index;
    }

    /**
     * Initialises Hatch order for the menu
     */
    private void initHatchOrder(){
        hatchOrder.add(HatchModel.HatchType.BLUE);
        hatchOrder.add(HatchModel.HatchType.PURPLE);
        hatchOrder.add(HatchModel.HatchType.YELLOW);


    }

    /**
     * Sets the coin value
     * @param coinValue coin value
     */
    public void setCoinValue(int coinValue) {
        this.coinValue = coinValue;
    }

    /**
     * Returns the coin value
     * @return coin value
     */
    public int getCoinValue() {
        return coinValue;
    }


    /**
     * Plays catch coin sound if the game is not muted
     */
    public void playCoinSound() {
        if(!muted)
            this.catchCoin.play();
    }


    /**
     * Mutes/unmutes the game
     * @param mute true to mute, false to unmute
     */
    public void setMuted(boolean mute) {
        muted = mute;
    }

    public void resetModel(){
        hatch = new HatchModel(EntityModel.ElementLane.MIDDLE, GameController.centerX,GameController.startY);
        obstacles.clear();
        coins.clear();
        powerUps.clear();
        coinValue = 10;
        score = 0;
        over =  false;
    }

}
