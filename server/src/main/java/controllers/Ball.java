package controllers;

import common.geometry.Point;
import common.geometry.Line;
import common.states.BallState;
import geometry.Collision;

public class Ball {

    BallState state;

    public Ball(BallState state) {
        this.state = state;
    }

    public Ball() {
        this.state = new BallState();
    }

    public void calculateStep(double seconds, Point fromPosition, Game game){
        // calculate new x and y position with current ball.x and ball.xv
        Line movement = fromPosition.addVector(state.velocity.multiply(seconds));

        Collision collision = Collision.withWalls(movement);

        // check if the ball is still in the game area
        if (collision == null) {
            // if the ball is still in the game area, update the ball.x and ball.y
            state.position = movement.end;
        }
        else {
            // if collision with left/right side but not with racket (goal)
            if (Collision.walls.get(0).pointIsOnLine(collision.point) && !game.leftRacket.getLine().pointIsOnLine(collision.point)) {
                // Left player lost
                game.rightRacket.incrementScore();
                game.ball.state.position.setXY(0, 0);
                game.ball.state.velocity.direction = 330;
                System.out.println("Left player lost");
                return;
            } else if (Collision.walls.get(1).pointIsOnLine(collision.point) && !game.rightRacket.getLine().pointIsOnLine(collision.point)) {
                // Right player lost
                game.leftRacket.incrementScore();
                game.ball.state.position.setXY(0, 0);
                game.ball.state.velocity.direction = 30;
                System.out.println("Right player lost");
                return;
            }

            // reflect
            state.velocity.direction = collision.reflectionAngle; // update ball velocity vector
            double distanceRemaining = movement.getDistance() - collision.distance;
            double remainingSeconds = distanceRemaining / state.velocity.length;

            // calculate Step recursively
            calculateStep(remainingSeconds, collision.point, game);
        }
    }
}
