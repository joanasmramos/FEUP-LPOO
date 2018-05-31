package com.hatchrun.game.Utilities;

import com.hatchrun.game.controller.GameController;
import com.hatchrun.game.model.entities.EntityModel;

public class Util {

    public  Util(){

    }

    public static int getStartingX(int width, EntityModel.ElementLane lane){

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
