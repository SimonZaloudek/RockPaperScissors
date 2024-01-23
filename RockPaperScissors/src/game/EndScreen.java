package game;

import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;
import menu.panels.IPanel;
import menu.panels.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class EndScreen implements IPanel {


    private final int WIDTH = 1600;
    private final int HEIGHT = 900;

    private final Frame frame;
    private final Game game;

    Button helpButton = new Button(EButtons.MENU, this, ((this.WIDTH/2) - (115)), 540, 225, 75, "MENU");
    Button menuButton = new Button(EButtons.EXIT, this, ((this.WIDTH/2) - (115)), 670, 225, 75, "EXIT");

    public EndScreen(Frame frame, Game game) {
        this.frame = frame;
        this.game = game;
    }


    public void drawEnd(Graphics2D g2d, String winner) {
        int x = ((this.WIDTH/2) - (500/2));
        int y = ((this.HEIGHT/2) - (700/2));

        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(x, y / 2 + 50, 500, 700, 10, 10);

        g2d.setColor(Color.ORANGE);
        Stroke tmp = g2d.getStroke();
        g2d.setStroke(new BasicStroke(20));
        g2d.drawRoundRect(x, y / 2 + 50, 500, 700, 10, 10);
        g2d.setStroke(tmp);
        this.setupButtons();
        g2d.drawRoundRect(this.WIDTH / 2 - 80, 375, 150, 150, 0, 0);

        g2d.setFont(new Font("Arial Bold", Font.BOLD, 40));
        g2d.drawString("WINNER IS:", this.WIDTH / 2 - 115, 355);
        if (winner.equals("ROCK")) {
            g2d.drawImage(new ImageIcon("assets/menuRock.png").getImage(), this.WIDTH / 2 - 50, 400, null);
        } else if (winner.equals("PAPER")) {
            g2d.drawImage(new ImageIcon("assets/menuPaper.png").getImage(), this.WIDTH / 2 - 50, 400, null);
        } else if (winner.equals("SCISSORS")) {
            g2d.drawImage(new ImageIcon("assets/menuScissors.png").getImage(), this.WIDTH / 2 - 50, 400, null);
        } else {
            System.out.println("err: WINNER NOT FOUND!");
        }

        g2d.drawImage(new ImageIcon("assets/peto.png").getImage(), x + 40, y/2 + 50 + 40, 420, 150, null);
    }

    public void setupButtons() {
        this.game.add(helpButton);
        this.game.add(menuButton);;
        this.helpButton.repaint();
        this.menuButton.repaint();
    }

    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case MENU -> {
                this.frame.remove(this.game);
                this.frame.add(new MenuPanel(this.frame));
            }
            case EXIT -> System.exit(0);
        }
    }
}

