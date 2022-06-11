package common.geometry;

import java.io.Serializable;

public class Line implements Serializable {
    public Point start;
    public Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public static Line scalePoint(Point start, double dx, double dy) {
        Point end = new Point(start.x + dx, start.y + dy);
        return new Line(start , end);
    }

    public double getDistance() {
        return start.getDistance(end);
    }

    public boolean pointIsOnLine(Point p) {
        double distanceSum = p.getDistance(end) + p.getDistance(start);
        return distanceSum == 2.0;
    }

    @Override
    public java.lang.String toString() {
        return start.toString() + "->" + end.toString();
    }
}
