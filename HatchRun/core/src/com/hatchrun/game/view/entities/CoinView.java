package com.hatchrun.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatchrun.game.HatchRun;
import com.hatchrun.game.model.entities.CoinModel;

public class CoinView extends  EntityView{

    private CoinModel model;

    public CoinView(HatchRun game, CoinModel model){
        this.model = model;
        super.sprite = createSprite(game);
    }

    @Override
    public Sprite createSprite(HatchRun game) {
        Texture texture = game.getAssetManager().get("coin.png", Texture.class);
        return new Sprite(texture);
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        sprite.setX(x);
        sprite.setY(y);
        sprite.draw(batch);
    }
}
