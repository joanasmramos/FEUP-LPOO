package com.hatchrun.game.view;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.hatchrun.game.controller.GameController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.hatchrun.game.HatchRun;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.CoinModel;
import com.hatchrun.game.model.entities.ObstacleModel;
import com.hatchrun.game.model.entities.PowerUpModel;
import com.hatchrun.game.view.entities.BackgroundView;
import com.hatchrun.game.view.entities.CoinView;
import com.hatchrun.game.view.entities.EntityView;
import com.hatchrun.game.view.entities.HatchView;
import com.hatchrun.game.view.entities.ObstacleView;
import com.hatchrun.game.view.entities.PowerUpView;

import java.util.ArrayList;

public class GameView extends ScreenAdapter

{
    private final HatchRun game;
    private final BackgroundView background = new BackgroundView();
    private final HatchView hatchView;
    private int gyroscopeCtr;

    public GameView(HatchRun game) {
        this.game = game;
        new GameModel();
        hatchView = new HatchView(game,GameModel.getInstance().getHatch(),false);
        setInputProcessor();
        gyroscopeCtr = 0;
    }


    @Override
    public void render(float delta) {
        //having trouble in getting the gyroscope right

        if(gyroscopeCtr == 15) {
            GameController.getInstance().treatGyroInput(Gdx.input.getGyroscopeY());
            gyroscopeCtr = 0;
        }
        else {
            gyroscopeCtr++;
        }

        GameController.getInstance().update(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        background.updateAndRender(delta, game.getBatch());
        drawEntities();
        game.getBatch().end();
    }



    private void drawEntities() {

       ArrayList<ObstacleModel> obstacles = GameModel.getInstance().getObstacles();

        for (int i = 0; i < obstacles.size(); i++){
            EntityView entity = new ObstacleView(game, obstacles.get(i));
            entity.draw(game.getBatch(), obstacles.get(i).getX(), obstacles.get(i).getY());
        }

        ArrayList<CoinModel> coins = GameModel.getInstance().getCoins();


        for (int i = 0; i < coins.size(); i++){
            EntityView entity = new CoinView(game, coins.get(i));
            entity.draw(game.getBatch(), coins.get(i).getX(), coins.get(i).getY());
        }


        ArrayList<PowerUpModel> powerUpModels = GameModel.getInstance().getPowerUps();
        for (int i = 0; i < powerUpModels.size(); i++){
            EntityView entity = new PowerUpView(game, powerUpModels.get(i));
            entity.draw(game.getBatch(), powerUpModels.get(i).getX(), powerUpModels.get(i).getY());
        }


        hatchView.draw(game.getBatch(), GameModel.getInstance().getHatch().getX(), GameModel.getInstance().getHatch().getY());

    }


    public void setInputProcessor(){


        Gdx.input.setInputProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
            @Override
            public boolean fling(float velocityX, float velocityY, int button) {
                if (velocityX > 0) {
                    GameController.getInstance().moveHatch(true);

                } else if (velocityX < 0)
                    GameController.getInstance().moveHatch(false);
                return true;
            }

        }));
    }



}

