package menu.panels;

import game.Game;
import handlers.Panels;
import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;

import javax.swing.ImageIcon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Trieda ktora vykresluje panel pred zaciatkom hry, uzivatel si voli moznosti simulatora
public class PreGameMenu extends Panels implements KeyListener {

    //pocet vykreslenych objektov
    private int rocks = 1;
    private int scissors = 1;
    private int papers = 1;

    PreGameMenu(Frame pFrame, String mapPath, String[] skinPaths) {
        super(pFrame, mapPath, skinPaths);
        super.addKeyListener(this);

        //Zakladne nastavenie panelu
        super.setupPanel(600, 800);

        if (!super.isVisible()) {
            super.setVisible(true);
        }
    }

    //Dizajn panelu
    public void drawScreen(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(50, 210, 500, 445, 0, 0);

        g2d.setColor(Color.ORANGE);
        Stroke tmp = g2d.getStroke();
        g2d.setStroke(new BasicStroke(10));
        g2d.drawRoundRect(50, 210, 500, 445, 0, 0);
        g2d.setStroke(tmp);

        //Vykreslovanie spravnych skinov
        g2d.drawImage(new ImageIcon(super.getSkinPaths()[0]).getImage(), 80, 240, null);
        g2d.drawImage(new ImageIcon(super.getSkinPaths()[2]).getImage(), 80, 382, null);
        g2d.drawImage(new ImageIcon(super.getSkinPaths()[4]).getImage(), 80, 525, null);

        g2d.setFont(new Font("Arial Bold", Font.BOLD, 30));

        //Fix grafickej chyby pri dvojcifernom cisle
        int rX = 401;
        int pX = 401;
        int sX = 401;
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

        this.setupButtons();
        super.requestFocus();
    }

    public void setupButtons() {

        Button menuButton = new Button(EButtons.MENU, this, 30, 30, 540, 150, "assets/BUTTONS/rpsMainLogo.png", 1);
        this.add(menuButton);

        Button backButton = new Button(EButtons.BACK, this, 40, 695, 175, 65, "BACK", 0);
        this.add(backButton);

        Button startButton = new Button(EButtons.PLAY, this, 385, 695, 175, 65, "START", 0);
        this.add(startButton);

        Button arrowRightButton1 = new Button(EButtons.FR, this, 440, 260, 100, 58, "assets/BUTTONS/buttonR.png", 1);
        this.add(arrowRightButton1);
        arrowRightButton1.repaint();

        Button arrowLeftButton1 = new Button(EButtons.FL, this, 280, 260, 100, 58, "assets/BUTTONS/buttonL.png", 1);
        this.add(arrowLeftButton1);
        arrowLeftButton1.repaint();

        Button arrowRightButton2 = new Button(EButtons.SR, this, 440, 402, 100, 58, "assets/BUTTONS/buttonR.png", 1);
        this.add(arrowRightButton2);
        arrowRightButton2.repaint();

        Button arrowLeftButton2 = new Button(EButtons.SL, this, 280, 402, 100, 58, "assets/BUTTONS/buttonL.png", 1);
        this.add(arrowLeftButton2);
        arrowLeftButton2.repaint();

        Button arrowRightButton3 = new Button(EButtons.TR, this, 440, 545, 100, 58, "assets/BUTTONS/buttonR.png", 1);
        this.add(arrowRightButton3);
        arrowRightButton3.repaint();

        Button arrowLeftButton3 = new Button(EButtons.TL, this, 280, 545, 100, 58, "assets/BUTTONS/buttonL.png", 1);
        this.add(arrowLeftButton3);
        arrowLeftButton3.repaint();
    }

    //Pouzivanie roznych tlacidiel
    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case MENU, BACK -> {
                super.getFrame().remove(this);
                super.getFrame().add(new MenuPanel(super.getFrame(), super.getMapPath(), super.getSkinPaths()));
            }
            case PLAY -> {
                super.getFrame().remove(this);
                super.getFrame().add(new Game(super.getFrame(), new int[]{ this.rocks, this.papers, this.scissors }, super.getMapPath(), super.getSkinPaths()));
            }
            case FR -> {
                if (this.rocks < 30) {
                    this.rocks++;
                    this.repaint();
                }
            }
            case FL -> {
                if (this.rocks > 1) {
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
                if (this.papers > 1) {
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
                if (this.scissors > 1) {
                    this.scissors--;
                    this.repaint();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //Pouzitie tlacidla ESCAPE na zrychlene nastavenie maximalneho poctu simulovanych objektov
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

