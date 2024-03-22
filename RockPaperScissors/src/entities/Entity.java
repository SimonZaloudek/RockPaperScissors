package entities;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

//Trieda ktora nastavuje entity kamenov, papierov a noznic
//
public class Entity {

    private int x;
    private int y;

    private final Random random = new Random(System.nanoTime());
    private int xDir;
    private int yDir;

    private char entityType;
    private final String[] skinPaths;

    private Image image;

    //Nastavenie zakladnych vlastnosti
    public Entity(String entity, int[] xy, int speed, String[] skinPaths) {
        this.skinPaths = skinPaths;
        this.setEntity(entity);
        this.x = xy[0];
        this.y = xy[1];
        //Vyber nahodneho smeru pri "spawne"
        this.randomizeDirection(speed);
    }

    //Metoda na aplikaciu zmenu rychlosti simulacie
    public void updateSpeed(int speed) {
        if (this.xDir < 0) {
            this.xDir = -speed;
        }
        if (this.xDir > 0) {
            this.xDir = speed;
        }
        if (this.yDir < 0) {
            this.yDir = -speed;
        }
        if (this.yDir > 0) {
            this.yDir = speed;
        }
    }

    //System kolizii
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 50, 50);
    }

    //System kolizii
    public boolean jeVKoliziiS(Entity anotherEntity) {
        return this.getBounds().intersects(anotherEntity.getBounds());
    }

    //Co sa stane v pripade kolizie -> vymenia sa smery danych objektov
    public void collisionHandler(Entity otherEntity) {
        int tempXDir = this.xDir;
        int tempYDir = this.yDir;

        this.setxDir(otherEntity.xDir);
        this.setyDir(otherEntity.yDir);

        otherEntity.setxDir(tempXDir);
        otherEntity.setyDir(tempYDir);
    }

    //Znahodnenie smeru
    public void randomizeDirection(int speed) {
        this.xDir = this.random.nextBoolean() ? speed : -speed;
        this.yDir = this.random.nextBoolean() ? speed : -speed;
    }

    public void setxDir(int xDir) {
        this.xDir = xDir;
    }

    public void setyDir(int yDir) {
        this.yDir = yDir;
    }

    //Nastavenie entity
    public void setEntity(String entity) {
        switch (entity) {
            case "ROCK" -> {
                this.image = new ImageIcon(this.skinPaths[1]).getImage();
                this.entityType = 'R';
            }
            case "PAPER" -> {
                this.image = new ImageIcon(this.skinPaths[3]).getImage();
                this.entityType = 'P';
            }
            case "SCISSORS" -> {
                this.image = new ImageIcon(this.skinPaths[5]).getImage();
                this.entityType = 'S';
            }
            default -> System.out.println("Entity not listed!");
        }
    }

    public char getEntityType() {
        return this.entityType;
    }

    public int getX() {
        return this.x;
    }

    public void updateX() {
        this.x = this.x + this.xDir;
    }

    public int getY() {
        return this.y;
    }

    public void updateY() {
        this.y = this.y + this.yDir;
    }

    public Image getImage() {
        return this.image;
    }
}
