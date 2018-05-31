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
            GameModel.getInstance().setScore(GameModel.getInstance().getScore()+1);
            lastSecondRegistered = System.currentTimeMillis();
        }

        updateObjects(delta);
        disposeObjects();
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
           if(checkOverlap( GameModel.getInstance().getHatch(),obs))
               System.exit(1);
           return true;
        }
        return false;
    }


    /**
     * Checks if two entities colide
     * @param entity1
     * @param entity2
     * @return true if colided, false if else
     */
    public static boolean checkOverlap(EntityModel entity1, EntityModel entity2){
        float y0;
        float x0;
        float x1;
        float y1;

        y0 = entity1.getY();
        y1 = y0 + entity1.getHeight();
        x0 = entity1.getX();
        x1 = x0 + entity1.getWidth();

        if( y0 >= entity2.getY() && y0 <=  entity2.getY()+entity2.getHeight()
                || y1>= entity2.getY() && y1 <= entity2.getY()+entity2.getHeight())
            return(entity2.getX() >= x0 && entity2.getX() <= x1);
        return false;
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

        if(canMove(p.getSecond())) {
            GameModel.getInstance().getHatch().setLane(p.getFirst());
            GameModel.getInstance().getHatch().setX(p.getSecond());
        } else Gdx.input.vibrate(500);
    }


    /**
     * Checks if hatch can move to a certain position
     * @param x position
     * @return true if can move, false if else
     */
    private boolean canMove(float x){


        ArrayList<ObstacleModel> obstacles = GameModel.getInstance().getObstacles();

        for(ObstacleModel o : obstacles){

            return !(checkOverlap(o,new HatchModel(GameModel.getInstance().getHatch().getLane(),x,GameModel.getInstance().getHatch().getY())));

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
