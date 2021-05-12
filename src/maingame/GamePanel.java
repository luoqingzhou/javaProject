package maingame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static String path = System.getProperty("user.dir");
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image image = new ImageIcon(path + "/resources/background1.png").getImage();
        g.drawImage(image, 0, 0, this);
    }
}
