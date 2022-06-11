package common.models;

import common.geometry.Point;

public class BallModel {
    public Point position;
    // percent per second -> Velocity
    public double xv;
    public double yv;

    protected BallModel(Point position, double xv, double yv) {
        this.position = position;
        this.xv = xv;
        this.yv = yv;
    }


    public BallModel() {
        position = new Point(0.5, 0.5);
        xv = 0.2;
        yv = 0.2;
    }
}
