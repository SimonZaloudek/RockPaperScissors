package game;

import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;
import menu.panels.IPanel;
import menu.panels.MenuPanel;

import java.awt.*;

public class Options implements IPanel {

    private final int WIDTH = 1600;
    private final int HEIGHT = 900;

    private final Frame frame;
    private final Game game;
    private boolean isDrawn = false;

    Button playButton = new Button(EButtons.PLAY, this, ((this.WIDTH/2) - (95)), 310, 185, 65, "RESUME");
    Button helpButton = new Button(EButtons.MENU, this, ((this.WIDTH/2) - (90)), 440, 175, 65, "MENU");
    Button menuButton = new Button(EButtons.EXIT, this, ((this.WIDTH/2) - (90)), 570, 175, 65, "EXIT");

    public Options(Frame frame, Game game) {
        this.frame = frame;
        this.game = game;
    }

    public void optionsMenu() {
        Graphics2D g2d = (Graphics2D) this.game.getGraphics();
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

    public void drawPause(Graphics2D g2d) {
        int x = ((this.WIDTH/2) - (300/2));
        int y = ((this.HEIGHT/2) + (400/2));

        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(x, y / 2 - 50, 300, 400, 10, 10);

        g2d.setColor(Color.ORANGE);
        Stroke tmp = g2d.getStroke();
        g2d.setStroke(new BasicStroke(10));
        g2d.drawRoundRect(x, y / 2 - 50, 300, 400, 10, 10);
        g2d.setStroke(tmp);
    }

    public void setupButtons() {
        if (this.isDrawn) {
            this.game.add(playButton);
            this.game.add(helpButton);
            this.game.add(menuButton);
            this.playButton.repaint();
            this.helpButton.repaint();
            this.menuButton.repaint();
        } else {
            this.game.remove(playButton);
            this.game.remove(helpButton);
            this.game.remove(menuButton);
        }
    }

    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case PLAY -> {
                this.optionsMenu();
                game.timer.start();
                game.requestFocus();
            }
            case MENU -> {
                this.frame.remove(this.game);
                this.frame.add(new MenuPanel(this.frame, this.game.getMapPath()));
            }
            case EXIT -> System.exit(0);
        }
    }
}
