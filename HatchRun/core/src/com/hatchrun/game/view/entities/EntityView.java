package com.hatchrun.game.view.entities;
import com.badlogic.gdx.Gdx;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatchrun.game.HatchRun;


public abstract class EntityView {

    Sprite sprite;

    public EntityView(HatchRun game){
        sprite = createSprite(game);
    }

    public abstract Sprite createSprite(HatchRun game);

    public void draw(SpriteBatch batch) {
        int i;
        if(GameModel.getInstance().getHatch().getLane()== EntityModel.ElementLane.LEFT) i =1;
        else if(GameModel.getInstance().getHatch().getLane()== EntityModel.ElementLane.RIGHT) i =3;
        else i = 2;
        sprite.setCenterX((1080-970)/2 + i*970/3 - sprite.getTexture().getWidth()/2);
        sprite.draw(batch);
    }

    //TO DO: update
}
