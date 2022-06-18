package common.states;

import common.geometry.Point;
import common.geometry.Vector;

public class BallState {
    public Point position;
    // percent per second -> yVelocity
    public Vector velocity;
    public int roundSeconds;
}
