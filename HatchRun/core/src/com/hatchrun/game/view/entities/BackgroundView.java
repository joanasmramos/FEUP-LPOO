package com.hatchrun.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hatchrun.game.model.GameModel;

public class BackgroundView {

    private Texture image;
    private float y1, y2, y3;
    private float imageScale;

    public BackgroundView () {
        image = new Texture("floor.png");

        y1 = 0;
        y2 = image.getHeight();
        y3 = image.getHeight()*2;
        imageScale = Gdx.graphics.getHeight() / image.getHeight();
    }

    public void updateAndRender (float deltaTime, SpriteBatch batch) {

        y1 -= GameModel.getInstance().speed * deltaTime;
        y2 -= GameModel.getInstance().speed * deltaTime;
        y3 -= GameModel.getInstance().speed * deltaTime;

        //if image reached the bottom of screen and is not visible, put it back on top
        if (y1 + image.getHeight() * imageScale <= 0)
            y1 = y3 + image.getHeight() * imageScale;

        if (y2 + image.getHeight() * imageScale <= 0)
            y2 = y1 + image.getHeight() * imageScale;

        if(y3 + image.getHeight() * imageScale <= 0)
            y3 = y2 + image.getHeight() * imageScale;

        //Render

        batch.draw(image, 0, y1,Gdx.graphics.getWidth() , image.getHeight() * imageScale);
        batch.draw(image, 0, y2, Gdx.graphics.getWidth(), image.getHeight() * imageScale);
        batch.draw(image, 0, y3, Gdx.graphics.getWidth(), image.getHeight() * imageScale);
    }



}

