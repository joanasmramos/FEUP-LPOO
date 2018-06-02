package com.hatchrun.game.view.entities;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatchrun.game.HatchRun;


public abstract class EntityView {

    Sprite sprite;
    protected HatchRun game;

    EntityView(HatchRun game){
        sprite = createSprite(game);
        this.game = game;
    }

    EntityView(){
    }

    public abstract Sprite createSprite(HatchRun game);

    public abstract void draw(SpriteBatch batch, float x, float y);

}
