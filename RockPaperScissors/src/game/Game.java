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

    ArrayList<Entity> entities = new ArrayList<>();

    Random random = new Random(System.nanoTime());

    public Game(Frame pFrame, int numOfRocks, int numOfPapers, int numOfScissors) {
        super.addKeyListener(this);

        this.setupPanel(Color.WHITE, 1600, 900);

        pFrame.add(this);
        pFrame.pack();
        pFrame.setLocationRelativeTo(null);

        this.options = new Options(pFrame, this);

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

    public void setupEntities(int numOfRocks, int numOfPapers, int numOfScissors) {
        for (int i = 0; i < numOfRocks; i++) {
            this.entities.add(new Entity("ROCK", this.setLocation()));
        }
        for (int i = 0; i < numOfPapers; i++) {
            this.entities.add(new Entity("PAPER", this.setLocation()));
        }
        for (int i = 0; i < numOfScissors; i++) {
            this.entities.add(new Entity("SCISSORS", this.setLocation()));
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
                entity.setxDir(-1);
            }
            if (entity.getX() < 51) {
                entity.setxDir(1);
            }
            if (entity.getY() > 799) {
                entity.setyDir(-1);
            }
            if (entity.getY() < 51) {
                entity.setyDir(1);
            }
            entity.updateX();
            entity.updateY();
            for (Entity otherEntity : this.entities) {
                if (entity != otherEntity && entity.collidesWith(otherEntity)) {
                    entity.randomizeDirection();
                    this.core(entity, otherEntity);
                }
            }
        }
    }

    private void core(Entity entity1, Entity entity2) {
        if (entity1.getEntityType() == 'R' && entity2.getEntityType() == 'S') {
            entity2.setEntity("ROCK");
        } else if (entity1.getEntityType() == 'P' && entity2.getEntityType() == 'R') {
            entity2.setEntity("PAPER");
        } else if (entity1.getEntityType() == 'S' && entity2.getEntityType() == 'P') {
            entity2.setEntity("SCISSORS");
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.moveEntities();
        this.repaint();
    }

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
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
}