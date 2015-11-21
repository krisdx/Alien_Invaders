package game;

/**
 * Created by Kristiyan Duba on 16.11.2015
 */

public class Launcher {
    public static final int GAME_WINDOW_WIDTH = 1150;
    public static final int GAME_WINDOW_HEIGHT = 650;

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine("Alien Invaders",
                GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
        gameEngine.start();
    }
}