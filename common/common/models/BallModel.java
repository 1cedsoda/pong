package common.models;

public class BallModel {
    public double x;
    public double y;
    // percent per second -> Velocity
    public double xv;
    public double yv;

    public BallModel() {
        x = 0.5;
        y = 0.5;
        xv = 0.2;
        yv = 0.2;
    }
}
