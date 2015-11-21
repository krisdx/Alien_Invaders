package entities.bullets;

import contracts.Interceptable;
import entities.Entity;
import graphics.Assets;

import java.awt.*;

public class EnemyBullet extends Entity implements Interceptable {
    private static final int ENEMY_BULLET_WIDTH = 17;
    private static final int ENEMY_BULLET_HEIGHT = 33;

    public EnemyBullet(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public boolean intersects(Rectangle rectangle) {
        return this.getBoundingBox().intersects(rectangle);
    }

    @Override
    public void tick() {
        this.y += this.getVelocity();
        this.getBoundingBox().setBounds(this.x + 17, this.y + 7,
                ENEMY_BULLET_WIDTH, ENEMY_BULLET_HEIGHT);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.enemyBullet, this.x, this.y, this.width, this.height, null);

        //// Bounding box of the enemy bullet
        //graphics.drawRect((int) this.getBoundingBox().getX(), (int) this.getBoundingBox().getY(), (int) this.getBoundingBox().getWidth(), (int) this.getBoundingBox().getHeight());
    }
}