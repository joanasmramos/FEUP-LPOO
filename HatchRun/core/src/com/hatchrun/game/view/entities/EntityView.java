package com.hatchrun.game.view.entities;
import com.badlogic.gdx.Gdx;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatchrun.game.HatchRun;
import com.hatchrun.game.view.GameView;


public abstract class EntityView {

    protected Sprite sprite;

    public EntityView(HatchRun game){
        sprite = createSprite(game);
    }

    public EntityView(){
    }

    public abstract Sprite createSprite(HatchRun game);

    public abstract void draw(SpriteBatch batch, float x, float y);

}
