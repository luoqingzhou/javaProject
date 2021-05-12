package maingame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    //private Game game;
    private static String path = System.getProperty("user.dir");
    private boolean isBackGroundPainted = false;
    private Image imgBackground;
    private Image imgBall;
    private Timer timerBall;
    private int panelWidth;
    private int panelHeight;
    private Ball ball;
    private boolean isStart;
    public GamePanel() {


        imgBackground = new ImageIcon(path + "/resources/background1.png").getImage();
        imgBall = new ImageIcon(path + "/resources/ball.png").getImage();

        panelWidth = this.getWidth();
        panelHeight = this.getHeight();

        ball = new Ball();
        isStart = false;

        BallTaskListener ballTaskListener = new BallTaskListener();
        MouseTaskListener mouseTaskListener = new MouseTaskListener();
        timerBall = new Timer(10, ballTaskListener);
        timerBall.start();
        this.addMouseListener(mouseTaskListener);
        this.addMouseMotionListener(mouseTaskListener);


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //System.out.println("paint!");
        if (!isBackGroundPainted) {
            g.drawImage(imgBackground, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        g.drawImage(imgBall, (int)ball.getX(), (int)ball.getY(), 30, 30,this);
    }

    public void startGame() {
        isStart = true;
        //System.out.println("mouseClicked!");
        ball.setDirection();
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
                System.out.println("timer work");
                ball.setDirection();
                ball.updateBallXY();
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
                System.out.println("mouseMoved!");
                int x = e.getX();
                int y = e.getY();
                double reg = Math.atan((y) / (x - 225));
                ball.setReg(reg);
            }
        }
    }




}
