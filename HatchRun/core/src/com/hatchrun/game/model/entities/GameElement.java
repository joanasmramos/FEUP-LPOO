package com.hatchrun.game.model.entities;

/**
 * A model representing a game element which has a certain lane
 */
public class GameElement {
    public enum ElementLane {LEFT, CENTER, RIGHT};

    private ElementLane lane;

    /**
     * Constructs a game element
     * @param lane The current lane of the element
     */
    GameElement(ElementLane lane) {
        this.lane = lane;
    }

    /**
     * Returns the element's lane
     * @return Element's lane
     */
    public ElementLane getLane() {
        return lane;
    }

    /**
     * Sets the element's lane to the given lane
     * @param lane Given lane
     */
    public void setLane(ElementLane lane) {
        this.lane = lane;
    }
}
