package com.hatchrun.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hatchrun.game.HatchRun;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.view.GameView;

public class HatchView extends EntityView {

    private TextureAtlas hatchAtlas;
    private Animation<TextureRegion> animation;
    float stateTime = 0;


    public HatchView(HatchRun game) {
        super(game);
        hatchAtlas = new TextureAtlas(Gdx.files.internal("bluehatch.atlas"));
        animation = new Animation<TextureRegion>(1/3f, hatchAtlas.getRegions());
    }

    @Override
    public Sprite createSprite(HatchRun game) {
        Texture texture = new Texture("bluehatch.png");
        Sprite s = new Sprite(texture);
        s.setCenter(Gdx.graphics.getWidth()/2,texture.getHeight()/2+100);

        return s;
    }

    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        batch.draw(animation.getKeyFrame(stateTime, true), GameModel.getInstance().getHatch().getX(), GameModel.getInstance().getHatch().getY());
    }

}
