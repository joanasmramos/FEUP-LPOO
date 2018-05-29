package com.hatchrun.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatchrun.game.HatchRun;
import com.hatchrun.game.model.entities.ObstacleModel;

public class ObstacleView extends EntityView{
    ObstacleModel model;

    public ObstacleView(HatchRun game, ObstacleModel model){

        this.model = model;
        super.sprite = createSprite(game);

    }

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

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        sprite.setX(x);
        sprite.setY(y);
        sprite.draw(batch);
    }
}
