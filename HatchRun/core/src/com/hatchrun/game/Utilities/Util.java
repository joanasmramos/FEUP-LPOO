package com.hatchrun.game.Utilities;

import com.hatchrun.game.controller.GameController;
import com.hatchrun.game.model.entities.EntityModel;

/**
 * An utility class for the lanes
 */
public class Util {


    /**
     * Computes the starting x coordinate for the given lane
     * @param width Width of each lane
     * @param lane Pretended lane
     * @return Lane's starting x coordinate
     */
    private static int getStartingX(int width, EntityModel.ElementLane lane){

        switch (lane){
            case LEFT:
                return GameController.leftX + (GameController.widthLane - width) / 2;
            case MIDDLE:
                return GameController.centerX + (GameController.widthLane - width) / 2;
            case RIGHT:
                return GameController.rightX + (GameController.widthLane - width) / 2;
        }

        return 0;
    }


}
