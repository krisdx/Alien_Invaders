package game;

import contracts.Updatable;
import graphics.Assets;
import input.InputHandler;
import states.GameState;
import states.State;
import states.StateManager;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameEngine implements Updatable, Runnable {
    private String title;
    private int width, height;

    private Thread thread;

    private Graphics graphics;
    private BufferStrategy bufferStrategy;

    private GameWindow display;

    public static InputHandler inputHandler;

    private State currentState;

    private boolean isRunning;

    public GameEngine(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        init();

        while (isRunning) {
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tick();

            render(this.graphics);
        }

        stop();
    }

    private void init() {
        this.display = new GameWindow(this.title, this.width, this.height);

        this.display.getCanvas().createBufferStrategy(2);
        this.bufferStrategy = this.display.getCanvas().getBufferStrategy();
        this.graphics = this.bufferStrategy.getDrawGraphics();

        inputHandler = new InputHandler(this.display.getFrame());

        Assets.init();

        this.currentState = new GameState(this);
        StateManager.setCurrentState(this.currentState);
    }

    public synchronized void start() {
        if (this.thread == null) {
            this.thread = new Thread(this);
            this.thread.start();

            this.isRunning = true;
        }
    }

    public synchronized void stop() {
        this.isRunning = false;

        if (this.thread != null) {
            try {
                this.thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void tick() {
        if (StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().tick();
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics = this.bufferStrategy.getDrawGraphics();

        graphics.drawImage(Assets.backgroundImage, 0, 0, 1150, 650, null);

        if (StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().render(graphics);
        }

        this.bufferStrategy.show();
        graphics.dispose();
    }
}