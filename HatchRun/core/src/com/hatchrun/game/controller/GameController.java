package com.hatchrun.game.controller;

import com.badlogic.gdx.Gdx;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.HatchModel;
import com.hatchrun.game.model.entities.ObstacleModel;
import com.hatchrun.game.view.GameView;

import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private static GameController instance;
    private ArrayList<ObstacleModel> obstaclesToAdd = new ArrayList<ObstacleModel>();
    private static final int playableWidth = (Gdx.graphics.getWidth()-(int)(2*Gdx.graphics.getWidth()*0.09));
    public static final int startX =(int)( Gdx.graphics.getWidth()*0.09);
    public static final int leftX = startX;
    public static final int centerX =(int)( startX+(9+playableWidth/3));
    public static final int rightX =(int)( startX+2*(9+playableWidth/3));
    public static final int startY =  (int) (0.03*Gdx.graphics.getHeight());
    private long lastTimeRegistered = 0;
    Random rand;

    public GameController(){
        rand = new Random();
    }


    public static GameController getInstance() {
        if(instance == null) instance = new GameController();

        return instance;
    }

    public void update(float delta){
        if(System.currentTimeMillis() - lastTimeRegistered >= 5000){
            generateObstacle();
            lastTimeRegistered = System.currentTimeMillis();
        }
        updateObjects(delta);
        updateSpeed(delta);
        disposeObjects();
        checkColissionBubble();
    }

    private void updateObjects(float delta){

        ArrayList<ObstacleModel> obstacles = GameModel.getInstance().getObstacles();

        for(ObstacleModel o :  obstacles){
            o.setY(o.getY()-GameModel.getInstance().speed*delta);
        }
    }


    private void disposeObjects(){

        ArrayList<ObstacleModel> obstacles = GameModel.getInstance().getObstacles();
        ArrayList<ObstacleModel> obstaclesToRemove = new ArrayList<ObstacleModel>();


        for(ObstacleModel o :  obstacles){
             if(o.getY() < -266)
                 obstaclesToRemove.add(o);
        }

        for(ObstacleModel o: obstaclesToRemove){
            obstacles.remove(o);
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
        switch (rand.nextInt(3)){
            case 0:
                GameModel.getInstance().addObstacle(new ObstacleModel(EntityModel.ElementLane.LEFT,leftX,Gdx.graphics.getHeight(), generateColour()));
                break;
            case 1:
                GameModel.getInstance().addObstacle(new ObstacleModel(EntityModel.ElementLane.MIDDLE,centerX,Gdx.graphics.getHeight(), generateColour()));
                break;
            case 2:
                GameModel.getInstance().addObstacle(new ObstacleModel(EntityModel.ElementLane.RIGHT,rightX,Gdx.graphics.getHeight(), generateColour()));
                break;
            default:
                break;
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

    public void checkColissionBubble(){

        float y0;
        float x0;
        float x1;

        for(ObstacleModel o : GameModel.getInstance().getObstacles()) {
            y0 = o.getY();
            x0 = o.getX();
            x1 = x0 + 280 / 2;


            if (GameModel.getInstance().getHatch().getX() <= x1 && GameModel.getInstance().getHatch().getX() >= x0)
                if (GameModel.getInstance().getHatch().getY() + 295 >= y0)
                    System.exit(1);
        }
    }

    public void moveHatch(boolean side){
        //if side = 1 then move right
        //else move left
        if(side) {
            switch (GameModel.getInstance().getHatch().getLane()) {
                case LEFT:
                    if(canMove(centerX, true)) {
                        GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.MIDDLE);
                        GameModel.getInstance().getHatch().setX(centerX);
                    }
                    break;
                case MIDDLE:
                    if(canMove(rightX, true)) {
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
                    if(canMove(centerX, false)) {
                        GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.MIDDLE);
                        GameModel.getInstance().getHatch().setX(centerX);
                    }

                    break;
                case MIDDLE:
                    if(canMove(leftX, false)) {
                        GameModel.getInstance().getHatch().setLane(EntityModel.ElementLane.LEFT);
                        GameModel.getInstance().getHatch().setX(leftX);
                    }

                    break;
                default:
                    break;
            }
        }

    }

    public boolean canMove(float x, boolean side){

        float y0;
        float x0;
        float y1;
        float x1;

        ArrayList<ObstacleModel> obstacles = GameModel.getInstance().getObstacles();

        for(ObstacleModel o : obstacles){
            y0 = o.getY();
            y1 = y0 + 260;
            x0 = o.getX();
            x1 = x0 + 280 / 2;

            if( y0 >= GameModel.getInstance().getHatch().getY() && y0 <=  GameModel.getInstance().getHatch().getY()+305
                    || y1>= GameModel.getInstance().getHatch().getY() && y1 <= GameModel.getInstance().getHatch().getY()+305)
                if(side){
                    if(x >= x0)
                        return false;
                }else{
                    if(x <= x1)
                        return false;
                }
        }
        return true;
    }

}
