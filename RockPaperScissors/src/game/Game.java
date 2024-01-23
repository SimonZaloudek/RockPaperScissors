package game;

import menu.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements KeyListener, ActionListener {

    Timer timer;
    Options options;
    EndScreen endScreen;

    String winner;
    int speed = 1;

    ArrayList<Entity> entities = new ArrayList<>();

    Random random = new Random(System.nanoTime());

    public Game(Frame pFrame, int numOfRocks, int numOfPapers, int numOfScissors) {
        super.addKeyListener(this);

        this.setupPanel(Color.WHITE, 1600, 900);

        pFrame.add(this);
        pFrame.pack();
        pFrame.setLocationRelativeTo(null);

        this.options = new Options(pFrame, this);
        this.endScreen = new EndScreen(pFrame, this);

        if (!this.isVisible()) {
            this.setVisible(true);
        }
        this.requestFocus();

        this.timer = new Timer(0, this);
        this.timer.start();

        this.setupEntities(numOfRocks, numOfPapers, numOfScissors);
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
            this.entities.add(new Entity("ROCK", this.setLocation(), this.speed));
        }
        for (int i = 0; i < numOfPapers; i++) {
            this.entities.add(new Entity("PAPER", this.setLocation(), this.speed));
        }
        for (int i = 0; i < numOfScissors; i++) {
            this.entities.add(new Entity("SCISSORS", this.setLocation(), this.speed));
        }
    }

    public int[] setLocation() {
        int WIDTH = 1600;
        int HEIGHT = 900;
        int[] xy = { random.nextInt((WIDTH - 105) - 60) + 60 , random.nextInt((HEIGHT - 105) - 60) + 60 };
        while (collisionForEntity(xy[0], xy[1])) {
            xy[0] = random.nextInt((WIDTH - 105) - 60) + 60;
            xy[1] = random.nextInt((HEIGHT - 105) - 60) + 60;
        }
        return xy;
    }

    public boolean collisionForEntity(int x, int y) {
        for (Entity entity : entities) {
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
        g2d.drawImage(new ImageIcon("assets/gamePanelMain.png").getImage(),0,0, 1600, 900, null);

        for (Entity entity : this.entities) {
            g2d.drawImage(entity.getImage(), entity.getX(), entity.getY(), null);
        }
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial Bold", Font.BOLD, 25));
        g2d.drawString(("SPEED: " + this.speed), 750, 885);
    }

    //GameLoop and Win Conditions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!this.winCondition()) {
            this.moveEntities();
            this.repaint();
        } else {
            timer.stop();
            this.endScreen.drawEnd((Graphics2D) this.getGraphics(), this.winner);
        }
    }

    private boolean winCondition() {
        if (areAllRock()) {
            this.winner = "ROCK";
            return true;
        }
        if (areAllPaper()) {
            this.winner = "PAPER";
            return true;
        }
        if (areAllScissors()) {
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
        if(code == KeyEvent.VK_ESCAPE) {
            if (this.timer.isRunning()) {
                this.options.optionsMenu();
                this.timer.stop();
            } else {
                this.options.optionsMenu();
                this.timer.start();
            }
        }
        if (code == KeyEvent.VK_UP) {
            this.speed++;
        }
        if (code == KeyEvent.VK_DOWN) {
            this.speed--;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
}