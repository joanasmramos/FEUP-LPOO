package com.hatchrun.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.hatchrun.game.HatchRun;

public class MainMenuView extends ScreenAdapter {
    HatchRun game;

    public MainMenuView(HatchRun game){
        this.game = game;
        loadAssets();
    }

    private void loadAssets() {
        this.game.getAssetManager().load("mainmenu.png", Texture.class);
        this.game.getAssetManager().finishLoading();
    }

    @Override
    public void render(float delta) {
        game.getBatch().begin();

        Texture t = game.getAssetManager().get("mainmenu.png", Texture.class);
            game.getBatch().draw(t, 0, 0);
        game.getBatch().end();

    }
}
