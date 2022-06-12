package common.geometry;

import java.io.Serializable;

public class Line implements Serializable {
    public Point start;
    public Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public double getDistance() {
        return start.getDistance(end);
    }

    public boolean pointIsOnLine(Point p) {
        double distanceSum = p.getDistance(end) + p.getDistance(start);
        return distanceSum == getDistance();
    }

    // get vector pointing away 90 degrees from line
    public Vector getNormalVector() {
        double x = end.x - start.x;
        double y = end.y - start.y;
        return Vector.xy(y, -x);
    }

    public Vector getVector() {
        return Vector.xy(end.x - start.x, end.y - start.y);
    }

    @Override
    public java.lang.String toString() {
        return start.toString() + "->" + end.toString();
    }
}
