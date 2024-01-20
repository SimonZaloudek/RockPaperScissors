package menu.panels;

import game.Game;
import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PreGameMenu extends JPanel implements IPanel, KeyListener {

    Frame frame;

    private int rocks = 0;
    private int scissors = 0;
    private int papers = 0;

    PreGameMenu(Frame pFrame) {
        this.frame = pFrame;
        super.addKeyListener(this);

        this.setupPanel(Color.BLACK, 600, 800);
        this.setupButtons();

        this.frame.add(this);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);

        if (!this.isVisible()) {
            this.setVisible(true);
        }
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
        this.drawPanel(g2d);
        this.setupButtons();
        super.requestFocus();

    }

    public void drawPanel(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(50,210,500, 445, 0, 0);

        g2d.setColor(Color.ORANGE);
        Stroke tmp = g2d.getStroke();
        g2d.setStroke(new BasicStroke(10));
        g2d.drawRoundRect(50,210,500, 445, 0, 0);
        g2d.setStroke(tmp);

        g2d.drawImage(new ImageIcon("assets/menuRock.png").getImage(), 80, 240, null);
        g2d.drawImage(new ImageIcon("assets/menuPaper.png").getImage(), 80, 382, null);
        g2d.drawImage(new ImageIcon("assets/menuScissors.png").getImage(), 80, 525, null);

        g2d.setFont(new Font("Arial Bold", Font.BOLD, 30));

        int rX = 401; int pX = 401; int sX = 401;
        if (this.rocks > 9) {
            rX = 395;
        }
        if (this.papers > 9) {
            pX = 395;
        }
        if (this.scissors > 9) {
            sX = 395;
        }
        g2d.drawString(Integer.toString(this.rocks), rX, 300);

        g2d.drawString(Integer.toString(this.papers), pX, 442);

        g2d.drawString(Integer.toString(this.scissors), sX, 585);
    }

    private void setupButtons() {

            Button menuButton = new Button(EButtons.MENU, this, 30, 30, 540, 150, 1);
            this.add(menuButton);

            Button backButton = new Button(EButtons.BACK, this, 40, 695, 175, 65, "BACK");
            this.add(backButton);

            Button startButton = new Button(EButtons.PLAY, this, 385, 695, 175, 65, "START");
            this.add(startButton);


            Button arrowRightButton1 = new Button(EButtons.FR, this, 440, 260, 100, 58, 3);
            this.add(arrowRightButton1);
            arrowRightButton1.repaint();

            Button arrowLeftButton1 = new Button(EButtons.FL, this, 280, 260, 100, 58, 2);
            this.add(arrowLeftButton1);
            arrowLeftButton1.repaint();


            Button arrowRightButton2 = new Button(EButtons.SR, this, 440, 402, 100, 58, 3);
            this.add(arrowRightButton2);
            arrowRightButton2.repaint();

            Button arrowLeftButton2 = new Button(EButtons.SL, this, 280, 402, 100, 58, 2);
            this.add(arrowLeftButton2);
            arrowLeftButton2.repaint();


            Button arrowRightButton3 = new Button(EButtons.TR, this, 440, 545, 100, 58, 3);
            this.add(arrowRightButton3);
            arrowRightButton3.repaint();

            Button arrowLeftButton3 = new Button(EButtons.TL, this, 280, 545, 100, 58, 2);
            this.add(arrowLeftButton3);
            arrowLeftButton3.repaint();
    }

    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case MENU, BACK -> {
                this.frame.remove(this);
                this.frame.add(new MenuPanel(this.frame));
            }
            case PLAY -> {
                this.frame.remove(this);
                this.frame.add(new Game(this.frame, this.rocks, this.papers, this.scissors));
            }
            case FR -> {
                if (this.rocks < 30) {
                    this.rocks++;
                    this.repaint();
                }
            }
            case FL -> {
                if (this.rocks > 0) {
                    this.rocks--;
                    this.repaint();
                }
            }
            case SR -> {
                if (this.papers < 30) {
                    this.papers++;
                    this.repaint();
                }
            }
            case SL -> {
                if (this.papers > 0) {
                    this.papers--;
                    this.repaint();
                }
            }
            case TR -> {
                if (this.scissors < 30) {
                    this.scissors++;
                    this.repaint();
                }
            }
            case TL -> {
                if (this.scissors > 0) {
                    this.scissors--;
                    this.repaint();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.papers = 30;
            this.scissors = 30;
            this.rocks = 30;
            this.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

