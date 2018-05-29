package com.hatchrun.game.view;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.hatchrun.game.controller.GameController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.hatchrun.game.HatchRun;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.CoinModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.ObstacleModel;
import com.hatchrun.game.view.entities.CoinView;
import com.hatchrun.game.view.entities.EntityView;
import com.hatchrun.game.view.entities.HatchView;
import com.hatchrun.game.view.entities.ObstacleView;

import java.util.ArrayList;

public class GameView extends ScreenAdapter

{
    private final HatchRun game;
    private final OrthographicCamera camera;
    private final BackgroundView background = new BackgroundView();
    private final HatchView hatchView;
    private static final float VIEWPORT_WIDTH = 1080;
    private static final float VIEWPORT_HEIGHT = 1812;

    public GameView(HatchRun game) {
        this.game = game;
        camera = createCamara();
        loadAssets();
        new GameModel(GameController.centerX,GameController.startY );
        hatchView = new HatchView(game,GameModel.getInstance().getHatch());
        Gdx.input.setInputProcessor(new GestureDetector(new GameInputProcessor()));
    }

    public OrthographicCamera createCamara() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();


        return camera;
    }

    @Override
    public void dispose(){

    }

    @Override
    public void render(float delta) {
        GameController.getInstance().update(delta);
        camera.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        background.updateAndRender(delta, game.getBatch());
        drawEntities();
        game.getBatch().end();
    }

    private void loadAssets() {
        this.game.getAssetManager().load("floor.png", Texture.class);
        this.game.getAssetManager().load("coin.png", Texture.class);
        this.game.getAssetManager().load("pinkbush.png", Texture.class);
        this.game.getAssetManager().load("bluebush.png", Texture.class);
        this.game.getAssetManager().load("yellowbush.png", Texture.class);
        this.game.getAssetManager().finishLoading();
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

        hatchView.draw(game.getBatch(), GameModel.getInstance().getHatch().getX(), GameModel.getInstance().getHatch().getY());

    }


}

