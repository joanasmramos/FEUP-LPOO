package com.hatchrun.game.Utilities;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ImageButtonFactory {
    private Texture btnTexture;
    private TextureRegion btnTexReg;
    private TextureRegionDrawable drawable;

    public ImageButtonFactory() {
    }

    public ImageButton getButton(FileHandle file) {
        btnTexture = new Texture(file);
        btnTexReg = new TextureRegion(btnTexture);
        drawable = new TextureRegionDrawable(btnTexReg);

        return new ImageButton(drawable);
    }
}
