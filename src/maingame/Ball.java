package maingame;

public class Ball {
    public boolean isRocketed;
    public boolean isOut;
    private double ballSpeed;
    private double collisionSpeed;
    private double vX0;
    private double vY0;
    private double vX;
    private double vY;
//    private int xReference;
//    private int yReference;
    private double xBall;
    private double yBall;
    private double gy;
//    private double ballReg;

    private Vector vecDir;

    public void init() {
        System.out.println("init");
        isRocketed = false;
        isOut = false;
        ballSpeed = 0.1;
        collisionSpeed = 1;
//        xReference = 225;
//        yReference = 0;
//        xBall = xReference;
//        yBall= yReference;
        xBall = 225;
        yBall = 10;
        gy = 0.5;
        vecDir = new Vector(0, 0);
    }

    public Ball() {
        init();
    }

    public double getX() {
        return xBall;
    }
    public double getY() {
        return yBall;
    }
    public Vector getVecDir() {return vecDir;}

    public void updateBallXY() {
        updateV();
        xBall += vX;
        yBall += vY;
    }

    public void setVecDir(int x, int y) {
        this.vecDir = new Vector(x, y);
    }

    public void setVecDir(Vector v) {this.vecDir = v;}

    public void setV0() {
        vX0 = ballSpeed * vecDir.getXDir();
        vY0 = ballSpeed * vecDir.getYDir();
    }

    public void resetV0() {
        vX0 = collisionSpeed * vecDir.getXDir();
        vY0 = collisionSpeed * vecDir.getYDir();
    }

    public void updateV() {
        vX = vX0;
        vY = vY0 + gy;
    }
}
