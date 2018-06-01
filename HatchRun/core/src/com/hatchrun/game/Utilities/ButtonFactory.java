package com.hatchrun.game.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

public class ButtonFactory {

    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/knewave-outline.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private BitmapFont buttonFont;
    private TextButton button;

    public ButtonFactory(){

    }

    public TextButton getButton(String label, int size, float posX, float posY) {
        parameter.size = size;
        buttonFont = generator.generateFont(parameter);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = buttonFont;
        button = new TextButton(label, style);
        button.setPosition(posX,posY, Align.center);
        return button;
    }

    public void dispose(){
        generator.dispose();
    }

}
