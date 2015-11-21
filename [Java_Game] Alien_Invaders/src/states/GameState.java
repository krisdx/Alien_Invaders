package states;

import entities.bullets.EnemyBullet;
import entities.creatures.Enemy;
import entities.creatures.Player;
import game.GameEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState extends State {
    private final int GAME_WINDOW_WIDTH = 1150;
    private final int GAME_WINDOW_HEIGHT = 650;

    private final int NUMBER_OF_ENEMIES = 5;

    private Player player;
    private List<Enemy> enemies;

    private int shotsPerTick;
    private double timePerShoot;
    private long lastTimeShot;
    private double deltaBetweenShots;

    public GameState(GameEngine gameEngine) {
        super(gameEngine);
        this.player = new Player((GAME_WINDOW_WIDTH / 2) - 100, GAME_WINDOW_HEIGHT - 150, 100, 100);

        this.spawnEnemies();

        this.shotsPerTick = 5;
        this.timePerShoot = 1000000000.0 / this.shotsPerTick;
        this.lastTimeShot = System.nanoTime();
        this.deltaBetweenShots = 0;
    }

    private void spawnEnemies() {
        this.enemies = new ArrayList<>();

        int spaceBetweenEnemies = 0;
        for (int i = 0; i < NUMBER_OF_ENEMIES; i++) {
            this.enemies.add(new Enemy(20 + spaceBetweenEnemies, 20, 100, 100));
            spaceBetweenEnemies += 200;
        }
    }

    @Override
    public void tick() {
        this.player.tick();

        this.moveEnemies();

        this.enemyShoot();

        // Checking if the player intersects an enemy(he has to lose health)
        for (int i = 0; i < this.enemies.size(); i++) {
            if (this.player.getBoundingBox().intersects(this.enemies.get(i).getBoundingBox())) {
                this.player.setHealth(this.player.getHealth() - this.enemies.get(i).getCreatureDamage());
            }
        }

        // Intersection / Collision between player bullet and an enemy
        for (int i = 0; i < this.player.getPlayerBullets().size(); i++) {
            for (int j = 0; j < this.enemies.size(); j++) {
                if (this.player.getPlayerBullets().size() > i &&
                        this.player.getPlayerBullets().get(i).intersects(this.enemies.get(j).getBoundingBox())) {
                    this.enemies.get(j).setHealth(this.enemies.get(j).getHealth() - this.player.getCreatureDamage());

                    this.player.getPlayerBullets().remove(i);
                    if (this.player.getPlayerBullets().size() == 0) {
                        break;
                    }
                }
            }
        }

        // Intersection / Collision between enemy bullet and player
        for (int i = 0; i < this.enemies.size(); i++) {
            for (int j = 0; j < this.enemies.get(i).getEnemyBullets().size(); j++) {
                if (this.enemies.get(i).getEnemyBullets().get(j).intersects(this.player.getBoundingBox())) {
                    this.player.setHealth(this.player.getHealth() - this.enemies.get(i).getCreatureDamage());

                    this.enemies.get(i).getEnemyBullets().remove(j);
                    if (this.enemies.get(i).getEnemyBullets().size() == 0) {
                        break;
                    }
                }
            }
        }

        // Removing dead enemies
        for (int i = 0; i < this.enemies.size(); i++) {
            if (this.enemies.get(i).getHealth() <= 0) {
                this.enemies.remove(i);
            }
        }

        // Removing player bullets who are out of range(canvas)
        for (int i = 0; i < this.player.getPlayerBullets().size(); i++) {
            if (this.player.getPlayerBullets().get(i).getBoundingBox().getY() <= 0) {
                this.player.getPlayerBullets().remove(i);
            }
        }

        // Removing enemy bullets who are out of range(canvas)
        for (int i = 0; i < this.enemies.size(); i++) {
            for (int j = 0; j < this.enemies.get(i).getEnemyBullets().size(); j++) {
                if (this.enemies.get(i).getEnemyBullets().get(j).getBoundingBox().getY() +
                        this.enemies.get(i).getEnemyBullets().get(j).getBoundingBox().getHeight() >= 650) {
                    this.enemies.get(i).getEnemyBullets().remove(j);
                    if (enemies.get(i).getEnemyBullets().size() == 0) {
                        break;
                    }
                }
            }
        }

        // Check if the game has to end
        if (this.enemies.size() == 0) {
            StateManager.setCurrentState(new EndGameState(this.gameEngine, "You Won!"));
        } else if (this.player.getHealth() <= 0) {
            StateManager.setCurrentState(new EndGameState(this.gameEngine, "Game Over!"));
        }
    }

    private void moveEnemies() {
        for (Enemy enemy : this.enemies) {
            enemy.tick();

            if (enemy.getBoundingBox().getX() + enemy.getBoundingBox().getWidth() >= GAME_WINDOW_WIDTH) {
                for (Enemy enemy1 : this.enemies) {
                    enemy1.setVelocityModifier(-1);
                    enemy1.tick();
                }
            } else if (enemy.getBoundingBox().getX() < 0) {
                for (Enemy enemy1 : this.enemies) {
                    enemy1.setVelocityModifier(1);
                    enemy1.tick();
                }
            }
        }

        // Second way of moving enemies
        /*for (Enemy enemy : this.enemies) {
            enemy.tick();

            if (enemy.getBoundingBox().getX() + enemy.getBoundingBox().getWidth() >= GAME_WINDOW_WIDTH) {
                enemy.setVelocityModifier(-1);
                this.enemies.forEach(entities.creatures.Enemy::tick);
            } else if (enemy.getBoundingBox().getX() < 0) {
                enemy.setVelocityModifier(1);
                this.enemies.forEach(entities.creatures.Enemy::tick);
            }
        }*/
    }

    private void enemyShoot() {
        long now = System.nanoTime();
        this.deltaBetweenShots += (now - this.lastTimeShot) / this.timePerShoot;
        this.lastTimeShot = now;

        if (this.deltaBetweenShots >= 1) {
            Random random = new Random();
            int enemyShooting = random.nextInt(this.enemies.size());

            this.enemies.get(enemyShooting).shoot();
            this.deltaBetweenShots--;
        }
    }

    @Override
    public void render(Graphics graphics) {
        this.player.render(graphics);

        for (Enemy enemy : this.enemies) {
            enemy.render(graphics);
        }

        for (Enemy enemy : this.enemies) {
            for (EnemyBullet bullet : enemy.getEnemyBullets()) {
                bullet.render(graphics);
            }
        }

        graphics.setFont(new Font("Arial", Font.BOLD, 22));
        if (this.player.getHealth() <= 30) {
            graphics.setColor(Color.red);
        } else {
            graphics.setColor(Color.white);
        }
        graphics.drawString("Your health is: " + this.player.getHealth(), 5, 630);
    }
}