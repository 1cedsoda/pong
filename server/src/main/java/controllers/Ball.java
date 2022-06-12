package controllers;

import common.geometry.Point;
import common.geometry.Line;
import common.geometry.Vector;
import common.models.BallModel;
import geometry.Collision;

public class Ball extends BallModel<Game, Ball, Racket, Player> {

    public Ball(Point position, Vector velocity) {
        super(position, velocity);
    }

    public Ball() {
        super();
    }

    public void calculateStep(double seconds, Point fromPosition, Game game){
        // calculate new x and y position with current ball.x and ball.xv
        Line movement = fromPosition.addVector(velocity.multiply(seconds));

        Collision collision = Collision.withWalls(movement);

        // check if the ball is still in the game area
        if (collision == null) {
            // if the ball is still in the game area, update the ball.x and ball.y
            position = movement.end;
        }
        else {
            // if collision with left/right side but not with racket (goal)
            if (Collision.walls.get(0).pointIsOnLine(collision.point) && !game.leftRacket.getLine().pointIsOnLine(collision.point)) {
                // Left player lost
                game.rightRacket.incrementScore();
                game.ball.position.setXY(0, 0);
                game.ball.velocity.direction = 330;
                System.out.println("Left player lost");
                return;
            } else if (Collision.walls.get(1).pointIsOnLine(collision.point) && !game.rightRacket.getLine().pointIsOnLine(collision.point)) {
                // Right player lost
                game.leftRacket.incrementScore();
                game.ball.position.setXY(0, 0);
                game.ball.velocity.direction = 30;
                System.out.println("Right player lost");
                return;
            }

            // reflect
            velocity.direction = collision.reflectionAngle; // update ball velocity vector
            double distanceRemaining = movement.getDistance() - collision.distance;
            double remainingSeconds = distanceRemaining / velocity.length;

            // calculate Step recursively
            calculateStep(remainingSeconds, collision.point, game);
        }
    }
}
