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

    private JLabel lblBackground;
    private JButton btnRestartAndPause;
    private JLabel lblScore;
    private JTextField txtScore;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel gamePanel;

    public Game(StartForm startform) {
        super("marble");

        this.startform = startform;
        this.formWidth = startform.getWidth();
        this.formHeight = startform.getHeight();

        this.setUI();
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

    public void setUI() {
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
        lblScore = new JLabel("current score");
        lblScore.setFont(new Font("Times New Roman", Font.BOLD, 20));
        txtScore = new JTextField("000");
        txtScore.setFont(new Font("Times New Roman", Font.BOLD, 20));

        panel.add(btnRestartAndPause);
        panel.add(lblScore);
        panel.add(txtScore);

        BtnListener btnListener = new BtnListener();
        btnRestartAndPause.addActionListener(btnListener);

        return panel;
    }

    private JPanel getGamePanel() {
        JPanel panel = new JPanel();
        System.out.println("gamePanel created");
        return panel;
    }

    class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnRestartAndPause) {
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
