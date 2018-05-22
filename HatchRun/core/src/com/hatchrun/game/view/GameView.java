package com.hatchrun.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.hatchrun.game.HatchRun;
import com.hatchrun.game.view.entities.BackgroundView;

import static com.hatchrun.game.controller.GameController.WORLD_HEIGHT;
import static com.hatchrun.game.controller.GameController.WORLD_WIDTH;

public class GameView extends ScreenAdapter {
    private final HatchRun game;
    private final OrthographicCamera camera;
    private final BackgroundView background = new BackgroundView();
    public final static float PIXEL_TO_METER = 0.04f;
    private static final float VIEWPORT_WIDTH = 30;

    public GameView(HatchRun game) {
        this.game = game;
        camera = createCamara();
        loadAssets();
    }

    public OrthographicCamera createCamara() {
        OrthographicCamera camera = new OrthographicCamera( Gdx.graphics.getHeight() ,Gdx.graphics.getWidth());

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        camera.rotate(90);


        return camera;
    }


    @Override
    public void render(float delta){

        //handle inputs
        //update controller
        //set camera
        camera.update();
        game.getBatch().begin();
        background.updateAndRender(delta ,game.getBatch());
        //draw entities
        game.getBatch().end();
        float i = Gdx.graphics.getWidth();

    }

    private void loadAssets(){
        this.game.getAssetManager().load("floor.png", Texture.class);
        this.game.getAssetManager().finishLoading();
    }


    private void drawEntities(){

    }

}

