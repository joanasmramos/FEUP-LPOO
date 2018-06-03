package com.hatchrun.game.view;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.hatchrun.game.controller.GameController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.Input.Peripheral;

import com.hatchrun.game.HatchRun;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.CoinModel;
import com.hatchrun.game.model.entities.HatchModel;
import com.hatchrun.game.model.entities.ObstacleModel;
import com.hatchrun.game.model.entities.PowerUpModel;
import com.hatchrun.game.view.entities.BackgroundView;
import com.hatchrun.game.view.entities.CoinView;
import com.hatchrun.game.view.entities.EntityView;
import com.hatchrun.game.view.entities.HatchView;
import com.hatchrun.game.view.entities.ObstacleView;
import com.hatchrun.game.view.entities.PowerUpView;
import com.hatchrun.game.view.HUDview;
import java.util.ArrayList;

/**
 * A class representing the game view, a screen adapter
 */
public class GameView extends ScreenAdapter

{
    private final HatchRun game;
    private final BackgroundView background = new BackgroundView();
    private final HatchView hatchView;
    private int gyroscopeCtr;
    private HUDview hud;
    private InputProcessor inputProcessor1;
    private Animation<TextureRegion> animation;
    private float stateTime  = 0;

    /**
     * Constructs the game view
     * @param game
     */
     GameView(HatchRun game) {
        this.game = game;
        new GameModel();
        GameModel.getInstance().setCatchCoin(Gdx.audio.newSound(Gdx.files.internal("soundEffects/catchcoin.mp3")));
        GameModel.getInstance().getHatch().setCurrentState(HatchModel.HatchState.RUNNING);
        hatchView = new HatchView(game,GameModel.getInstance().getHatch());
        setInputProcessor();
        gyroscopeCtr = 0;
        hud = new HUDview(game.getBatch());

        InputProcessor inputProcessor2 = hud;
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputProcessor1);
        inputMultiplexer.addProcessor(inputProcessor2);
        Gdx.input.setInputProcessor(inputMultiplexer);
        TextureAtlas hatchAtlas = new TextureAtlas(Gdx.files.internal("hatchgif.atlas"));
        animation = new Animation<TextureRegion>(1 /5f, hatchAtlas.getRegions());
    }


    /**
     * Updates view and model according to time and user inputs
     * @param delta Time since last update
     */
    @Override
    public void render(float delta) {

        game.getBatch().begin();

        if(GameModel.getInstance().isOver()){
            game.setScreen(new GameOverView(game));
        }

        if(hud.isPaused()){
            delta = 0;
            hatchView.setStill(true);
        }
        else {
            hatchView.setStill(false);
        }

        if(!hud.isPaused()) {
            GameModel.getInstance().getHatch().setCurrentState(HatchModel.HatchState.RUNNING);

            if (gyroscopeCtr == 15) {
                GameController.getInstance().treatGyroInput(Gdx.input.getGyroscopeY());
                gyroscopeCtr = 0;
            } else {
                gyroscopeCtr++;
            }
        }

        GameController.getInstance().update(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        hud.act(delta);

        background.updateAndRender(delta, game.getBatch());
        drawEntities();

  if(hud.isPaused()) {
        stateTime += Gdx.graphics.getDeltaTime();
            game.getBatch().draw(game.getAssetManager().get("whitedialog.png", Texture.class),
                    Gdx.graphics.getWidth() / 2 - 325, Gdx.graphics.getHeight() / 2 - 400);
            game.getBatch().draw(animation.getKeyFrame(stateTime, true), (int)(0.32*Gdx.graphics.getWidth()), (int)(Gdx.graphics.getHeight() *0.45));
        }

        game.getBatch().end();

        hud.draw();
    }

    /**
     * Draws entities
     */
    private void drawEntities() {

        ArrayList<CoinModel> coins = GameModel.getInstance().getCoins();


        for (int i = 0; i < coins.size(); i++){
            EntityView entity = new CoinView(game);
            entity.draw(game.getBatch(), coins.get(i).getX(), coins.get(i).getY());
        }


        ArrayList<PowerUpModel> powerUpModels = GameModel.getInstance().getPowerUps();
        for (int i = 0; i < powerUpModels.size(); i++){
            EntityView entity = new PowerUpView(game, powerUpModels.get(i));
            entity.draw(game.getBatch(), powerUpModels.get(i).getX(), powerUpModels.get(i).getY());
        }


        if(!GameModel.getInstance().getHatch().isShielded())
            hatchView.draw(game.getBatch(), GameModel.getInstance().getHatch().getX(), GameModel.getInstance().getHatch().getY());


        ArrayList<ObstacleModel> obstacles = GameModel.getInstance().getObstacles();

        for (int i = 0; i < obstacles.size(); i++){
            EntityView entity = new ObstacleView(game, obstacles.get(i));
            entity.draw(game.getBatch(), obstacles.get(i).getX(), obstacles.get(i).getY());
        }

        if(GameModel.getInstance().getHatch().isShielded())
            hatchView.draw(game.getBatch(), GameModel.getInstance().getHatch().getX(), GameModel.getInstance().getHatch().getY());
    }

    /**
     * Sets up input processor for the fling gesture (switch lane)
     */
    private void setInputProcessor(){

        inputProcessor1 = new GestureDetector(new GestureDetector.GestureAdapter() {
            @Override
            public boolean fling(float velocityX, float velocityY, int button) {

                if(!hud.isPaused()) {
                if (velocityX > 0) {
                    GameController.getInstance().moveHatch(true);

                } else if (velocityX < 0)
                    GameController.getInstance().moveHatch(false);
                return true;

            }
            return false;
            }

        });
    }



}

