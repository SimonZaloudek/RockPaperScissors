package game;

import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;
import menu.panels.HelpPanel;
import menu.panels.IPanel;
import menu.panels.MenuPanel;
import menu.panels.PreGameMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener, IPanel {

    private final Frame frame;
    private final int WIDTH = 1600;
    private final int HEIGHT = 900;
    private boolean isDrawn = false;

    Button playButton = new Button(EButtons.PLAY, this, ((this.WIDTH/2) - (95)), 310, 185, 65, "RESUME");
    Button helpButton = new Button(EButtons.MENU, this, ((this.WIDTH/2) - (90)), 440, 175, 65, "MENU");
    Button menuButton = new Button(EButtons.EXIT, this, ((this.WIDTH/2) - (90)), 570, 175, 65, "EXIT");

    public Game(Frame pFrame) {
        this.frame = pFrame;
        super.addKeyListener(this);

        this.setupPanel(Color.WHITE, 1600, 900);

        this.frame.add(this);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);

        if (!this.isVisible()) {
            this.setVisible(true);
        }
        this.requestFocus();
    }

    public void setupPanel(Color color, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(color);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        this.drawGame(g2d);
    }

    private void drawGame(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0,0,this.WIDTH, this.HEIGHT, 0, 0);
    }

    public void drawPause(Graphics2D g2d) {
        int width = 300;
        int height = 400;

        int x = ((this.WIDTH/2) - (width/2));
        int y = ((this.HEIGHT/2) + (height/2));

        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(x, y / 2 - 50, width, height, 10, 10);

        g2d.setColor(Color.ORANGE);
        Stroke tmp = g2d.getStroke();
        g2d.setStroke(new BasicStroke(10));
        g2d.drawRoundRect(x, y / 2 - 50, width, height, 10, 10);
        g2d.setStroke(tmp);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_ESCAPE) {
            this.optionsMenu();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case PLAY -> {
                this.optionsMenu();
                this.requestFocus();
            }
            case MENU -> {
                this.frame.remove(this);
                this.frame.add(new MenuPanel(this.frame));
            }
            case EXIT -> System.exit(0);
        }
    }

    public void setupButtons() {

        if (this.isDrawn) {
            this.add(playButton);
            this.add(helpButton);
            this.add(menuButton);
            this.playButton.repaint();
            this.helpButton.repaint();
            this.menuButton.repaint();
        } else {
            this.remove(playButton);
            this.remove(helpButton);
            this.remove(menuButton);
        }
    }

    public void optionsMenu() {
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        if (!this.isDrawn) {
            this.isDrawn = true;
            this.drawPause(g2d);
            this.setupButtons();
        } else {
            this.isDrawn = false;
            this.setupButtons();
            this.paint(this.getGraphics());
        }
        g2d.dispose();
    }
}