package com.hatchrun.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.hatchrun.game.HatchRun;

public class HatchView extends EntityView {

    public HatchView(HatchRun game) {
        super(game);
    }

    @Override
    public Sprite createSprite(HatchRun game) {
        Texture texture = new Texture("bluehatch.png");
        Sprite s = new Sprite(texture);
        s.setCenter(Gdx.graphics.getWidth()/2,texture.getHeight()/2+100);

        return s;
    }
}
