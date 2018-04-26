package com.hatchrun.game.model;

public class Coin  extends GameObject {

    int pointsWorth; // necessario?

    /**
     * Constructs a coin
     *
     * @param lane The current lane of the coin
     */
    Coin(ElementLane lane) {
        super(lane);
    }

    /**
     * Constructs a coin
     * @param lane Current lane of the coin
     * @param points Points that the coin's worth score wise
     */
    Coin(ElementLane lane, int points) {
        super(lane);
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
