package com.hatchrun.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hatchrun.game.model.GameModel;

import java.text.DecimalFormat;

public class HUDview extends Stage {
    private int score;
    private Label scoreLabel;
    private Table table;
    private ScreenViewport viewport;
    BitmapFont bmap;

    HUDview(SpriteBatch sb){
        super(new ScreenViewport(), sb);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/knewave.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 5;
        bmap = generator.generateFont(parameter);

        score = GameModel.getInstance().getScore();
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(bmap, Color.WHITE));
        scoreLabel.setFontScale(10f);
        scoreLabel.setAlignment(Align.right);

        table = new Table();
        table.top();
        table.setFillParent(true);
        table.add(scoreLabel).align(Align.top);

        addActor(table);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        score = GameModel.getInstance().getScore();
        DecimalFormat ft = new DecimalFormat("000000");
        scoreLabel.setText(""+ft.format(score));
    }
}
