package maingame;

import sun.jvm.hotspot.ui.MemoryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartForm extends JFrame {

    static public void main(String[] args) {
        new StartForm();
    }

    private final int formWidth = 450;
    private final int  formHeight = 700;
    private JPanel mainPanel;
    private JPanel showPanel;
    private JPanel gamePanel;
    private JPanel menuPanel;
    private JLabel lblBackground;
    private JButton btnStart;
    private JButton btnExit;
    private JButton btnPause;
    private JButton btnRestart;
    private JButton btnRank;
    private JButton btnMenu;



    public StartForm() {
        super("marble");
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

        btnStart.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnExit.setFont(new Font("Times New Roman", Font.BOLD, 20));

        BtnListener btnListener = new BtnListener();
        btnStart.addActionListener(btnListener);
        btnExit.addActionListener(btnListener);

        showPanel.add(btnStart);
        gamePanel.add(btnExit);
    }

    public void setPanel() {
        mainPanel = (JPanel)this.getContentPane();
        showPanel = new JPanel();
        gamePanel = new JPanel();
        menuPanel = new JPanel();

        mainPanel.setSize(formWidth, formHeight);
        showPanel.setBounds(0, 0, formWidth, (int)(0.1 * formHeight));
        gamePanel.setBounds(0, (int)(0.1 * formHeight), formWidth, (int)(0.8 * formHeight));
        menuPanel.setBounds(0, (int)(0.9 * formHeight), formWidth, (int)(0.1 * formHeight));

        mainPanel.setLayout(new GridLayout(3, 1));
        showPanel.setLayout(new FlowLayout());
        menuPanel.setLayout(new FlowLayout());

        mainPanel.add(showPanel);
        mainPanel.add(gamePanel);
        mainPanel.add(menuPanel);
    }


    class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnStart) {
                JOptionPane.showMessageDialog(mainPanel, "start");
            }
            else if (e.getSource() == btnExit) {
                JOptionPane.showMessageDialog(mainPanel, "exit");
            }
            else {
                System.out.println("error");
            }
        }
    }
}
