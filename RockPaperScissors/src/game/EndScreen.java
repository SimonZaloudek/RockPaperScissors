package game;

import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;
import menu.panels.IPanel;
import menu.panels.MenuPanel;

import javax.swing.ImageIcon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class EndScreen implements IPanel {


    private final int width = 1600;

    private final Frame frame;
    private final Game game;
    private final int[] numOfObj;

    private final Button playButton = new Button(EButtons.PLAY, this, ((this.width / 2) - (120)), 535, 235, 75, "PLAY AGAIN", 0);
    private final Button helpButton = new Button(EButtons.MENU, this, ((this.width / 2) - (115)), 620, 225, 75, "MENU", 0);
    private final Button menuButton = new Button(EButtons.EXIT, this, ((this.width / 2) - (115)), 705, 225, 75, "EXIT", 0);

    public EndScreen(Frame frame, Game game, int[] numOfObj) {
        this.frame = frame;
        this.game = game;
        this.numOfObj = new int[]{numOfObj[0], numOfObj[1], numOfObj[2]};
    }


    public void drawEnd(Graphics2D g2d, String winner) {
        int x = ((this.width / 2) - (500 / 2));
        int height = 900;
        int y = ((height / 2) - (700 / 2));

        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(x, y / 2 + 50, 500, 700, 10, 10);

        g2d.setColor(Color.ORANGE);
        Stroke tmp = g2d.getStroke();
        g2d.setStroke(new BasicStroke(20));
        g2d.drawRoundRect(x, y / 2 + 50, 500, 700, 10, 10);
        g2d.setStroke(tmp);
        this.setupButtons();
        g2d.drawRoundRect(this.width / 2 - 80, 375, 150, 150, 0, 0);

        g2d.setFont(new Font("Arial Bold", Font.BOLD, 40));
        g2d.drawString("WINNER IS:", this.width / 2 - 115, 355);
        switch (winner) {
            case "ROCK" ->
                    g2d.drawImage(new ImageIcon(this.game.getSkinPaths()[0]).getImage(), this.width / 2 - 55, 400, null);
            case "PAPER" ->
                    g2d.drawImage(new ImageIcon(this.game.getSkinPaths()[2]).getImage(), this.width / 2 - 55, 400, null);
            case "SCISSORS" ->
                    g2d.drawImage(new ImageIcon(this.game.getSkinPaths()[4]).getImage(), this.width / 2 - 55, 400, null);
            default -> System.out.println("err: WINNER NOT FOUND!");
        }

        g2d.drawImage(new ImageIcon("assets/BUTTONS/rpsMainLogo.png").getImage(), x + 30, y / 2 + 50 + 40, 440, 140, null);
    }

    public void setupButtons() {
        this.game.add(this.playButton);
        this.game.add(this.helpButton);
        this.game.add(this.menuButton);
        this.playButton.repaint();
        this.helpButton.repaint();
        this.menuButton.repaint();
    }

    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case PLAY -> {
                this.frame.remove(this.game);
                this.frame.add(new Game(this.frame, this.numOfObj[0], this.numOfObj[1], this.numOfObj[2], this.game.getMapPath(), this.game.getSkinPaths()));
            }
            case MENU -> {
                this.frame.remove(this.game);
                this.frame.add(new MenuPanel(this.frame, this.game.getMapPath(), this.game.getSkinPaths()));
            }
            case EXIT -> System.exit(0);
        }
    }
}

