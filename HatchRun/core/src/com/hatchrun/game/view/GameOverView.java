package com.hatchrun.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hatchrun.game.HatchRun;
import com.hatchrun.game.model.GameModel;

public class GameOverView extends ScreenAdapter {

    private TextButton exitButton;
    private Label scoreLabel;
    private Label youLost;
    private Stage stage;
    private BitmapFont bmap;
    private HatchRun game;
    private Table table;
    private Table table2;
    private TextButton.TextButtonStyle style;
    private ScreenViewport viewport;
    private Image background;


    public GameOverView(HatchRun game) {
        createStyle();

        this.game = game;
        viewport = new ScreenViewport();
        stage = new Stage(viewport);

        setUpExitButton();
        setUpLabels();
        setUpTable();
        setUpTable2();
    }

    private void setUpTable2() {
        Texture t = game.getAssetManager().get("gameovermenu.jpg", Texture.class);
        background = new Image(t);

        table2 = new Table();
        table2.add(background);
        stage.addActor(table2);
        table2.setFillParent(true);
    }

    private void createStyle() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/knewave.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 150;
        bmap = generator.generateFont(parameter);
        style = new TextButton.TextButtonStyle();
        style.font = bmap;
    }

    private void setUpTable() {
        table = new Table();

        table.add(youLost);
        table.row();
        table.add(scoreLabel);
        table.row();
        table.add(exitButton);

        stage.addActor(table);

    }

    private void setUpLabels() {
        youLost = new Label("You lost! Your score: ", new Label.LabelStyle(bmap, Color.PINK));
        scoreLabel = new Label(String.format("%06d", GameModel.getInstance().getScore()), new Label.LabelStyle(bmap, Color.PINK));
    }

    public void setUpExitButton() {
        exitButton = new TextButton("Exit", style);

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
