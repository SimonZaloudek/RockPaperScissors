package game;

import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;
import menu.panels.IPanel;
import menu.panels.MenuPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

//Trieda ktora implementuje a nastavuje pauzu v hre
public class Options implements IPanel {



    private final Frame frame;
    private final Game game;
    private boolean isDrawn = false;

    //Nastavenia tlacidiel
    private Button playButton;
    private Button helpButton;
    private Button menuButton;

    public Options(Frame frame, Game game) {
        this.frame = frame;
        this.game = game;
        this.playButton = new Button(EButtons.PLAY, this, ((this.frame.getScreenWidth() / 2) - (95)), 310, 185, 65, "RESUME", 0);
        this.helpButton= new Button(EButtons.MENU, this, ((this.frame.getScreenWidth() / 2) - (90)), 440, 175, 65, "MENU", 0);
        this.menuButton= new Button(EButtons.EXIT, this, ((this.frame.getScreenWidth() / 2) - (90)), 570, 175, 65, "EXIT", 0);
    }

    public void optionsMenu() {
        Graphics2D g2d = (Graphics2D)this.game.getGraphics();
        if (!this.isDrawn) {
            this.isDrawn = true;
            this.drawPause(g2d);
            this.setupButtons();
        } else {
            this.isDrawn = false;
            this.setupButtons();
        }
        g2d.dispose();
    }

    //Dizajn
    public void drawPause(Graphics2D g2d) {
        int x = ((this.frame.getScreenWidth() / 2) - (300 / 2));
        int height = 900;
        int y = ((height / 2) + (400 / 2));

        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(x, y / 2 - 50, 300, 400, 10, 10);

        g2d.setColor(Color.ORANGE);
        Stroke tmp = g2d.getStroke();
        g2d.setStroke(new BasicStroke(10));
        g2d.drawRoundRect(x, y / 2 - 50, 300, 400, 10, 10);
        g2d.setStroke(tmp);
    }

    //Prida tlacidla na panel hry, v pripade ze su vykreslene ich odstrani
    public void setupButtons() {
        if (this.isDrawn) {
            this.game.add(this.playButton);
            this.game.add(this.helpButton);
            this.game.add(this.menuButton);
            this.playButton.repaint();
            this.helpButton.repaint();
            this.menuButton.repaint();
        } else {
            this.game.remove(this.playButton);
            this.game.remove(this.helpButton);
            this.game.remove(this.menuButton);
        }
    }

    //Funkcie jednotlivych tlacidiel
    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case PLAY -> {
                this.optionsMenu();
                this.game.getTimer().start();
                this.game.requestFocus();
            }
            case MENU -> {
                this.frame.remove(this.game);
                this.frame.add(new MenuPanel(this.frame, this.game.getMapPath(), this.game.getSkinPaths()));
            }
            case EXIT -> System.exit(0);
        }
    }
}
