package com.hatchrun.game.view.entities;
import com.badlogic.gdx.Gdx;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatchrun.game.HatchRun;
import com.hatchrun.game.view.GameView;


public abstract class EntityView {

    Sprite sprite;


    public EntityView(HatchRun game){
        sprite = createSprite(game);
    }

    public abstract Sprite createSprite(HatchRun game);

    public void draw(SpriteBatch batch) {
        int i;
        if(GameModel.getInstance().getHatch().getLane()== EntityModel.ElementLane.LEFT) i =0;
        else if(GameModel.getInstance().getHatch().getLane()== EntityModel.ElementLane.RIGHT) i =2;
        else i = 1;
        sprite.setX(GameView.startX+i*(9+GameView.playableWidth/3));
        sprite.draw(batch);
    }

    //TO DO: update
}
