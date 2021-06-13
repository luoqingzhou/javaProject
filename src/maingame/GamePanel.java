package maingame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    private Game game;

    private static String path = System.getProperty("user.dir");

    private Image imgBackground;
    private Image imgBall;
    private Image imgCircle;
    private Image imgTriangle;
    private Image imgSquare;

    private Timer timerBall;
    private Ball ball;
    private int panelWidth;
    private int panelHeight;


    private boolean isStart;
    private ArrayList<Point> pointStartArray;
    private ArrayList<Point> pointEndArray;
    private ArrayList<Vector> vecArray;
    private ArrayList<Graph> graphArray;
    private ArrayList<Integer> graphX;
    private ArrayList<Integer> graphY;


    private int sumScore;
    private int perScore;

    private int lastIndex;
    public GamePanel(Game game) {
        this.game = game;

        lastIndex = -1;

        panelWidth = 450;
        panelHeight = 600;

        perScore = 0;
        sumScore = 0;

        imgBackground = new ImageIcon(path + "/resources/background4.png").getImage();
        imgBall = new ImageIcon(path + "/resources/ball.png").getImage();
        imgTriangle = new ImageIcon(path + "/resources/triangle3.png").getImage();
        imgCircle = new ImageIcon(path + "/resources/circle3.png").getImage();
        imgSquare = new ImageIcon(path + "/resources/square3.png").getImage();



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
        graphArray = new ArrayList<>();
        graphX = new ArrayList<>();
        graphY = new ArrayList<>();

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
        g.drawImage(imgBackground, 0, 0, this.getWidth(), this.getHeight(), this);
        g.drawImage(imgBall, (int)ball.getX(), (int)ball.getY(), 15, 15,this);
        try {
            for (int i = 0; i < graphArray.size(); i++) {
                if (graphArray.get(i).getType() == 0) {
                    g.drawImage(imgTriangle, graphX.get(i), graphY.get(i), 40, 40,this);
                }
                else if (graphArray.get(i).getType() == 1) {
                    g.drawImage(imgCircle, graphX.get(i), graphY.get(i), 40, 40,this);
                }
                else {
                    g.drawImage(imgSquare, graphX.get(i), graphY.get(i), 40, 40,this);
                }
                g.setColor(Color.WHITE);
                g.setFont((new Font("Times New Roman", Font.BOLD, 15)));
                g.drawString(String.valueOf(graphArray.get(i).getScore()), graphX.get(i) + 15, graphY.get(i) + 25);
            }
        }catch (Exception e) {
            System.out.println("Draw error");
        }
    }

    public void startGame() {
        upDataGraph();
        createGraph();
        upDatePoint();
        upDateVecArray();
        //pointDebug();

        isStart = true;
        lastIndex = -1;
        ball.setV0();
        ball.isRocketed = true;
    }

    public void stopGame() {

        isStart = false;
        ball.isOut = true;
        ball.init();
    }

    class BallTaskListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println("timer work");
            if (isStart) {
                checkForCollision();
                game.setLblScore(perScore);
                if (!isStart) {
//                    System.out.println("action return");
                    GamePanel.this.repaint();
                }
                else {
                    ball.resetV0();
                    ball.updateBallXY();
                    GamePanel.this.repaint();
                }
            }
            else {
                GamePanel.this.repaint();
            }
        }
    }

    class MouseTaskListener implements MouseMotionListener, MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!isStart) {
//                System.out.println("mouse clicked!");
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

    public void checkForCollision() {
//        System.out.println("check");
//        System.out.println(pointStartArray.size());
//        System.out.println(ball.getX());
//        System.out.println(ball.getY());
        for (int i = 0; i < pointStartArray.size(); i++) {
//            double judge1 = Math.abs((ball.getX() - pointStartArray.get(i).getX()) * (ball.getY() - pointEndArray.get(i).getY()))
//                    - Math.abs((ball.getX() - pointEndArray.get(i).getX()) * (ball.getY() - pointStartArray.get(i).getY()));
//            double judge2 = ((ball.getY() - pointEndArray.get(i).getY()) * (ball.getY() - pointStartArray.get(i).getY()));
//            if (i == 2 && ball.getY() < 602 && ball.getY() > 598) {
//                System.out.println(judge1 + "  " + judge2);
//                System.out.println(ball.getX() - pointStartArray.get(i).getX());
//                System.out.println(ball.getY() - pointEndArray.get(i).getY());
//                System.out.println(ball.getX() - pointEndArray.get(i).getX());
//                System.out.println(ball.getY() - pointStartArray.get(i).getY());
//            }
//            if (Math.abs(judge1) < 10
//                && judge2 < 0.0000001) {
            double com = 0;
            boolean judgeX = false;
            boolean judgeY = false;
            boolean on = false;
            if (Math.abs(pointStartArray.get(i).getX() - pointEndArray.get(i).getX()) < 0.0001) {
                com = pointEndArray.get(i).getX();
                judgeX = true;
                double max = Math.max(pointStartArray.get(i).getY(), pointEndArray.get(i).getY());
                double min = Math.min(pointStartArray.get(i).getY(), pointEndArray.get(i).getY());
                if (ball.getY() >= min && ball.getY() <= max) {
                    on = true;
                }
            }
            else {
                com = pointEndArray.get(i).getY();
                judgeY = true;
                double max = Math.max(pointStartArray.get(i).getX(), pointEndArray.get(i).getX());
                double min = Math.min(pointStartArray.get(i).getX(), pointEndArray.get(i).getX());
                if (ball.getX() >= min && ball.getX() <= max) {
                    on = true;
                }
            }

            if(((judgeX && Math.abs(ball.getX() - com) < 1) || (judgeY && Math.abs(ball.getY() - com) < 1)) && on && i != lastIndex) {
                if (i == 2) {
                    stopGame();
                    System.out.println("stop");
                    break;
                }
                lastIndex = i;
                if (i > 3) {
                    Graph gra = graphArray.get(i / 4 - 1);
                    int index = i / 4 - 1;
                    perScore++;
                    if (gra.subScore() == 0) {
                        graphArray.remove(index);
                        graphX.remove(index);
                        graphY.remove(index);
                        upDatePoint();
                        upDateVecArray();
                        lastIndex = -1;
                    }
                }
//                System.out.println("Collision!!!");
//                System.out.println(ball.getX()  + "    " + ball.getY());
//                System.out.println(pointStartArray.get(i).getX() + "    " + pointStartArray.get(i).getY());
//                System.out.println(pointEndArray.get(i).getX() + "    " + pointEndArray.get(i).getY());
//                vecArray.get(i).print();
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
//                System.out.println(inner);
                Vector vNotChange = Vector.mul(tempVec, inner);
                Vector vChange = Vector.sub(ball.getVecDir(), vNotChange);
                vNotChange.print();
                vChange.print();
                vChange.reverse();
                vChange.print();
                ball.setVecDir(Vector.add(vNotChange, vChange));
                ball.getVecDir().print();
                break;
            }
        }
    }

    public void createGraph() {
        Random r = new Random();
        int num = r.nextInt(2) + 1;
        for (int i = 0; i < num; i++) {
            boolean isOK = false;
            int x = 0;
            int y = 0;
            x = r.nextInt(360) + 40;
            y = r.nextInt(50) + 450;
            while (!isOK && i != 0) {
                x = r.nextInt(360) + 40;
                y = r.nextInt(80) + 600;
                for (int j = 0; j < graphX.size(); j++) {
                    if (Math.abs(x - graphX.get(j)) >30 && Math.abs(y - graphY.get(j)) >30) {
                        isOK = true;
                    }
                    else {
                        isOK = false;
                        break;
                    }
                }
            }
            graphArray.add(new Graph());
            graphX.add(x);
            graphY.add(y);
        }
    }

    public void upDataGraph() {
       try {
           for (int i = 0; i < graphArray.size(); i++) {
               int y = graphY.get(i);
               graphY.set(i, y - 50);
           }
       }catch (Exception e) {
           System.out.println("upDataGraph error");
       }
        gameEndJudge();
    }

    public void upDatePoint() {
        try{
            for (int i = 0; i < graphArray.size(); i++) {
                if (4 * (i + 2) <= pointStartArray.size()) {
                    pointStartArray.set(4 * (i + 2) - 4, new Point(graphX.get(i), graphY.get(i)));
                    pointEndArray.set(4 * (i + 2) - 4, new Point(graphX.get(i) + 20, graphY.get(i)));
                    pointStartArray.set(4 * (i + 2) - 3, new Point(graphX.get(i) + 20, graphY.get(i)));
                    pointEndArray.set(4 * (i + 2) - 3, new Point(graphX.get(i) + 20, graphY.get(i) + 20));
                    pointStartArray.set(4 * (i + 2) - 2, new Point(graphX.get(i) + 20, graphY.get(i) + 20));
                    pointEndArray.set(4 * (i + 2) - 2, new Point(graphX.get(i), graphY.get(i) + 20));
                    pointStartArray.set(4 * (i + 2) - 1, new Point(graphX.get(i), graphY.get(i) + 20));
                    pointEndArray.set(4 * (i + 2) - 1, new Point(graphX.get(i), graphY.get(i)));
                }
                else {
                    pointStartArray.add(new Point(graphX.get(i), graphY.get(i)));
                    pointEndArray.add(new Point(graphX.get(i) + 20, graphY.get(i)));
                    pointStartArray.add(new Point(graphX.get(i) + 20, graphY.get(i)));
                    pointEndArray.add(new Point(graphX.get(i) + 20, graphY.get(i) + 20));
                    pointStartArray.add(new Point(graphX.get(i) + 20, graphY.get(i) + 20));
                    pointEndArray.add(new Point(graphX.get(i), graphY.get(i) + 20));
                    pointStartArray.add(new Point(graphX.get(i), graphY.get(i) + 20));
                    pointEndArray.add(new Point(graphX.get(i), graphY.get(i)));
                }
            }
        }catch (Exception e) {
            System.out.println("upDatePoint error");
        }
    }

    public void upDateVecArray() {
        try {
            for(int i = 0; i < pointStartArray.size(); i++)  {
                if (i < vecArray.size()) {
                    vecArray.set(i, new Vector(pointEndArray.get(i).getX() - pointStartArray.get(i).getX(), pointEndArray.get(i).getY() - pointStartArray.get(i).getY()));
                }
                else {
                    vecArray.add(new Vector(pointEndArray.get(i).getX() - pointStartArray.get(i).getX(), pointEndArray.get(i).getY() - pointStartArray.get(i).getY()));
                }
            }
        }catch (Exception e) {
            System.out.println("upDateVecArray error");
        }
    }

    public void gameEndJudge() {
        for (int i = 0; i < graphArray.size(); i++) {
            if (graphY.get(i) <= 10) {
                JOptionPane.showMessageDialog(this, "you dead");
                game.newGame();
            }
        }
    }

    public boolean pause() {
        if(isStart) {
            timerBall.stop();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean restart() {
        if (isStart) {
            timerBall.start();
            return true;
        }
        else {
            return false;
        }
    }

    public void pointDebug() {
        for (int i = 0; i < pointStartArray.size(); i++) {
            System.out.println(pointStartArray.get(i).getX() + "    " + pointStartArray.get(i).getY());
            System.out.println(pointEndArray.get(i).getX() + "    " + pointEndArray.get(i).getY());
        }
    }
}
