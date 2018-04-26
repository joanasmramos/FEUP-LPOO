package com.hatchrun.game.model;

/**
 * A model representing a game object
 */
public class GameObject extends GameElement {

    // TO DO: que atributos partilham TODOS os objetos??

    /**
     * Constructs a game object
     *
     * @param lane The current lane of the object
     */
    GameObject(ElementLane lane) {
        super(lane);
    }
}
