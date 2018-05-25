package com.hatchrun.game.view;
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
import com.hatchrun.game.view.entities.HatchView;

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
        hatchView = new HatchView(game);
        Gdx.input.setInputProcessor(new GestureDetector(new GameInputProcessor()));
        new GameModel(GameController.centerX,GameController.startY );
    }

    public OrthographicCamera createCamara() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();


        return camera;
    }


    @Override
    public void render(float delta) {
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
        this.game.getAssetManager().finishLoading();
    }


    private void drawEntities() {
        hatchView.draw(game.getBatch());
    }


}

