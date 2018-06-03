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

/**
 * Game over view
 */
public class GameOverView extends ScreenAdapter {

    private TextButton exitButton;
    private TextButton playAgain;
    private Label scoreLabel;
    private Label youLost;
    private Label yourScore;
    private Stage stage;
    private HatchRun game;
    private Table table;
    private ScreenViewport viewport;

    /**
     * Constructs a new game over view
     * @param game Game
     */
     GameOverView(HatchRun game) {
        this.game = game;
        viewport = new ScreenViewport();
        stage = new Stage(viewport);

        setUpExitButton();
        setUpPlayButton();
        setUpLabels();
        setUpTable();
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Creates a BitmapFont given a ttf and a size
     * @param size Size
     * @param file .ttf file
     * @return BitmapFont created
     */
    private BitmapFont createBmapFont(int size, String file) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(file));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont bmap = generator.generateFont(parameter);
        return bmap;
    }

    /**
     * Creates TextButtonStyle given a BitmapFont
     * @param bmap BitmapFont
     * @return TextButtonStyle created
     */
    private TextButton.TextButtonStyle createStyle(BitmapFont bmap) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = bmap;

        return style;
    }

    /**
     * Sets up table with the labels and buttons for this view
     */
    private void setUpTable() {
        table = new Table();

        table.setFillParent(true);
        table.add(youLost);
        table.row();
        table.add(yourScore);
        table.row();
        table.add(scoreLabel);
        table.row();
        table.add(playAgain);
        table.row();
        table.add(exitButton);
        table.padBottom(40);

        stage.addActor(table);

    }

    /**
     * Sets up labels for this view
     */
    private void setUpLabels() {
        youLost = new Label("YOU LOST!", new Label.LabelStyle(createBmapFont(200, "fonts/knewave.ttf"), Color.BLACK));
        yourScore = new Label("Your score:", new Label.LabelStyle(createBmapFont(100, "fonts/knewave.ttf"), Color.BLACK));
        scoreLabel = new Label(String.format("%06d", GameModel.getInstance().getScore()), new Label.LabelStyle(createBmapFont(150, "fonts/knewave.ttf"), Color.BLACK));
    }

    /**
     * Sets up an Exit button and its listener
     */
    private void setUpExitButton() {
        exitButton = new TextButton("Exit", createStyle(createBmapFont(150, "fonts/knewave.ttf")));
        exitButton.align(Align.center);

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
    }

    /**
     * Sets up a Play Again button and its listener
     */
    private void setUpPlayButton(){
        playAgain = new TextButton("Play Again", createStyle(createBmapFont(150, "fonts/knewave.ttf")));
        playAgain.align(Align.center);

        playAgain.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameModel.getInstance().resetModel();
                game.setScreen(new ChooseHatchView(game));
            }
        });
    }

    /**
     * Updates view according to time
     * @param delta Time since last update
     */
    @Override
    public void render(float delta) {
        game.getBatch().begin();
        Texture t = game.getAssetManager().get("gameovermenu.jpg", Texture.class);
        game.getBatch().draw(t, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.getBatch().end();
        stage.act();
        stage.draw();
    }

    /**
     * Disposes
     */
    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }


}
