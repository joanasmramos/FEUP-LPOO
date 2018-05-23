package com.hatchrun.game.view;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.hatchrun.game.controller.GameController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    public final static float PIXEL_TO_METER = 0.04f;
    private static final float VIEWPORT_WIDTH = 30;

    public GameView(HatchRun game) {
        this.game = game;
        camera = createCamara();
        loadAssets();
        hatchView = new HatchView(game);
        Gdx.input.setInputProcessor(new GestureDetector(new GameInputProcessor()));
    }

    public OrthographicCamera createCamara() {
        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        camera.rotate(90);


        return camera;
    }


    @Override
    public void render(float delta) {

        //handle inputs
        //update controller
        //set camera
        camera.update();
        game.getBatch().begin();
        background.updateAndRender(delta, game.getBatch());
        drawEntities();
        //handleInputs(delta);
        game.getBatch().end();

    }

    private void loadAssets() {
        this.game.getAssetManager().load("floor.png", Texture.class);
        this.game.getAssetManager().finishLoading();
    }


    private void drawEntities() {
        hatchView.draw(game.getBatch());
    }

    private static final int SWIPE_THRESHOLD = 40;

    private long lastTapTime = System.currentTimeMillis();

    private void handleInputs(float delta) {
    }

}