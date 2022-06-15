package common.geometry;

public class Vector {
    public double direction;
    public double length;

    public Vector(double direction, double length) {
        this.direction = direction;
        this.length = length;
    }

    static Vector xy(double x, double y) {
        double direction = Math.toDegrees(Math.atan2(y, x));
        double length = Math.sqrt(x * x + y * y);
        return new Vector(direction, length);
    }

    public Vector() {
        // Needed for Kryo serialization
    }

    public double getX() {
        return Math.cos(Math.toRadians(direction)) * length;
    }

    public double getY() {
        return Math.sin(Math.toRadians(direction)) * length;
    }

    public void setX(double x) {
        direction = Math.toDegrees(Math.atan2(getY(), x));
        length = Math.sqrt(x * x + getY() * getY());
    }

    public void setY(double y) {
        direction = Math.toDegrees(Math.atan2(getY(), getX()));
        length = Math.sqrt(getX() * getX() + y * y);
    }

    // Function that return
    // dot product of two vector array.
    static double dot(Vector a, Vector b) {
        double product = 0;
        product += product + a.getX() * b.getY();
        product += product + a.getY() * b.getX();
        return product;
    }

    public Vector reflectFromAxialLine(Line line) {
        if (line.start.x == line.end.x) {
            return Vector.xy(-getX(), getY());
        } else if (line.start.y == line.end.y) {
            return Vector.xy(getX(), -getY());
        } else {
            throw new RuntimeException("Line is not a vertical or horizontal line");
        }
    }

    public Vector multiply(double factor) {
        return new Vector(direction, length * factor);
    }
}
