package entities.bullets;

import contracts.Interceptable;
import entities.Entity;
import graphics.Assets;

import java.awt.*;

public class PlayerBullet extends Entity implements Interceptable {
    private final int PLAYER_BULLET_WIDTH = 28;
    private final int PLAYER_BULLET_HEIGHT = 50;

    public PlayerBullet(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {
        this.y -= this.getVelocity();
        this.getBoundingBox().setBounds(this.x + 35, this.y + 21,
                PLAYER_BULLET_WIDTH, PLAYER_BULLET_HEIGHT);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.playerBullet, this.x, this.y, this.width, this.height, null);

        //// Bounding box of the player bullet
        //graphics.drawRect((int) this.getBoundingBox().getX(), (int) this.getBoundingBox().getY(), (int) this.getBoundingBox().getWidth(), (int) this.getBoundingBox().getHeight());
    }

    @Override
    public boolean intersects(Rectangle rectangle) {
        return this.getBoundingBox().intersects(rectangle);
    }
}