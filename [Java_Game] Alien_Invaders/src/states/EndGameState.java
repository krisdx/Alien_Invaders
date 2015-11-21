package states;

import game.GameEngine;

import java.awt.*;

public class EndGameState extends State {
    private static final int GAME_WINDOW_WIDTH = 1150;
    private static final int GAME_WINDOW_HEIGHT = 650;

    private String endGameMessage;

    public EndGameState(GameEngine gameEngine, String endGameMessage) {
        super(gameEngine);
        this.endGameMessage = endGameMessage;
    }

    @Override
    public void tick() {
        this.gameEngine.stop();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setFont(new Font("Arial", Font.BOLD, 55));
        graphics.setColor(Color.BLACK.darker());
        graphics.drawString(this.endGameMessage,
                (GAME_WINDOW_WIDTH / 2) - 150, GAME_WINDOW_HEIGHT / 2);
    }
}