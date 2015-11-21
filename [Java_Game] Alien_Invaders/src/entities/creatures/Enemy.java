package entities.creatures;

import contracts.Shooting;
import entities.bullets.EnemyBullet;
import graphics.Assets;

import java.awt.*;
import java.util.*;

public class Enemy extends Creature implements Shooting {
    private final int ENEMY_BULLET_WIDTH = 50;
    private final int ENEMY_BULLET_HEIGHT = 50;

    private final int ENEMY_DAMAGE = 5;
    private final int ENEMY_LOW_HEALTH = 30;

    private int velocityModifier;

    private ArrayList<EnemyBullet> enemyBullets;

    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height);

        setCreatureDamage(ENEMY_DAMAGE);
        this.setVelocityModifier(1);

        this.enemyBullets = new ArrayList<>();
    }

    @Override
    public void tick() {
        this.x += this.getVelocity() * this.getVelocityModifier();
        this.getBoundingBox().setBounds(this.x + 7, this.y + 7, this.width + 10, this.height - 17);

        this.getEnemyBullets().forEach(entities.bullets.EnemyBullet::tick);
    }

    @Override
    public boolean intersects(Rectangle rectangle) {
        return this.getBoundingBox().contains(rectangle);
    }

    @Override
    public void shoot() {
        this.getEnemyBullets().add(new EnemyBullet(this.x + 35, this.y + 45,
                ENEMY_BULLET_WIDTH, ENEMY_BULLET_HEIGHT));
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.enemy, this.x, this.y, null);

        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        if (this.getHealth() < ENEMY_LOW_HEALTH) {
            graphics.setColor(Color.RED.darker());
        } else {
            graphics.setColor(Color.RED);
        }
        graphics.drawString("Health: " + this.getHealth(), this.x, this.y);

        for (EnemyBullet enemyBullet : this.getEnemyBullets()) {
            enemyBullet.render(graphics);
        }

        //// Bounding box of enemy
        //graphics.drawRect((int) this.getBoundingBox().getX(), (int) this.getBoundingBox().getY(), (int) this.getBoundingBox().getWidth(), (int) this.getBoundingBox().getHeight());
    }

    public int getVelocityModifier() {
        return velocityModifier;
    }

    public void setVelocityModifier(int velocityModifier) {
        this.velocityModifier = velocityModifier;
    }

    public ArrayList<EnemyBullet> getEnemyBullets() {
        return enemyBullets;
    }
}