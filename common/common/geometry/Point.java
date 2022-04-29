package common.geometry;

public class Point {
    public double x;
            public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean inGameArea() {
                return Math.abs(x) < 1.0 && Math.abs(y) < 1.0;
            }

            public double getDistance(Point p) {
                return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
            }
}
