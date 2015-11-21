package entities.creatures;

import contracts.Interceptable;
import contracts.Shooting;
import entities.Entity;

public abstract class Creature extends Entity implements Interceptable, Shooting {
    private final int DEFAULT_HEALTH = 100;

    private int health;
    private int creatureDamage;

    public Creature(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.setHealth(DEFAULT_HEALTH);
    }

    public int getCreatureDamage() {
        return creatureDamage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCreatureDamage(int creatureDamage) {
        this.creatureDamage = creatureDamage;
    }
}