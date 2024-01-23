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

    char entityType;

    Image image;

    public Entity(String entity, int[] xy) {
        this.setEntity(entity);
        this.x = xy[0];
        this.y = xy[1];
        this.randomizeDirection();
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 50, 50);
    }

    public boolean collidesWith(Entity anotherEntity) {
        return this.getBounds().intersects(anotherEntity.getBounds());
    }

    public void randomizeDirection() {
        this.xDir = random.nextBoolean() ? 1 : -1;
        this.yDir = random.nextBoolean() ? 1 : -1;
    }

    public void setxDir(int xDir) {
        this.xDir = xDir;
    }

    public void setyDir(int yDir) {
        this.yDir = yDir;
    }

    public void setEntity(String entity) {
        switch (entity) {
            case "ROCK" -> {
                this.image = new ImageIcon("assets/rock.png").getImage();
                this.entityType = 'R';
            }
            case "PAPER" -> {
                this.image = new ImageIcon("assets/paper.png").getImage();
                this.entityType = 'P';
            }
            case "SCISSORS" -> {
                this.image = new ImageIcon("assets/scissors.png").getImage();
                this.entityType = 'S';
            }
            default -> System.out.println("Entity not listed!");
        }
    }

    public char getEntityType() {
        return entityType;
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
