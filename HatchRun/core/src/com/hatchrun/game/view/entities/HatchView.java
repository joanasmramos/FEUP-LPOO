package com.hatchrun.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.hatchrun.game.HatchRun;
import com.hatchrun.game.model.GameModel;
import com.hatchrun.game.model.entities.EntityModel;
import com.hatchrun.game.model.entities.HatchModel;

public class HatchView extends EntityView {

    private HatchModel model;
    private Texture texture;
    private TextureAtlas hatchAtlas;
    Sprite s;
    private Animation<TextureRegion> animation;
    float stateTime = 0;
    boolean still;


    public HatchView(HatchRun game, HatchModel model, boolean still) {
        super(game);
        hatchAtlas = new TextureAtlas(Gdx.files.internal("bluehatch.atlas"));
        animation = new Animation<TextureRegion>(1/3f, hatchAtlas.getRegions());
        this.still  = still;
    }

    @Override
    public Sprite createSprite(HatchRun game) {

        switch (GameModel.getInstance().getHatchOrder().get(GameModel.getHatchOrderIndex())){
            case BLUE:
                texture = new Texture("blue.png");
                break;
            case PURPLE:
                texture = new Texture("purple.png");
                break;
            case YELLOW:
                texture = new Texture("yellow.png");
        }

        s = new Sprite(texture);
        s.setCenter(Gdx.graphics.getWidth()/2,texture.getHeight()/2+100);

        return s;
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y) {
        stateTime += Gdx.graphics.getDeltaTime();
        s.setX(x);
        s.setY(y);
        if(still) s.draw(batch);
        else batch.draw(animation.getKeyFrame(stateTime, true), x, y);
    }


}
