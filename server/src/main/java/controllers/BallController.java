package controllers;

import common.models.BallModel;
import common.geometry.Line;

public class BallController extends common.controllers.BallController {
    private GameController gameController;
    public BallController(BallModel ballState, GameController gameController) {
        super(ballState);
    }

    public calculateStep( double dt ){
        // calculate new x and y position with current ballState.x and ballState.xv
        double dx = ballState.xv * dt;
        double dy = ballState.yv * dt;
        Line movement = Line.scalePoint(ballState.position, dx, dy);

        // check if the ball is still in the game area
        if (Math.abs(newX) < 1.0 && Math.abs(newY) < 1.0) {
            // if the ball is still in the game area, update the ballState.x and ballState.y
            ballState.x = newX;
            ballState.y = newY;
        } else {





        }
    }


}
