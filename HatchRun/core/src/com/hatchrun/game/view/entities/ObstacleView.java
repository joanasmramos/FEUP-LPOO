package com.hatchrun.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatchrun.game.HatchRun;
import com.hatchrun.game.model.entities.ObstacleModel;

/**
 * Obstacle view
 */
public class ObstacleView extends EntityView{
    private ObstacleModel model;

    /**
     * Constructs an obstacle view
     * @param game
     * @param model
     */
    public ObstacleView(HatchRun game, ObstacleModel model){

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

        switch (model.getColour()){
            case PINK:
                texture = game.getAssetManager().get("pinkbush.png", Texture.class);
                break;
            case YELLOW:
                texture = game.getAssetManager().get("yellowbush.png",Texture.class);
                break;
            case BLUE:
                texture = game.getAssetManager().get("bluebush.png",Texture.class);
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
