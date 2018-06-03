package com.hatchrun.game.view.entities;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatchrun.game.HatchRun;

/**
 * Entity view
 */
public abstract class EntityView {

    Sprite sprite;
    protected HatchRun game;

    /**
     * Creates an entity view
     * @param game
     */
    EntityView(HatchRun game){
        sprite = createSprite(game);
        this.game = game;
    }

    EntityView(){
    }

    /**
     * Creates sprite
     * @param game Game
     * @return Sprite created
     */
    public abstract Sprite createSprite(HatchRun game);

    /**
     * Draws entity
     * @param batch Batch
     * @param x x coordinate
     * @param y y coordinate
     */
    public abstract void draw(SpriteBatch batch, float x, float y);

}
