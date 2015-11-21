package input;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    public static boolean up, down, left, right, space;

    public InputHandler(JFrame frame) {
        frame.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent key) {
        int keyCode = key.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            up = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            space = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent key) {
        int keyCode = key.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            up = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            space = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // auto generated method by KeyListener interface...
    }
}