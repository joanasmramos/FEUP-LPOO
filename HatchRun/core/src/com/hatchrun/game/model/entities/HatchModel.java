package com.hatchrun.game.model.entities;

import com.hatchrun.game.model.GameModel;

/**
 * A model representing a hatch (the only game element user-controlled)
 */

public class HatchModel extends EntityModel {

    public enum HatchType {BLUE, PURPLE, YELLOW}
    public enum HatchState {STILL, RUNNING};
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
        super(lane, x, y, 200, 301);
        shielded = false;
        if(GameModel.getInstance().getHatchOrder().size()>0)
            currentHatch = GameModel.getInstance().getHatchOrder().get(GameModel.getInstance().getHatchOrderIndex());
        else currentHatch = HatchType.BLUE;

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




    public HatchState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(HatchState currentState) {
        this.currentState = currentState;
    }

}

