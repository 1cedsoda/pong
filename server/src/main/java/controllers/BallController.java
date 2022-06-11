package controllers;

import common.geometry.Point;
import common.messages.GameCreateSignal;
import common.models.BallModel;
import common.geometry.Line;

import java.util.ArrayList;
import java.util.List;

public class BallController extends common.controllers.BallController {

    public BallController(Point position, double xv, double yv, GameController gameController) {
        super(position, xv, yv, gameController);
    }

    public BallController(BallModel model, GameController gameController) {
        super(model, gameController);
    }

    public void calculateStep( double dt ){
        // calculate new x and y position with current ballState.x and ballState.xv
        double dx = xv * dt;
        double dy = yv * dt;
        Line movement = Line.scalePoint(position, dx, dy);

        // check if the ball is still in the game area
        if (Math.abs(movement.end.x) < 1.0 && Math.abs(movement.end.y) < 1.0) {
            // if the ball is still in the game area, update the ballState.x and ballState.y
            position = movement.end;
        }
        //else {
            // calculate collision point


        //}
    }
}
