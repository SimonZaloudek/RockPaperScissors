package game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Entity {

    private int x;
    private int y;

    private static final Random random = new Random(System.nanoTime());
    int xDir;
    int yDir;

    Image image;

    public Entity(String entity, int x, int y) {
        switch (entity) {
            case "ROCK" -> this.image = new ImageIcon("assets/rock.png").getImage();
            case "PAPER" -> this.image = new ImageIcon("assets/paper.png").getImage();
            case "SCISSORS" -> this.image = new ImageIcon("assets/scissors.png").getImage();
            default -> System.out.println("Entity not listed!");
        }
        this.x = x;
        this.y = y;
        this.randomizeDirection();
    }

    public void randomizeDirection() {
        this.xDir = random.nextBoolean() ? 1 : -1;
        this.yDir = random.nextBoolean() ? 1 : -1;
    }

    public int getX() {
        return x;
    }

    public void updateX() {
        this.x = this.x + this.xDir;
    }

    public int getY() {
        return y;
    }

    public void updateY() {
        this.y = this.y + this.yDir;
    }

    public Image getImage() {
        return this.image;
    }

}
