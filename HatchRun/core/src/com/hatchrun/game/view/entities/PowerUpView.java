package com.hatchrun.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatchrun.game.HatchRun;
import com.hatchrun.game.model.entities.CoinModel;
import com.hatchrun.game.model.entities.PowerUpModel;

/**
 * Power Up View
 */
public class PowerUpView extends  EntityView{
    private PowerUpModel model;

    /**
     * Constructs power up view
     * @param game Game
     * @param model Obstacle's model
     */
    public PowerUpView(HatchRun game, PowerUpModel model){
        this.model = model;
        super.sprite = createSprite(game);
    }

    /**
     * Creates sprite
     * @param game Game
     * @return Sprite created
     */
    @Override
    public Sprite createSprite(HatchRun game) {
        Texture texture = null;

        switch (model.getType()){
            case DOUBLECOINS:
                texture = game.getAssetManager().get("doublecoins.png", Texture.class);
                break;
            case SHIELD:
                texture = game.getAssetManager().get("shield.png",Texture.class);
                break;
            default:
                break;
        }

        return new Sprite(texture);
    }

    /**
     * Draws obstacle
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

