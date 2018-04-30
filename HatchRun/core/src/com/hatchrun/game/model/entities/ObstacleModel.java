package com.hatchrun.game.model.entities;

/**
 * A model representing a game object
 */
public class ObstacleModel extends EntitieModel {

    /**
     * Constructs a game element in the given position
     *
     * @param lane The current lane of the element
     * @param x X coordinate
     * @param y Y coordinate
     */
    ObstacleModel(ElementLane lane, float x, float y) {
        super(lane, x, y);
    }
}
