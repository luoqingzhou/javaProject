package maingame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    //private Game game;
    private static String path = System.getProperty("user.dir");
    private Image imgBackground;
    private Image imgBall;
    private Timer timerBall;
    private Ball ball;
    private int panelWidth;
    private int panelHeight;
    private boolean isStart;
    private ArrayList<Point> pointStartArray;
    private ArrayList<Point> pointEndArray;
    private ArrayList<Vector> vecArray;
    private int recentIndex;
    public GamePanel() {

        panelWidth = 450;
        panelHeight = 600;
        recentIndex = -1;

        imgBackground = new ImageIcon(path + "/resources/background4.png").getImage();
        imgBall = new ImageIcon(path + "/resources/ball.png").getImage();

        ball = new Ball();
        isStart = false;

        BallTaskListener ballTaskListener = new BallTaskListener();
        MouseTaskListener mouseTaskListener = new MouseTaskListener();
        timerBall = new Timer(1, ballTaskListener);
        timerBall.start();
        this.addMouseListener(mouseTaskListener);
        this.addMouseMotionListener(mouseTaskListener);

        pointStartArray = new ArrayList<>();
        pointEndArray = new ArrayList<>();
        vecArray = new ArrayList<>();

        pointStartArray.add(new Point(0, 0));
        pointEndArray.add(new Point(panelWidth, 0));

        pointStartArray.add(new Point(panelWidth, 0));
        pointEndArray.add(new Point(panelWidth, panelHeight));

        pointStartArray.add(new Point(panelWidth, panelHeight));
        pointEndArray.add(new Point(0, panelHeight));

        pointStartArray.add(new Point(0, panelHeight));
        pointEndArray.add(new Point(0, 0));

        this.upDateVecArray();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //System.out.println("paint!");
        g.drawImage(imgBackground, 0, 0, this.getWidth(), this.getHeight(), this);
        g.drawImage(imgBall, (int)ball.getX(), (int)ball.getY(), 15, 15,this);
    }

    public void startGame() {
        isStart = true;
        //System.out.println("mouseClicked!");
        ball.setV0();
        ball.setRocketedTrue();
    }

    public void stopGame() {
        isStart = false;
        ball.init();
    }

    class BallTaskListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isStart) {
                //System.out.println("timer work");
                System.out.println("check");
                checkForCollision();
                ball.resetV0();
                ball.updateBallXY();
                System.out.println(ball.getX() + "   " + ball.getY());
                GamePanel.this.repaint();
            }
        }
    }

    class MouseTaskListener implements MouseMotionListener, MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!isStart) {
                GamePanel.this.startGame();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (!isStart) {
                //System.out.println("mouseMoved!");
                int x = e.getX();
                int y = e.getY();
                ball.setVecDir(x - 225, y);
            }
        }
    }

    public void upDateVecArray() {
        for(int i = 0; i < pointStartArray.size(); i++)  {
            if (i < vecArray.size()) {
                vecArray.set(i, new Vector(pointEndArray.get(i).getX() - pointStartArray.get(i).getX(), pointEndArray.get(i).getY() - pointStartArray.get(i).getY()));
            }
            else {
                vecArray.add(new Vector(pointEndArray.get(i).getX() - pointStartArray.get(i).getX(), pointEndArray.get(i).getY() - pointStartArray.get(i).getY()));
            }
        }
    }

    public void checkForCollision() {
        for (int i = 0; i < pointStartArray.size(); i++) {
            if (((ball.getX() - pointStartArray.get(i).getX()) * (ball.getY() - pointEndArray.get(i).getY())
                - (ball.getX() - pointEndArray.get(i).getX()) * (ball.getY() - pointStartArray.get(i).getY())) < 0.000001 && recentIndex != i) {
                System.out.println("Collision!!!");
                System.out.println(ball.getX()  + "    " + ball.getY());
                System.out.println(pointStartArray.get(i).getX() + "    " + pointStartArray.get(i).getY());
                System.out.println(pointEndArray.get(i).getX() + "    " + pointEndArray.get(i).getY());
                vecArray.get(i).print();
                double inner = Vector.innerMul(ball.getVecDir(), vecArray.get(i));
                Vector tempVec;
                if (inner < 0) {
                   inner = -inner;
                   tempVec = new Vector(-vecArray.get(i).getX(), -vecArray.get(i).getY());
                }
                else {
                    tempVec = new Vector(vecArray.get(i).getX(), vecArray.get(i).getY());
                }
                ball.getVecDir().print();
                System.out.println(inner);
                Vector vNotChange = Vector.mul(tempVec, inner);
                Vector vChange = Vector.sub(ball.getVecDir(), vNotChange);
                vNotChange.print();
                vChange.print();
                vChange.reverse();
                vChange.print();
                ball.setVecDir(Vector.add(vNotChange, vChange));
                ball.getVecDir().print();
                recentIndex = i;
                break;
            }
        }
    }
}
