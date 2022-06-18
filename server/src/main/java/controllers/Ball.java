package controllers;

import common.geometry.Line;
import common.geometry.Point;
import common.states.BallState;
import geometry.Collision;

public class Ball {

    BallState state;

    public Ball(BallState state) {
        this.state = state;
    }

    public void calculateStep(double seconds, Point fromPosition, Game game) {
        // calculate new x and y position with current ball.x and ball.xv
        Line movement = fromPosition.addVector(state.velocity.multiply(seconds));

        Collision collision = Collision.withWalls(movement, game);

        // check if the ball is still in the game area
        if (collision == null) {
            // if the ball is still in the game area, update the ball.x and ball.y
            state.position = movement.end;
        } else {
            // if collision with left/right side but not with racket (goal)
            if (Collision.walls.get(0).pointIsOnLine(collision.point) && !game.leftRacket.getLine().pointIsOnLine(collision.point)) {
                // Left player lost
                game.rightRacket.incrementScore();
                game.ball.state.position.setXY(-0.5, 0);
                game.ball.state.velocity.direction = 0;
                game.ball.state.roundSeconds = 0;
                game.log("Left player lost");
                return;
            } else if (Collision.walls.get(1).pointIsOnLine(collision.point) && !game.rightRacket.getLine().pointIsOnLine(collision.point)) {
                // Right player lost
                game.leftRacket.incrementScore();
                game.ball.state.position.setXY(0.5, 0);
                game.ball.state.velocity.direction = 180;
                game.ball.state.roundSeconds = 0;
                game.log("Right player lost");
                return;
            }

            // reflect
            state.velocity.direction = collision.reflectionAngle; // update ball velocity vector
            // randomize reflection angle by 1-10 degrees but not into area 260-280 and 80-100
            do {
                state.velocity.direction += (Math.random() * 60) - 30;
                state.velocity.direction = state.velocity.direction % 360;
            } while ((state.velocity.direction > 80 && state.velocity.direction < 100) || (state.velocity.direction > 260 && state.velocity.direction < 280));

            double distanceRemaining = movement.getDistance() - collision.distance;
            double remainingSeconds = distanceRemaining / state.velocity.length;

            // calculate Step recursively
            calculateStep(remainingSeconds, collision.point, game);
        }
    }
}
