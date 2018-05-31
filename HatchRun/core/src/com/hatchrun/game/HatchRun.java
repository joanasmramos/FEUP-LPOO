package com.hatchrun.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatchrun.game.view.MainMenuView;

public class HatchRun extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;


	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		loadAssets();
		startGame();

	}

    private void startGame() {

		setScreen(new MainMenuView(this));
    }

	private void loadAssets() {

		assetManager.load("mainmenu.png", Texture.class);
		assetManager.load("floor.png", Texture.class);
		assetManager.load("coin.png", Texture.class);
		assetManager.load("pinkbush.png", Texture.class);
		assetManager.load("bluebush.png", Texture.class);
		assetManager.load("yellowbush.png", Texture.class);
		assetManager.load("doublecoins.png", Texture.class);
		assetManager.load("shield.png", Texture.class);
        assetManager.load("blue.png", Texture.class);
        assetManager.load("purple.png", Texture.class);
        assetManager.load("yellow.png", Texture.class);
        assetManager.load("choosemenu.png", Texture.class);
        assetManager.finishLoading();
	}


    public SpriteBatch getBatch() {
        return batch;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}


}
