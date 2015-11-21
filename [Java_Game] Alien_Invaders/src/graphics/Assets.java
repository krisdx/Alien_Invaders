package graphics;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage player;
    public static BufferedImage playerBullet;

    public static BufferedImage enemy;
    public static BufferedImage enemyBullet;

    public static BufferedImage backgroundImage;

    public static void init() {
        player = ImageLoader.loadImage("/images/Player.png");
        playerBullet = ImageLoader.loadImage("/images/Player_Bullet.png");

        enemy = ImageLoader.loadImage("/images/Enemy.png");
        enemyBullet = ImageLoader.loadImage("/images/Enemy_Bullet.png");

        backgroundImage = ImageLoader.loadImage("/images/Background_Image.jpg");
    }
}