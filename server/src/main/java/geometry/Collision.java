package geometry;

import common.geometry.Line;
import common.geometry.Point;

public class Collision {
    Point intersection;
    double d;

    public Collision(Point intersection, double intersectionDistance) {
        this.intersection = intersection;
        this.d = intersectionDistance;
    }

    public static Collision calulateCollision(Line primaryLine, Line secondaryLine) {

        double a1 = primaryLine.end.x - primaryLine.start.y;
        double b1 = primaryLine.start.x - primaryLine.end.x;
        double c1 = a1 * primaryLine.start.x + b1 * primaryLine.start.y;

        double a2 = secondaryLine.end.x - secondaryLine.start.y;
        double b2 = secondaryLine.start.x - secondaryLine.end.x;
        double c2 = a2 * secondaryLine.start.x + b2 * secondaryLine.start.y;

        double delta = a1 * b2 - a2 * b1;
        Point intersection = new Point((float) ((b2 * c1 - b1 * c2) / delta), (float) ((a1 * c2 - a2 * c1) / delta));


        if (intersection.x == Double.MIN_VALUE || intersection.x == Double.MIN_VALUE) {
            return null;
        } else {
            double intersectionDistance = primaryLine.start.getDistance(intersection);
            return new Collision(intersection, intersectionDistance);
        }
    };
}
