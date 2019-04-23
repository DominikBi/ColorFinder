package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Main extends KeyAdapter {

    private Robot robot = new Robot();
    private Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    private JFrame jFrame = new JFrame();

    public void setup() {
        jFrame.setSize(100, 100);
        jFrame.setVisible(true);
        jFrame.addKeyListener(this);
    }


    private Main() throws AWTException {}

    public static void main(String[] args) throws AWTException {
        Main main = new Main();
        main.setup();
    }

    private int getRGBDistance(Color c1, Color c2) {
        int diffRed   = Math.abs(c1.getRed() - c2.getRed());
        int diffGreen = Math.abs(c1.getGreen() - c2.getGreen());
        int diffBlue  = Math.abs(c1.getBlue() - c2.getBlue());
        return diffRed + diffGreen + diffBlue;
    }

    public void start() {
        BufferedImage image = robot.createScreenCapture(area);
        for (int x = 0; x < image.getWidth(); x++){
            for (int y = 0; y < image.getHeight(); y++){
                if (getRGBDistance(Color.red, new Color(image.getRGB(x, y))) < 25){
                    robot.mouseMove(x,y);
                    break;
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("ja");
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE){
            start();
        }
    }

}
