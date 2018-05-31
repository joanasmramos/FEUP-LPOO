package com.hatchrun.game.model.entities;

/**
 * A model representing a hatch (the only game element user-controlled)
 */

public class HatchModel extends EntityModel {

    public enum HatchType {BLUE, GREEN, PURPLE, YELLOW};
    public enum HatchState {RUNNING, STILL};
    private boolean shielded;
    private HatchType currentHatch;
    private HatchState currentState;

    /**
     * Constructs a hatch (default = purple) in the given position
     * with no coins, zero score, not shielded
     * @param lane The current lane of the hatch
     * @param x X coordinate
     * @param y Y coordinate
     */
    public HatchModel(ElementLane lane, float x, float y) {
        super(lane, x, y, 200, 305);
        shielded = false;
        currentHatch = HatchType.BLUE;
        currentState = HatchState.STILL;
    }





    /**
     * Returns true if the hatch is shielded, false otherwise
     * @return shielded
     */
    public boolean isShielded() {
        return shielded;
    }

    /**
     * Shields/unshields the hatch
     * @param shielded True if shielded, false otherwise
     */
    public void setShielded(boolean shielded) {
        this.shielded = shielded;
    }

    /**
     * Returns the hatch's colour
     * @return hatch's colour
     */
    public HatchType getCurrentHatch() {
        return currentHatch;
    }

    /**
     * Sets the hatch's colour
     * @param currentHatch Colour
     */
    public void setCurrentHatch(HatchType currentHatch) {
        this.currentHatch = currentHatch;
    }

}

