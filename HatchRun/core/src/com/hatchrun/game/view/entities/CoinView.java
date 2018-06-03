package com.hatchrun.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatchrun.game.HatchRun;
import com.hatchrun.game.model.entities.CoinModel;

/**
 * Coin view
 */
public class CoinView extends  EntityView{

    /**
     * Constructs a coin view
     * @param game Game
     */
    public CoinView(HatchRun game){
        super.sprite = createSprite(game);
    }

    /**
     * Creates a sprite
     * @param game Game
     * @return Sprite created
     */
    @Override
    public Sprite createSprite(HatchRun game) {
        Texture texture = game.getAssetManager().get("coin.png", Texture.class);
        return new Sprite(texture);
    }

    /**
     * Draws coin
     * @param batch Batch
     * @param x x coordinate
     * @param y y coordinate
     */
    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        sprite.setX(x);
        sprite.setY(y);
        sprite.draw(batch);
    }
}
