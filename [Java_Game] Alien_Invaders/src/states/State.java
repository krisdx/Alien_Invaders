package states;

import contracts.Updatable;
import game.GameEngine;

public abstract class State implements Updatable {
    protected GameEngine gameEngine;

    protected State(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
}