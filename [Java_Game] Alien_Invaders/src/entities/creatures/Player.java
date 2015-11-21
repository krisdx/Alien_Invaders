package entities.creatures;

import contracts.Shooting;
import entities.bullets.PlayerBullet;
import graphics.Assets;
import input.InputHandler;

import java.awt.*;
import java.util.*;

public class Player extends Creature implements Shooting {
    private final int DEFAULT_PLAYER_DAMAGE = 10;

    private final int PLAYER_BULLET_WIDTH = 100;
    private final int PLAYER_BULLET_HEIGHT = 100;

    private ArrayList<PlayerBullet> playerBullets;

    private boolean hasShot;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        setCreatureDamage(DEFAULT_PLAYER_DAMAGE);

        this.playerBullets = new ArrayList<>();
    }

    @Override
    public void tick() {
        if (InputHandler.up) {
            if (this.getBoundingBox().getY() - this.getVelocity() >= 0) {
                this.y -= this.getVelocity();
            }
        } else if (InputHandler.down) {
            if (this.getBoundingBox().getY() + this.getBoundingBox().getHeight() + this.getVelocity() <= 650) {
                this.y += this.getVelocity();
            }
        }

        if (InputHandler.left) {
            if (this.getBoundingBox().getX() - this.getVelocity() >= 0) {
                this.x -= this.getVelocity();
            }
        } else if (InputHandler.right) {
            if (this.getBoundingBox().getX() + this.getBoundingBox().getWidth() + this.getVelocity() <= 1150) {
                this.x += this.getVelocity();
            }
        }

        this.getBoundingBox().setBounds(this.x + 26, this.y + 1, this.width - 24, this.height + 29);

        if (InputHandler.space && !hasShot) {
            shoot();
            hasShot = true;
        } else if (!InputHandler.space) {
            hasShot = false;
        }

        this.getPlayerBullets().forEach(entities.bullets.PlayerBullet::tick);
    }

    @Override
    public boolean intersects(Rectangle rectangle) {
        return this.getBoundingBox().contains(rectangle);
    }

    @Override
    public void shoot() {
        this.getPlayerBullets().add(new PlayerBullet(this.x + 13, this.y - 35,
                PLAYER_BULLET_WIDTH, PLAYER_BULLET_HEIGHT));
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.player, this.x, this.y, null);

        for (PlayerBullet playerBullet : this.getPlayerBullets()) {
            playerBullet.render(graphics);
        }

        //// Bounding box of player
        //graphics.drawRect((int) this.getBoundingBox().getX(), (int) this.getBoundingBox().getY(), (int) this.getBoundingBox().getWidth(), (int) this.getBoundingBox().getHeight());
    }

    public ArrayList<PlayerBullet> getPlayerBullets() {
        return playerBullets;
    }
}