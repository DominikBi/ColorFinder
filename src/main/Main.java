package main;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Main implements NativeKeyListener {

    private Robot robot = new Robot();
    private Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

    public void setup() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    private Main() throws AWTException {}

    public static void main(String[] args) throws AWTException {
        Main main = new Main();
        main.setup();
    }

    private int getRGBDistance(Color c1, Color c2) {
        int diffRed = Math.abs(c1.getRed() - c2.getRed());
        int diffGreen = Math.abs(c1.getGreen() - c2.getGreen());
        int diffBlue = Math.abs(c1.getBlue() - c2.getBlue());
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

    @Override
    public void nativeKeyPressed(NativeKeyEvent event) {

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent event) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            start();
        }
    }
}
