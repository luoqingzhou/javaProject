package maingame;

import sun.jvm.hotspot.ui.MemoryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StartForm extends JFrame {

    static public void main(String[] args) {
        new StartForm();
    }
    private static String path = System.getProperty("user.dir");
    private final int formWidth = 450;
    private final int  formHeight = 700;
    private JPanel mainPanel;
    private JLabel lblBackground;
    private JButton btnStart;
    private JButton btnExit;
    private JButton btnRank;

    protected JLabel lbl;

    public StartForm() {
        super("marble");
        //this.setBackground();
        this.setPanel();
        this.setBtn();
        this.setMiddle();



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

    }

    public void setMiddle() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();
        this.setBounds((screenWidth - formWidth) / 2, (screenHeight - formHeight) / 2, formWidth, formHeight);
    }

    public void setBtn() {
        btnStart = new JButton("start");
        btnExit = new JButton("exit");
        btnRank = new JButton("rank");

        btnStart.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnExit.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnRank.setFont(new Font("Times New Roman", Font.BOLD, 20));

        BtnListener btnListener = new BtnListener();
        btnStart.addActionListener(btnListener);
        btnExit.addActionListener(btnListener);
        btnRank.addActionListener(btnListener);

        btnStart.setBounds(175, 300, 100, 50);
        btnExit.setBounds(175, 400, 100, 50);
        btnRank.setBounds(175, 500, 100, 50);


        mainPanel.add(btnStart);
        mainPanel.add(btnExit);
        mainPanel.add(btnRank);


    }

    public void setPanel() {
        mainPanel = (JPanel)this.getContentPane();
        mainPanel.setLayout(null);
        mainPanel.setOpaque(false);
    }

    public void setBackground() {
        ImageIcon img = new ImageIcon(path + "/resources/background1.png");
        lblBackground = new JLabel(img);
        lblBackground.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        getLayeredPane().add(lblBackground, -30000);
    }


    class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnStart) {
                System.out.println("ready to new Game");
                new Game(StartForm.this);
                StartForm.this.setVisible(false);
            }
            else if (e.getSource() == btnExit) {
                System.exit(0);
            }
            else if (e.getSource() == btnRank) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(new File(path + "/rank/rank.txt")));
                    String temp = reader.readLine();
                    int i = 1;
                    String message = "rank\n";
                    while (temp != null && i <= 5) {
                        message += String.valueOf(i) + ": " + temp + "\n";
                        temp = reader.readLine();
                        i++;
                    }
                    JOptionPane.showMessageDialog(StartForm.this, message);
                    reader.close();
                }catch (IOException exp) {
                    System.out.println("rank error");
                }
            }
            else {
                System.out.println("error");
            }
        }
    }
}
