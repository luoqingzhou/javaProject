package maingame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Game extends JFrame{
    private StartForm startform;
    private static String path = System.getProperty("user.dir");

    private int formWidth;
    private int formHeight;

    private boolean isPlaying = true;

    private JButton btnRestartAndPause;
    private JButton btnMenu;
    private JLabel lblShowScore;
    private JLabel lblScore;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel gamePanel;

    public Game(StartForm startform) {
        super("marble");

        this.startform = startform;
        this.formWidth = startform.getWidth();
        this.formHeight = startform.getHeight();

        this.setPanel();
        this.setMiddle();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void setMiddle() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();
        this.setBounds((screenWidth - formWidth) / 2, (screenHeight - formHeight) / 2, formWidth, formHeight);
    }

    public void setPanel() {
//        ImageIcon img = new ImageIcon(path + "/resources/background1.png");
//        lblBackground = new JLabel(img);
//        lblBackground.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
//        getLayeredPane().add(lblBackground, -30000);


        mainPanel = (JPanel)this.getContentPane();
        mainPanel.setOpaque(false);

        mainPanel.setLayout(new BorderLayout());
        topPanel = getTopPanel();
        gamePanel = getGamePanel();
        topPanel.setOpaque(false);
        gamePanel.setOpaque(false);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
    }

    private JPanel getTopPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        btnRestartAndPause = new JButton("pause");
        btnRestartAndPause.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnMenu = new JButton("menu");
        btnMenu.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblShowScore = new JLabel("current score");
        lblShowScore.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblScore = new JLabel("000");
        lblScore.setFont(new Font("Times New Roman", Font.BOLD, 20));

        panel.add(btnMenu);
        panel.add(btnRestartAndPause);
        panel.add(lblShowScore);
        panel.add(lblScore);


        BtnListener btnListener = new BtnListener();
        btnMenu.addActionListener(btnListener);
        btnRestartAndPause.addActionListener(btnListener);

        return panel;
    }

    private JPanel getGamePanel() {
        JPanel panel = new GamePanel();
        //System.out.println("gamePanel created");
        return panel;
    }



    class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnMenu) {
                isPlaying = false;
                Game.this.dispose();
                Game.this.startform.setVisible(true);
            }
            else if (e.getSource() == btnRestartAndPause) {
                if (isPlaying) {
                    isPlaying = false;
                    btnRestartAndPause.setText("restart");
                    JOptionPane.showMessageDialog(Game.this, "pause");
                }
                else {
                    isPlaying = true;
                    btnRestartAndPause.setText("pause");
                    JOptionPane.showMessageDialog(Game.this, "restart");
                }
            }
        }
    }
}
