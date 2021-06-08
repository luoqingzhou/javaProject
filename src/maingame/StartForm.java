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
    private JPanel startPanel;
    private JButton btnStart;
    private JButton btnExit;
    private JButton btnRank;

    public StartForm() {
        super("marble");
        this.setPanel();
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

    public JPanel getStartPanel() {
        JPanel panel = new StartPanel();
        panel.setLayout(null);

//        ImageIcon imgStart = new ImageIcon(path + "/resources/start.jpg");
//        ImageIcon imgExit = new ImageIcon(path + "/resources/exit.jpg");
//        ImageIcon imgRank = new ImageIcon(path + "/resources/rank.jpg");

        btnStart = new JButton("Start");
        btnExit = new JButton("Exit");
        btnRank = new JButton("Rank");

        btnStart.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnExit.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnRank.setFont(new Font("Times New Roman", Font.BOLD, 20));

        BtnListener btnListener = new BtnListener();
        btnStart.addActionListener(btnListener);
        btnExit.addActionListener(btnListener);
        btnRank.addActionListener(btnListener);

//        btnStart.setBounds(175, 300, imgStart.getIconWidth(), imgStart.getIconHeight());
//        btnExit.setBounds(175, 400, imgExit.getIconWidth(), imgExit.getIconHeight());
//        btnRank.setBounds(175, 500, imgRank.getIconWidth(), imgRank.getIconHeight());
        btnStart.setBounds(175, 300, 100, 40);
        btnExit.setBounds(175, 400, 100, 40);
        btnRank.setBounds(175, 500, 100, 40);

        panel.add(btnStart);
        panel.add(btnExit);
        panel.add(btnRank);

        return panel;
    }

    public void setPanel() {
        mainPanel = (JPanel)this.getContentPane();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setOpaque(false);

        startPanel = getStartPanel();
        startPanel.setOpaque(false);
        mainPanel.add(startPanel, BorderLayout.CENTER);
    }


    class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnStart) {
                //System.out.println("ready to new Game");
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

    class StartPanel extends JPanel {
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Image image = new ImageIcon(path + "/resources/background4.png").getImage();
            g.drawImage(image, 0, 0, this);
        }
    }
}
