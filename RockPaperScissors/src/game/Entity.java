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

    public Entity(String entity, int[] xy, int speed) {
        this.setEntity(entity);
        this.x = xy[0];
        this.y = xy[1];
        this.randomizeDirection(speed);
    }

    public void updateSpeed(int speed) {
        if (this.xDir < 0) {
            xDir = -speed;
        }
        if (this.xDir > 0) {
            xDir = speed;
        }
        if (this.yDir < 0) {
            yDir = -speed;
        }
        if (this.yDir > 0) {
            yDir = speed;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 50, 50);
    }

    public boolean collidesWith(Entity anotherEntity) {
        return this.getBounds().intersects(anotherEntity.getBounds());
    }

    public void collisionHandler(Entity otherEntity) {
        int tempXDir = this.xDir;
        int tempYDir = this.yDir;

        this.setxDir(otherEntity.xDir);
        this.setyDir(otherEntity.yDir);

        otherEntity.setxDir(tempXDir);
        otherEntity.setyDir(tempYDir);
    }

    public void randomizeDirection(int speed) {
        this.xDir = random.nextBoolean() ? speed : -speed;
        this.yDir = random.nextBoolean() ? speed : -speed;
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
