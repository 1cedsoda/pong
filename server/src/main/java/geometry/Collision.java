package geometry;

import common.geometry.Line;
import common.geometry.Point;
import common.models.RacketModel;
import controllers.RacketController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Collision implements Serializable {
    Point intersection;
    double d;

    public Collision(Point intersection, double intersectionDistance) {
        this.intersection = intersection;
        this.d = intersectionDistance;
    }

    public Collision(Point intersection, Point start) {
        this.intersection = intersection;
        this.d = start.getDistance(intersection);
    }

    public static Collision calulateCollision(Line primaryLine, Line secondaryLine) {

        Point A = primaryLine.start;
        Point B = primaryLine.end;
        Point C = secondaryLine.start;
        Point D = secondaryLine.end;

        // Line AB represented as a1x + b1y = c1
        double a1 = B.y - A.y;
        double b1 = A.x - B.x;
        double c1 = a1*(A.x) + b1*(A.y);

        // Line CD represented as a2x + b2y = c2
        double a2 = D.y - C.y;
        double b2 = C.x - D.x;
        double c2 = a2*(C.x)+ b2*(C.y);

        double determinant = a1*b2 - a2*b1;

        if (determinant == 0)
        {
            // The lines are parallel. This is simplified
            // by returning a pair of FLT_MAX
            return null;
        }
        else
        {
            double x = (b2*c1 - b1*c2)/determinant;
            double y = (a1*c2 - a2*c1)/determinant;
            Point i = new Point(x, y);
            if (secondaryLine.pointIsOnLine(i)) {
                return new Collision(i, primaryLine.start.getDistance(i));
            }
            return null;
        }




    }

    public static Collision calculateCollisonWithWalls(Line primarayLine) {
        List<Line> walls = new ArrayList<Line>();
        walls.add(new Line(new Point(-1, -1), new Point(-1, 1)));
        walls.add(new Line(new Point(-1, 1), new Point(1, 1)));
        walls.add(new Line(new Point(1, 1), new Point(1, -1)));
        walls.add(new Line(new Point(1, -1), new Point(-1, -1)));

        for (Line wall : walls) {
            Collision collision = Collision.calulateCollision(primarayLine, wall);
            if (collision != null) {
                return collision;
            }
        }
        return null;
    }

    public static Collision calculateCollsionWithRackets(Line primaryLine, RacketController leftRacketController, RacketController rightRacketController) {
        Collision collision = calculateCollsionWithRacket(primaryLine, leftRacketController, 0);
        if (collision != null) {
            return collision;
        }

        collision = calculateCollsionWithRacket(primaryLine, rightRacketController, 1);
        return collision;
    }

    public static Collision calculateCollsionWithRacket(Line primaryLine, RacketController racketController, double x) {
        Line racketLine = racketController.getLine(x);
        return Collision.calulateCollision(primaryLine, racketLine);
    }

    public String toString() {
        return "{ " + intersection.toString() + " , " + d + " }";
    }
}
