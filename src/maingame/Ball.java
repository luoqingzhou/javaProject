package maingame;

public class Ball {
    private boolean isRocketed;
    private int ballSpeed;
    private double directionX;
    private double directionY;
    private int xReference;
    private int yReference;
    private double xBall;
    private double yBall;
    private double ballReg;
    private int ballSize;


    public void init() {
        isRocketed = false;
        ballSpeed = 8;
        xReference = 225;
        yReference = 0;
        xBall = xReference;
        yBall= yReference;
        ballSize = 30;
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
        xBall += directionX;
        yBall += directionY;
    }

    public void setRocketedTrue() {
        isRocketed = true;
    }

    public void setRocketedFalse() {
        isRocketed = false;
    }

    public boolean getIsRocketed() {
        return isRocketed;
    }

    public void setReg(double reg) {
        ballReg = reg;
    }

    public void setDirection() {
        directionX = ballSpeed * Math.cos(ballReg);
        directionY = ballSpeed * Math.sin(ballReg);
    }
}
