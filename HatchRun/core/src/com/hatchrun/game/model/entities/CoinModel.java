package com.hatchrun.game.model.entities;

/**
 * A model representing a game coin
 */
public class CoinModel extends EntitieModel {


    /**
     * Constructs a coin in the given position
     *
     * @param lane The current lane of the coin
     * @param x X coordinate
     * @param y Y coordinate
     */
    CoinModel(ElementLane lane, float x, float y) {
        super(lane, x, y);
    }

}