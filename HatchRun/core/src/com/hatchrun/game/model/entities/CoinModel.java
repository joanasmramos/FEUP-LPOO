package com.hatchrun.game.model.entities;

/**
 * A model representing a game coin
 */
public class CoinModel extends EntityModel {


    /**
     * Constructs a coin in the given position
     *
     * @param lane The current lane of the coin
     * @param x X coordinate
     * @param y Y coordinate
     */
    public CoinModel(ElementLane lane, float x, float y) {
        super(lane, x, y);
    }

}
