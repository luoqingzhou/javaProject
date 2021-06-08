package maingame;

public class Vector {
    private double x;
    private double y;
    private double xn;
    private double yn;

    public static Vector mul(Vector v, double num) {
        return new Vector(v.getX() * num, v.getY() * num, 0);
    }

    public static Vector add(Vector v1, Vector v2) {
        return new Vector(v1.getX() + v2.getX(), v1.getY() + v2.getY(), 0);
    }

    public static Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.getX() - v2.getX(), v1.getY() - v2.getY(), 0);
    }

    public static double innerMul(Vector v1, Vector v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY();
    }

    public Vector(double x, double y) {
        //System.out.println(x + "     " + (x * x + y * y) + "     " + (x / (x * x + y * y)));
        this.x = (x / Math.sqrt((x * x + y * y)));
        this.y = (y / Math.sqrt((x * x + y * y)));
        this.yn = Math.sqrt(Math.pow(this.x, 2) / (Math.pow(this.x, 2) + Math.pow(this.y, 2)));
        this.xn = - this.y * this.yn / this.x;
    }

    public Vector(double x, double y, int a) {
        a = 0;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getXDir() {
        return this.x / Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public double getYDir() {
        return this.y / Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public void print() {
        System.out.println(x + "     " + y);
    }
    //向量反相
    public void reverse() {
        this.x = -this.x;
        this.y = -this.y;
    }

}

