package maingame;

public class Vector {
    Point point;

    public Vector(Point point) {
        this.point = new Point(point.getX(), point.getY());
    }

    public Vector(Point pointStart, Point pointEnd) {
        this.point = new Point(pointEnd.getX() - pointStart.getX(), pointEnd.getY() - pointStart.getY());
    }

}
