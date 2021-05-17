package maingame;

public class Ball {
    private boolean isRocketed;
    private int ballSpeed;
    private double vX0;
    private double vY0;
    private double vX;
    private double vY;
    private int xReference;
    private int yReference;
    private double xBall;
    private double yBall;
    private int gy;
    private double ballReg;
    private int ballSize;


    public void init() {
        isRocketed = false;
        ballSpeed = 5;
        xReference = 225;
        yReference = 0;
        xBall = xReference;
        yBall= yReference;
        ballSize = 30;
        gy = 3;
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

    public void updateBallXY() {
        updateV();
        xBall += vX;
        yBall += vY;
    }

    public void setRocketedTrue() {
        isRocketed = true;
    }

    public void setRocketedFalse() {
        isRocketed = false;
    }

    public void setReg(double reg) {
        ballReg = reg;
    }

    public void setV0() {
        vX0 = ballSpeed * Math.cos(ballReg);
        vY0 = ballSpeed * Math.sin(ballReg);
    }

    public void updateV() {
        vX = vX0;
        vY = vY0 + gy;
    }
}
