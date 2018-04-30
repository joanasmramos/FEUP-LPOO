package com.hatchrun.game.model.entities;

/**
 * A model representing a power-up object
 */
public class PowerUpModel extends ObstacleModel {
    private enum PowerUpType {DOUBLECOINS, SHIELD, CHANGECOLOR};

    private PowerUpType type;

    /**
     * Constructs a power-up object in the given position
     *
     * @param lane The current lane of the power up
     * @param x X coordinate
     * @param y Y coordinate
     */
    PowerUpModel(ElementLane lane, float x, float y) {
        super(lane, x, y);
    }

    /**
     * Constructs a power-up object from a certain type in the given position
     *
     * @param lane The current lane of the power up
     * @param x X coordinate
     * @param y Y coordinate
     * @param type Power-up's type
     */
    PowerUpModel(ElementLane lane, float x, float y, PowerUpType type) {
        super(lane, x, y);
        this.type = type;
    }

    /**
     * Returns the power-up's type
     * @return Power-up's type
     */
    public PowerUpType getType() {
        return type;
    }

    /**
     * Sets the power-up's type
     * @param type Power-up's type
     */
    public void setType(PowerUpType type) {
        this.type = type;
    }
}
