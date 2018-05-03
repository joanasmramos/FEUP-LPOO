package com.hatchrun.game.model.entities;

/**
 * A model representing a hatch (the only game element user-controlled)
 */

public class HatchModel extends EntitieModel {
    private enum HatchType {BLUE, GREEN, PURPLE, YELLOW};

    private int coins;
    private int score;
    private boolean shielded;
    private HatchType currentHatch;

    /**
     * Constructs a hatch (default = purple) in the given position
     * with no coins, zero score, not shielded
     * @param lane The current lane of the hatch
     * @param x X coordinate
     * @param y Y coordinate
     */
    public HatchModel(ElementLane lane, float x, float y) {
        super(lane, x, y);
        coins = 0;
        score = 0;
        shielded = false;
        currentHatch = HatchType.PURPLE;
    }

    /**
     * Constructs (in the given position) the default hatch:
     * purple, with no coins, zero score, not shielded
     * @param x X coordinate
     * @param y Y coordinate
     */
    HatchModel(float x, float y) {
        super(ElementLane.MIDDLE, x, y);
        coins = 0;
        score = 0;
        shielded = false;
        currentHatch = HatchType.PURPLE;
    }

    /**
     * Returns hatch's coins
     * @return Hatch's coins
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Sets hatch's coins
     * @param coins Hatch's coins
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * Returns hatch's score
     * @return Score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets hatch's score
     * @param score Score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns true if the hatch is shielded, false otherwise
     * @return shielded
     */
    public boolean isShielded() {
        return shielded;
    }

    /**
     * Shields/unshields the hatch
     * @param shielded True if shielded, false otherwise
     */
    public void setShielded(boolean shielded) {
        this.shielded = shielded;
    }

    /**
     * Returns the hatch's colour
     * @return hatch's colour
     */
    public HatchType getCurrentHatch() {
        return currentHatch;
    }

    /**
     * Sets the hatch's colour
     * @param currentHatch Colour
     */
    public void setCurrentHatch(HatchType currentHatch) {
        this.currentHatch = currentHatch;
    }

}

