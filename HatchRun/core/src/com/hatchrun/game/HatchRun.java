package com.hatchrun.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatchrun.game.view.GameView;

public class HatchRun extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();

		setScreen(new GameView(this));

	}

	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}


}
