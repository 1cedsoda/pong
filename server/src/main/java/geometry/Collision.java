package geometry;

import common.geometry.Line;
import common.geometry.Point;
import common.geometry.Vector;
import controllers.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Collision implements Serializable {
    public Point point;
    public double distance;

    public double reflectionAngle;

    public static List<Line> walls = new ArrayList<Line>(List.of(
            new Line(new Point(-1, -1), new Point(-1, 1)), // Prioritize left wall
            new Line(new Point(1, 1), new Point(1, -1)), // Prioritize right wall
            new Line(new Point(-1, 1), new Point(1, 1)),
            new Line(new Point(1, -1), new Point(-1, -1)))
    );

    public Collision(Point point, double intersectionDistance, double reflectionAngle) {
        this.point = point;
        this.distance = intersectionDistance;
        this.reflectionAngle = reflectionAngle;
    }

    public Collision(Point point, Point start, double reflectionAngle) {
        this.point = point;
        this.distance = start.getDistance(point);
        this.reflectionAngle = reflectionAngle;
    }

    public static Collision withLine(Line primaryLine, Line secondaryLine) {

        Point A = primaryLine.start;
        Point B = primaryLine.end;
        Point C = secondaryLine.start;
        Point D = secondaryLine.end;

        // Line AB represented as a1x + b1y = c1
        double a1 = B.y - A.y;
        double b1 = A.x - B.x;
        double c1 = a1 * (A.x) + b1 * (A.y);

        // Line CD represented as a2x + b2y = c2
        double a2 = D.y - C.y;
        double b2 = C.x - D.x;
        double c2 = a2 * (C.x) + b2 * (C.y);

        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            // The lines are parallel.
            return null;
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            Point i = new Point(x, y);
            if (secondaryLine.pointIsOnLine(i) && primaryLine.pointIsOnLine(i)) {
                Vector primaryVector = primaryLine.getVector();
                Vector reflectionVector = primaryVector.reflectFromAxialLine(secondaryLine);
                double reflectionAngle = reflectionVector.direction;
                return new Collision(i, primaryLine.start.getDistance(i), reflectionAngle);
            }
            return null;
        }
    }

    public static Collision withWalls(Line primarayLine, Game game) {
        // Bound shortcut
        if (primarayLine.end.inGameArea()) {
            return null;
        }

        for (Line wall : walls) {
            Collision collision = Collision.withLine(primarayLine, wall);
            if (collision != null) {
                game.log("Collision with wall at " + collision.point);
                return collision;
            }
        }
        return null;
    }

    public String toString() {
        return "{ " + point.toString() + " , " + distance + " }";
    }
}
