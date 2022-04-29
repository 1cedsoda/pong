package common.geometry;

public class Line {
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
}
