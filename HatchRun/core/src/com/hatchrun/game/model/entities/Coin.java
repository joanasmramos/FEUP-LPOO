package com.hatchrun.game.model.entities;

/**
 * A model representing a game coin
 */
public class Coin  extends GameElement {

    int pointsWorth; // necessario?

    /**
     * Constructs a coin in the given position
     *
     * @param lane The current lane of the coin
     * @param x X coordinate
     * @param y Y coordinate
     */
    Coin(ElementLane lane, float x, float y) {
        super(lane, x, y);
    }

    /**
     * Constructs a coin in the given position, worth a
     * certain amount of points
     * @param lane Current lane of the coin
     * @param points Points that the coin's worth score wise
     * @param x X coordinate
     * @param y Y coordinate
     */
    Coin(ElementLane lane, float x, float y, int points) {
        super(lane, x, y);
        pointsWorth = points;
    }

    /**
     * Returns the points that the coin's worth
     * @return Points
     */
    public int getPointsWorth() {
        return pointsWorth;
    }

    /**
     * Sets the points that the coin's worth
     * @param pointsWorth Points
     */
    public void setPointsWorth(int pointsWorth) {
        this.pointsWorth = pointsWorth;
    }

}
