package com.hatchrun.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hatchrun.game.Utilities.ImageButtonFactory;
import com.hatchrun.game.model.GameModel;

import java.text.DecimalFormat;

/**
 * A stage representing the HUD
 */
public class HUDview extends Stage {
    private Label scoreLabel;
    private Table rightTable;
    private Table leftTable;
    private ImageButtonFactory btnFactory;
    private ScreenViewport viewport;
    private BitmapFont bmap;
    private ImageButton pauseButton;
    private ImageButton playButton;
    private ImageButton soundButton;
    private boolean shielded;
    private boolean doubleCoins;
    private boolean pause;
    private int score;

    /**
     * Constructs a new HUD, with the given sprite batch, initializes all components
     * @param sb Sprite Batch
     */
    HUDview(SpriteBatch sb){
        super(new ScreenViewport(), sb);

        viewport = new ScreenViewport(new OrthographicCamera());

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/knewave.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        bmap = generator.generateFont(parameter);

        btnFactory = new ImageButtonFactory();

        setUpRightTable();
        setUpLeftTable();
    }

    /**
     * Sets up the right table, which has the score
     */
    public void setUpRightTable(){
        score = GameModel.getInstance().getScore();
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(bmap, Color.WHITE));

        rightTable = new Table();
        rightTable.top();
        rightTable.setFillParent(true);
        rightTable.add(scoreLabel);
        rightTable.align(Align.topRight);
        rightTable.padRight(30);

        addActor(rightTable);
    }

    /**
     * Sets up the left table, which has pause and sound buttons
     */
    public void setUpLeftTable(){
        pauseButton = btnFactory.getButton(Gdx.files.internal("pause_button.png"));
        soundButton = btnFactory.getButton(Gdx.files.internal("sound_button.png"));

        leftTable = new Table();
        leftTable.top();
        leftTable.setFillParent(true);
        leftTable.align(Align.topLeft);
        leftTable.padTop(20);
        leftTable.padLeft(40);
        leftTable.add(pauseButton);
        leftTable.add(soundButton).padLeft(10);

        addActor(leftTable);
    }

    /**
     * Updates
     * @param delta
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        score = GameModel.getInstance().getScore();
        DecimalFormat ft = new DecimalFormat("000000");
        scoreLabel.setText(""+ft.format(score));
    }
}
