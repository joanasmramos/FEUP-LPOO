package com.hatchrun.game.model.entities;

/**
 * A model representing a specific game object: go-under obstacle
 */
public class GoUnderObj extends GameElement {

    // atributos??

    /**
     * Constructs a specific type of game object in the given position: an
     * object that will end the game unless the user goes under it
     *
     * @param lane The current lane of the obstacle
     * @param x X coordinate
     * @param y Y coordinate
     */
    GoUnderObj(ElementLane lane, float x, float y) {
        super(lane, x, y);
    }
}
