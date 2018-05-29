package com.hatchrun.game.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.CoinModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.HatchModel;
import com.hatchrun.game.model.entities.ObstacleModel;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.ArrayList;
import java.util.Random;

public class GameController{
    private static GameController instance;
    private static final int playableWidth = (Gdx.graphics.getWidth()-(int)(2*Gdx.graphics.getWidth()*0.09));
    public static final int startX =(int)( Gdx.graphics.getWidth()*0.09);
    public static final int leftX = startX;
    public static final int centerX =(int)( startX+(9+playableWidth/3));
    public static final int rightX =(int)( startX+2*(9+playableWidth/3));
    public static final int widthLane = centerX - leftX;
    public static final int startY =  (int) (0.03*Gdx.graphics.getHeight());
    private long lastTimeRegisteredObstacles = 0;
    private long lastTimeRegisteredCoins = 0;
    Random rand;

    public GameController(){
        rand = new Random();
    }


    public static GameController getInstance() {
        if(instance == null) instance = new GameController();

        return instance;
    }

    public void update(float delta){

        if(System.currentTimeMillis() - lastTimeRegisteredCoins >= 9000){
            generateCoins();
            lastTimeRegisteredCoins = System.currentTimeMillis();
        }

        if(System.currentTimeMillis() - lastTimeRegisteredObstacles >= 5000){
            generateObstacle();
            lastTimeRegisteredObstacles = System.currentTimeMillis();
        }

        updateObjects(delta);
        updateSpeed(delta);
        disposeObjects();
        checkColission();
        catchCoins();
    }

    private void updateObjects(float delta){

        ArrayList<ObstacleModel> obstacles = GameModel.getInstance().getObstacles();

        for(ObstacleModel o :  obstacles){
            o.setY(o.getY()-GameModel.getInstance().speed*delta);
        }

        ArrayList<CoinModel> coins = GameModel.getInstance().getCoins();

        for(CoinModel c :  coins){
            c.setY(c.getY()-GameModel.getInstance().speed*delta);
        }
    }


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


    private int updateSpeed(float delta){
        if (GameModel.getInstance().speed < GameModel.getInstance().goalSpeed) {
            GameModel.getInstance().speed += GameModel.getInstance().GOAL_REACH_ACCELERATION * delta;
            if (GameModel.getInstance().speed > GameModel.getInstance().goalSpeed)
                GameModel.getInstance().speed = GameModel.getInstance().goalSpeed;
        } else if (GameModel.getInstance().speed > GameModel.getInstance().goalSpeed) {
            GameModel.getInstance().speed -= GameModel.getInstance().GOAL_REACH_ACCELERATION * delta;
            if (GameModel.getInstance().speed < GameModel.getInstance().goalSpeed)
                GameModel.getInstance().speed = GameModel.getInstance().goalSpeed;
        }

        if (!GameModel.getInstance().speedFixed)
            GameModel.getInstance().speed += GameModel.getInstance().ACCELERATION * delta;

        return GameModel.getInstance().speed;
    }

    private void generateObstacle() {
        ObstacleModel temp = null;

        switch (rand.nextInt(3)){
            case 0:
                temp = new ObstacleModel(EntityModel.ElementLane.LEFT,leftX,Gdx.graphics.getHeight(), generateColour());
                break;
            case 1:
                temp = new ObstacleModel(EntityModel.ElementLane.MIDDLE,centerX,Gdx.graphics.getHeight(), generateColour());
                break;
            case 2:
                temp = new ObstacleModel(EntityModel.ElementLane.RIGHT,rightX,Gdx.graphics.getHeight(), generateColour());
                break;
            default:
                break;
        }

        for(CoinModel coin: GameModel.getInstance().getCoins()){
            if(checkOverlap(temp,coin)) return;
        }

        GameModel.getInstance().getObstacles().add(temp);
    }

    private CoinModel generateCoin() {

        CoinModel coin = null;

        switch (rand.nextInt(3)){
            case 0:
                coin = new CoinModel(EntityModel.ElementLane.LEFT,leftX + 40,Gdx.graphics.getHeight());
                break;
            case 1:
                coin = new CoinModel(EntityModel.ElementLane.MIDDLE,centerX+40,Gdx.graphics.getHeight());
                break;
            case 2:
                coin = new CoinModel(EntityModel.ElementLane.RIGHT,rightX+40,Gdx.graphics.getHeight());
                break;
            default:
                break;
        }
        return coin;
    }

    private void generateCoins() {

        ArrayList<CoinModel> coinsToAdd = new ArrayList<CoinModel>();
        CoinModel temp = generateCoin();
        int j = 0;
        int i = rand.nextInt(5);
        int x, y;

        while(j<i){
            coinsToAdd.add(new CoinModel(temp.getLane(),temp.getX(), temp.getY()));
            temp.setY(temp.getY()+3*temp.getHeight()/2);
            j++;
        }

        for(CoinModel c: coinsToAdd){
            GameModel.getInstance().getCoins().add(c);
        }
    }

    private ObstacleModel.Colour generateColour(){
        switch (rand.nextInt(3)){
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


    public void checkColission(){


        for(ObstacleModel o : GameModel.getInstance().getObstacles()) {
           if(checkOverlap(o, GameModel.getInstance().getHatch()))
               System.exit(1);
        }
    }


    public boolean checkOverlap(EntityModel entity1, EntityModel entity2){
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
            if(entity2.getX() >= x0 && entity2.getX() <= x1)
                return true;
        return false;
    }

    public void moveHatch(boolean side){
        if(side) {
            switch (GameModel.getInstance().getHatch().getLane()) {
                case LEFT:
                    if(canMove(centerX)) {
                        GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.MIDDLE);
                        GameModel.getInstance().getHatch().setX(centerX);
                    }
                    break;
                case MIDDLE:
                    if(canMove(rightX)) {
                        GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.RIGHT);
                        GameModel.getInstance().getHatch().setX(rightX);
                    }
                    break;
                 default:
                     break;
            }
        }else{
            switch (GameModel.getInstance().getHatch().getLane()) {
                case RIGHT:
                    if(canMove(centerX)) {
                        GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.MIDDLE);
                        GameModel.getInstance().getHatch().setX(centerX);
                    }

                    break;
                case MIDDLE:
                    if(canMove(leftX)) {
                        GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.LEFT);
                        GameModel.getInstance().getHatch().setX(leftX);
                    }

                    break;
                default:
                    break;
            }
        }

    }

    public boolean canMove(float x){

        float y0;
        float x0;
        float y1;
        float x1;

        ArrayList<ObstacleModel> obstacles = GameModel.getInstance().getObstacles();

        for(ObstacleModel o : obstacles){
            y0 = o.getY();
            y1 = y0 + o.getHeight();
            x0 = o.getX();
            x1 = x0 + o.getWidth();

            if( y0 >= GameModel.getInstance().getHatch().getY() && y0 <=  GameModel.getInstance().getHatch().getY()+GameModel.getInstance().getHatch().getHeight()
                    || y1>= GameModel.getInstance().getHatch().getY() && y1 <= GameModel.getInstance().getHatch().getY()+GameModel.getInstance().getHatch().getHeight())
                if(x >= x0 && x <= x1)
                    return false;
        }
        return true;
    }


    public void catchCoins(){
        CoinModel tempCoin;

        ArrayList<CoinModel> coinsToRemove= new ArrayList<CoinModel>();

        for(CoinModel coin : GameModel.getInstance().getCoins()){
            tempCoin = new CoinModel(coin.getLane(), coin.getX(), coin.getY()+coin.getHeight());
            if(checkOverlap(GameModel.getInstance().getHatch(), tempCoin))
                coinsToRemove.add(coin);
        }


        for(CoinModel coin : coinsToRemove){
                GameModel.getInstance().getCoins().remove(coin);
        }
    }



}
