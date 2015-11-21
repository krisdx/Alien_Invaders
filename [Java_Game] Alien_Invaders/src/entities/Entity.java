package entities;

import contracts.Updatable;

import java.awt.*;

public abstract class Entity implements Updatable {
    private final int DEFAULT_VELOCITY = 3;

    protected int x, y;
    protected int width, height;
    private int velocity;

    private Rectangle boundingBox;

    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = DEFAULT_VELOCITY;

        this.boundingBox = new Rectangle(this.x, this.y, this.width, this.height);
    }

    public int getVelocity() {
        return this.velocity;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }
}