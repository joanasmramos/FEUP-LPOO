package com.hatchrun.game.model.entities;

/**
 * A model representing a specific game object: go-under obstacle
 */
public class GoUnderObj extends GameElement {

    // atributos??

    /**
     * Constructs a game object: an object that will end the game
     * unless the user goes under it
     *
     * @param lane The current lane of the object
     */
    GoUnderObj(ElementLane lane) {
        super(lane);
    }
}
