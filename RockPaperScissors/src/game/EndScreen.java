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

    private final Frame frame;
    private final Game game;
    private final int[] numOfObj;

    Button playButton = new Button(EButtons.PLAY, this, ((this.WIDTH/2) - (120)), 535, 235, 75, "PLAY AGAIN");
    Button helpButton = new Button(EButtons.MENU, this, ((this.WIDTH/2) - (115)), 620, 225, 75, "MENU");
    Button menuButton = new Button(EButtons.EXIT, this, ((this.WIDTH/2) - (115)), 705, 225, 75, "EXIT");

    public EndScreen(Frame frame, Game game, int[] numOfObj) {
        this.frame = frame;
        this.game = game;
        this.numOfObj = new int[]{numOfObj[0], numOfObj[1], numOfObj[2]};
    }


    public void drawEnd(Graphics2D g2d, String winner) {
        int x = ((this.WIDTH/2) - (500/2));
        int HEIGHT = 900;
        int y = ((HEIGHT /2) - (700/2));

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
        switch (winner) {
            case "ROCK" ->
                    g2d.drawImage(new ImageIcon("assets/menuRock.png").getImage(), this.WIDTH / 2 - 50, 400, null);
            case "PAPER" ->
                    g2d.drawImage(new ImageIcon("assets/menuPaper.png").getImage(), this.WIDTH / 2 - 50, 400, null);
            case "SCISSORS" ->
                    g2d.drawImage(new ImageIcon("assets/menuScissors.png").getImage(), this.WIDTH / 2 - 50, 400, null);
            default -> System.out.println("err: WINNER NOT FOUND!");
        }

        g2d.drawImage(new ImageIcon("assets/peto.png").getImage(), x + 40, y/2 + 50 + 40, 420, 150, null);
    }

    public void setupButtons() {
        this.game.add(playButton);
        this.game.add(helpButton);
        this.game.add(menuButton);
        this.playButton.repaint();
        this.helpButton.repaint();
        this.menuButton.repaint();
    }

    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case PLAY -> {
                this.frame.remove(this.game);
                this.frame.add(new Game(this.frame, this.numOfObj[0], this.numOfObj[1], this.numOfObj[2], this.game.getMapPath()));
            }
            case MENU -> {
                this.frame.remove(this.game);
                this.frame.add(new MenuPanel(this.frame, this.game.getMapPath()));
            }
            case EXIT -> System.exit(0);
        }
    }
}

