package game;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private String title;
    private int width;
    private int height;

    private JFrame frame;
    private Canvas canvas;

    public GameWindow(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        initFrame();
        initCanvas();

        this.frame.add(this.canvas);
        this.frame.pack();
    }

    private void initCanvas() {
        this.canvas = new Canvas();

        this.canvas.setPreferredSize(new Dimension(this.getWidth(), this.height));
        this.canvas.setMaximumSize(new Dimension(this.getWidth(), this.height));
        this.canvas.setMinimumSize(new Dimension(this.getWidth(), this.height));

        this.canvas.setFocusable(false);
    }

    private void initFrame() {
        this.frame = new JFrame(this.title);

        this.frame.setVisible(true);
        this.frame.setSize(this.getWidth(), this.height);
        this.frame.setFocusable(true);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public int getWidth() {
        return width;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return this.frame;
    }
}