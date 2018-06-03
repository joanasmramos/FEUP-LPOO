package com.hatchrun.game.controller;

import com.badlogic.gdx.Gdx;
import com.hatchrun.game.Utilities.Pair;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.CoinModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.HatchModel;
import com.hatchrun.game.model.entities.ObstacleModel;
import com.hatchrun.game.model.entities.PowerUpModel;

import java.util.ArrayList;
import java.util.Random;

public class GameController{
    private static GameController instance;
    private static  CoinsController coinsController = new CoinsController();
    private static  ObstaclesController obstaclesController = new ObstaclesController();
    private static  PowerUpController powerUpController = new PowerUpController();
    private static final int playableWidth = (Gdx.graphics.getWidth()-(int)(2*Gdx.graphics.getWidth()*0.09));
    private static final int startX =(int)( Gdx.graphics.getWidth()*0.09);
    public static final int leftX = startX;
    public static final int centerX =(int)( startX+(9+playableWidth/3));
    public static final int rightX =(int)( startX+2*(9+playableWidth/3));
    public static final int widthLane = centerX - leftX;
    public static final int startY =  (int) (0.03*Gdx.graphics.getHeight());
    private long lastSecondRegistered = 0;
    public static Random rand;

    private GameController(){
        rand = new Random();
    }


    /**
     * Singleton
     * @return intance
     */
    public static GameController getInstance() {
        if(instance == null) instance = new GameController();

        return instance;
    }


    /**
     * Main controller function. Generates, destroys objects.
     * @param delta
     */
    public void update(float delta){

        coinsController.update();
        obstaclesController.update();
        powerUpController.update();

        updateSpeed(delta);


        if(System.currentTimeMillis() - lastSecondRegistered >= 1000){
            if(delta != 0) {
                GameModel.getInstance().setScore(GameModel.getInstance().getScore() + 1);
            }
            lastSecondRegistered = System.currentTimeMillis();
        }

        updateObjects(delta);
        disposeObjects();
        if(!GameModel.getInstance().getHatch().isShielded())
            checkColission();

    }

    /**
     * Generates new coins and obstacles
     * @param delta
     */
    private void updateObjects(float delta){

        ArrayList<ObstacleModel> obstacles = GameModel.getInstance().getObstacles();

        for(ObstacleModel o :  obstacles){
            o.setY(o.getY()-GameModel.getInstance().speed*delta);
        }

        ArrayList<CoinModel> coins = GameModel.getInstance().getCoins();


        for(CoinModel c :  coins){
            c.setY(c.getY()-GameModel.getInstance().speed*delta);
        }

        ArrayList<PowerUpModel> powerUps = GameModel.getInstance().getPowerUps();

        for(PowerUpModel p :  powerUps){
            p.setY(p.getY()-GameModel.getInstance().speed*delta);
        }
    }


    /**
     * Removes objects no longer in the game area
     */
    private void disposeObjects(){

        ArrayList<ObstacleModel> obstacles = GameModel.getInstance().getObstacles();
        ArrayList<ObstacleModel> obstaclesToRemove = new ArrayList<ObstacleModel>();
        ArrayList<CoinModel> coins = GameModel.getInstance().getCoins();
        ArrayList<CoinModel> coinsToRemove = new ArrayList<CoinModel>();


        for(ObstacleModel o :  obstacles){
             if(o.getY() < -266)
                 obstaclesToRemove.add(o);
        }

        for(ObstacleModel o: obstaclesToRemove){
            obstacles.remove(o);
        }

        for(CoinModel c :  coins){
            if(c.getY() < -266)
                coinsToRemove.add(c);
        }

        for(CoinModel c: coinsToRemove){
            coins.remove(c);
        }
    }


    /**
     * Updates game speed.
     * @param delta
     */
    private void updateSpeed(float delta){

        if (System.currentTimeMillis() - lastSecondRegistered >= 1000) {
            GameModel.getInstance().speed *= GameModel.getInstance().ACCELERATION;
            GameModel.getInstance().OBSTACLE_TIME -= GameModel.getInstance().OBSTACLE_TIME * 0.01;
            GameModel.getInstance().COIN_TIME -= GameModel.getInstance().COIN_TIME *0.01;
        }

    }


    /**
     * Checks if hatch colides with obstacles
     * @return true if colided, false if elese
     */
    private boolean checkColission(){

        ObstacleModel obs = null;

        for(ObstacleModel o : GameModel.getInstance().getObstacles()) {
            obs = o;
            //obs.setHeigth((int)(o.getHeight()-0.02*o.getHeight()));
           if(isOverlapped( GameModel.getInstance().getHatch(),obs))
               System.exit(1);
           return true;
        }
        return false;
    }


    /**
     * Checks if two entities overlad
     * @param entity1
     * @param entity2
     * @return true if overlad, false if else
     */
    public static boolean isOverlapped(EntityModel entity1, EntityModel entity2){


        double cx1 = entity1.getX() + entity1.getWidth()/2;
        double cx2 = entity2.getX() + entity2.getWidth()/2;
        double dx = cx2-cx1;

        double cy1 = entity1.getY() + entity1.getHeight()/2;
        double cy2 = entity2.getY() + entity2.getHeight()/2;
        double dy = cy2-cy1;

        double h = Math.sqrt(dx*dx + dy*dy);

        return(h - 30 < (entity1.getWidth()/2 + entity2.getWidth()/2));
    }


    /**
     * Figures out which lane the hatch is moving to according to user's input
     * @param side true if moving right, false if moving left
     */
    private Pair<EntityModel.ElementLane,Integer> getDirection(boolean side){
        if(side) {
            switch (GameModel.getInstance().getHatch().getLane()) {
                case LEFT:
                    return new Pair<EntityModel.ElementLane, Integer>(EntityModel.ElementLane.MIDDLE,centerX);
                case MIDDLE:
                    return new Pair<EntityModel.ElementLane, Integer>(EntityModel.ElementLane.RIGHT,rightX);
                 default:
                     return null;
            }
        }else{
            switch (GameModel.getInstance().getHatch().getLane()) {
                case RIGHT:
                    return new Pair<EntityModel.ElementLane, Integer>(EntityModel.ElementLane.MIDDLE,centerX);
                case MIDDLE:
                    return new Pair<EntityModel.ElementLane, Integer>(EntityModel.ElementLane.LEFT,leftX);
                default:
                    return null;
            }
        }
    }


    /**
     * Moves hatch to a specific lane
     * @param side true if moving right, false if moving left
     */
    public void moveHatch(boolean side){

        Pair<EntityModel.ElementLane,Integer> p = getDirection(side);

        if (p != null) {
            if(canMove(p.getSecond())) {
                GameModel.getInstance().getHatch().setLane(p.getFirst());
                GameModel.getInstance().getHatch().setX(p.getSecond());
            } else Gdx.input.vibrate(500);
        }
    }


    /**
     * Checks if hatch can move to a certain position
     * @param x position
     * @return true if can move, false if else
     */
    private boolean canMove(float x){
        ObstacleModel o;

        if(GameModel.getInstance().getObstacles().size()>0) {
            o = GameModel.getInstance().getObstacles().get(0);

            return !(isOverlapped( new HatchModel(GameModel.getInstance().getHatch().getLane(), x, GameModel.getInstance().getHatch().getY()),o));
        }
        return true;
    }



    public void treatGyroInput(float gyroInput) {
        if(gyroInput > 4) {
            moveHatch(true);
        }
        else if(gyroInput <= -3) {
            moveHatch(false);
        }
    }



}
