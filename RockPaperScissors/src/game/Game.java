package game;

import menu.Frame;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements KeyListener, ActionListener {

    private final Timer timer;
    private final Options options;
    private final EndScreen endScreen;
    private final String mapPath;
    private final String[] skinPaths;

    private String winner;
    private int speed = 1;
    private final float startTime;

    private double elapsedTime;

    private final ArrayList<Entity> entities = new ArrayList<>();

    private final Random random = new Random(System.nanoTime());

    public Game(Frame pFrame, int numOfRocks, int numOfPapers, int numOfScissors, String mapPath, String[] skinPaths) {
        super.addKeyListener(this);
        this.startTime = System.nanoTime();

        this.mapPath = mapPath;
        this.skinPaths = skinPaths;
        this.setupPanel(Color.WHITE, 1600, 900);

        pFrame.add(this);
        pFrame.pack();
        pFrame.setLocationRelativeTo(null);

        int[] numOfObj = new int[]{numOfRocks, numOfPapers, numOfScissors};
        this.options = new Options(pFrame, this);
        this.endScreen = new EndScreen(pFrame, this, numOfObj);

        if (!this.isVisible()) {
            this.setVisible(true);
        }
        this.requestFocus();

        this.timer = new Timer(0, this);
        this.timer.start();
        this.setupEntities(numOfObj[0], numOfObj[1], numOfObj[2]);

    }

    public void setupPanel(Color color, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(color);
        this.setLayout(null);
        this.setVisible(true);
    }


    //Spawn, Movement and Collisions
    public void setupEntities(int numOfRocks, int numOfPapers, int numOfScissors) {
        for (int i = 0; i < numOfRocks; i++) {
            this.entities.add(new Entity("ROCK", this.setLocation(), this.speed, this.skinPaths));
        }
        for (int i = 0; i < numOfPapers; i++) {
            this.entities.add(new Entity("PAPER", this.setLocation(), this.speed, this.skinPaths));
        }
        for (int i = 0; i < numOfScissors; i++) {
            this.entities.add(new Entity("SCISSORS", this.setLocation(), this.speed, this.skinPaths));
        }
    }

    public int[] setLocation() {
        int width = 1600;
        int height = 900;
        int[] xy = { this.random.nextInt((width - 105) - 60) + 60 , this.random.nextInt((height - 105) - 60) + 60 };
        while (this.collisionForEntity(xy[0], xy[1])) {
            xy[0] = this.random.nextInt((width - 105) - 60) + 60;
            xy[1] = this.random.nextInt((height - 105) - 60) + 60;
        }
        return xy;
    }

    public boolean collisionForEntity(int x, int y) {
        for (Entity entity : this.entities) {
            if (Math.abs(x - entity.getX()) < 50 && Math.abs(y - entity.getY()) < 50) {
                return true;
            }
        }
        return false;
    }

    private void moveEntities() {
        for (Entity entity : this.entities) {
            if (entity.getX() > 1499) {
                entity.setxDir(-this.speed);
            }
            if (entity.getX() < 51) {
                entity.setxDir(this.speed);
            }
            if (entity.getY() > 799) {
                entity.setyDir(-this.speed);
            }
            if (entity.getY() < 51) {
                entity.setyDir(this.speed);
            }
            entity.updateSpeed(this.speed);
            entity.updateX();
            entity.updateY();
            for (Entity otherEntity : this.entities) {
                if (entity != otherEntity && entity.collidesWith(otherEntity)) {
                    this.core(entity, otherEntity);
                }
            }
        }
    }

    //Core of the GAME
    private void core(Entity entity1, Entity entity2) {
        if (entity1.getEntityType() == 'R' && entity2.getEntityType() == 'S') {
            entity2.setEntity("ROCK");
            entity1.collisionHandler(entity2);
        } else if (entity1.getEntityType() == 'P' && entity2.getEntityType() == 'R') {
            entity2.setEntity("PAPER");
            entity1.collisionHandler(entity2);
        } else if (entity1.getEntityType() == 'S' && entity2.getEntityType() == 'P') {
            entity2.setEntity("SCISSORS");
            entity1.collisionHandler(entity2);
        }
    }


    //PaintSegment
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        this.drawGame(g2d);
    }

    private void drawGame(Graphics2D g2d) {
        g2d.drawImage(new ImageIcon(this.mapPath).getImage(), 0, 0, 1600, 900, null);

        for (Entity entity : this.entities) {
            g2d.drawImage(entity.getImage(), entity.getX(), entity.getY(), null);
        }
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial Bold", Font.BOLD, 25));
        g2d.drawString(("SPEED: " + this.speed), 750, 885);
        g2d.drawImage(new ImageIcon("assets/BUTTONS/downArr.png").getImage(), 700, 858, 35, 35, null);
        if (this.speed > 9) {
            g2d.drawImage(new ImageIcon("assets/BUTTONS/upArr.png").getImage(), 890, 858, 35, 35, null);
        } else {
            g2d.drawImage(new ImageIcon("assets/BUTTONS/upArr.png").getImage(), 880, 858, 35, 35, null);
        }
    }

    //GameLoop and Win Conditions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!this.winCondition()) {
            this.moveEntities();
            this.repaint();
        } else {
            this.timer.stop();
            float endTime = System.nanoTime();
            this.elapsedTime = ((double)(endTime - this.startTime) / 1000000000);
            this.endScreen.drawEnd((Graphics2D)this.getGraphics(), this.winner);
        }
    }

    private boolean winCondition() {
        if (this.areAllRock()) {
            this.winner = "ROCK";
            return true;
        }
        if (this.areAllPaper()) {
            this.winner = "PAPER";
            return true;
        }
        if (this.areAllScissors()) {
            this.winner = "SCISSORS";
            return true;
        }
        return false;
    }

    private boolean areAllRock() {
        for (Entity entity : this.entities) {
            if (entity.getEntityType() == 'P' || entity.getEntityType() == 'S') {
                return false;
            }
        }
        return true;
    }
    private boolean areAllPaper() {
        for (Entity entity : this.entities) {
            if (entity.getEntityType() == 'R' || entity.getEntityType() == 'S') {
                return false;
            }
        }
        return true;
    }
    private boolean areAllScissors() {
        for (Entity entity : this.entities) {
            if (entity.getEntityType() == 'P' || entity.getEntityType() == 'R') {
                return false;
            }
        }
        return true;
    }


    //KeyEvents
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE) {
            if (this.timer.isRunning()) {
                this.options.optionsMenu();
                this.timer.stop();
            } else {
                this.options.optionsMenu();
                this.timer.start();
            }
        }
        if (code == KeyEvent.VK_UP) {
            if (this.speed < 20) {
                this.speed++;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            if (this.speed > 1) {
                this.speed--;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    public String getMapPath() {
        return this.mapPath;
    }

    public String[] getSkinPaths() {
        return this.skinPaths;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public double getElapsedTime() {
        return this.elapsedTime;
    }
}