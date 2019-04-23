package Main;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FrameRecorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


public class Main implements KeyListener {
    Robot robot = new Robot();
    Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    JFrame jFrame = new JFrame();
    int x = 0;
    int y = 0;

    public void KeyEventClass() {
        jFrame.setSize(100, 100);
        jFrame.setVisible(true);
        jFrame.addKeyListener(this);


    }


    public Main() throws AWTException {
    }

    public static void main(String[] args) throws AWTException {
        Main main = new Main();
        main.KeyEventClass();


    }

    public void start() {
          if(x >= 1920 && y >= 1080){
              x=0;
              y=0;

          }

        BufferedImage image = robot.createScreenCapture(area);
        while(x < 1920){
            while(y < 1080){
                int blue = image.getRGB(x, y) & 0xFF;
                int green = (image.getRGB(x, y) >> 8) & 0xFF;
                int red = (image.getRGB(x, y) >> 16) & 0xFF;
                if(blue > Color.red.getBlue() - 15 && blue < Color.red.getBlue() + 15){
                    if(green > Color.red.getGreen() - 15 && green < Color.red.getGreen() + 15){
                        if(red > Color.red.getRed() - 15 && red < Color.red.getRed() +15){
                            robot.mouseMove(x,y);
                            break;
                        }
                    }
                }

                y++;
            }
                x++;
        }
        y++;
        x++;
       }





    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("ja");
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE){
            start();
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
