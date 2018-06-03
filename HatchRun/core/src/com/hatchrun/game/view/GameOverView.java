package com.hatchrun.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hatchrun.game.HatchRun;
import com.hatchrun.game.model.GameModel;

public class GameOverView extends ScreenAdapter {

    private TextButton exitButton;
    private Label scoreLabel;
    private Label youLost;
    private Label yourScore;
    private Stage stage;
    private HatchRun game;
    private Table table;
    private Table table2;
    private ScreenViewport viewport;
    private Image background;


    public GameOverView(HatchRun game) {
        this.game = game;
        viewport = new ScreenViewport();
        stage = new Stage(viewport);

        setUpExitButton();
        setUpLabels();
        setUpTable();
        Gdx.input.setInputProcessor(stage);
    }


    private BitmapFont createBmapFont(int size, String file) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(file));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont bmap = generator.generateFont(parameter);
        return bmap;
    }

    private TextButton.TextButtonStyle createStyle(BitmapFont bmap) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = bmap;

        return style;
    }

    private void setUpTable() {
        table = new Table();

        table.setFillParent(true);
        table.add(youLost);
        table.row();
        table.add(yourScore);
        table.row();
        table.add(scoreLabel);
        table.row();
        table.add(exitButton);
        table.padBottom(40);

        stage.addActor(table);

    }

    private void setUpLabels() {
        youLost = new Label("YOU LOST!", new Label.LabelStyle(createBmapFont(200, "fonts/knewave.ttf"), Color.BLACK));
        yourScore = new Label("Your score:", new Label.LabelStyle(createBmapFont(100, "fonts/knewave.ttf"), Color.BLACK));
        scoreLabel = new Label(String.format("%06d", GameModel.getInstance().getScore()), new Label.LabelStyle(createBmapFont(150, "fonts/knewave-outline.ttf"), Color.BLACK));
    }

    public void setUpExitButton() {
        exitButton = new TextButton("EXIT", createStyle(createBmapFont(150, "fonts/knewave.ttf")));
        exitButton.setColor(Color.BLACK);
        exitButton.align(Align.center);

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
    }

    @Override
    public void render(float delta) {
        game.getBatch().begin();
        Texture t = game.getAssetManager().get("gameovermenu.jpg", Texture.class);
        game.getBatch().draw(t, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.getBatch().end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
