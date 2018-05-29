package com.hatchrun.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hatchrun.game.HatchRun;


public class MainMenuView extends ScreenAdapter {
    HatchRun game;
    ButtonFactory buttonFactory = new ButtonFactory();
    TextButton playButton;
    TextButton exitButton;

    private Stage stage;


    public MainMenuView(HatchRun game){
        this.game = game;
        loadAssets();
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        playButton = buttonFactory.getButton("Play",150, this.stage.getWidth()/2, this.stage.getHeight()/4);
        exitButton = buttonFactory.getButton("Exit",150, this.stage.getWidth()/2, this.stage.getHeight()/4 - playButton.getHeight() );

        addButtonsListeners();
        this.stage.addActor(playButton);
        this.stage.addActor(exitButton);
    }

    private void loadAssets() {

        this.game.getAssetManager().load("mainmenu.png", Texture.class);
        this.game.getAssetManager().finishLoading();
    }

    @Override
    public void dispose(){
        buttonFactory.dispose();
    }

    @Override
    public void render(float delta) {

        game.getBatch().begin();

        Texture t = game.getAssetManager().get("mainmenu.png", Texture.class);
        game.getBatch().draw(t, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.getBatch().end();
        this.stage.act();
        this.stage.draw();
    }


    public void addButtonsListeners(){
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameView(game));
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(1);
            }
        });
    }

}
