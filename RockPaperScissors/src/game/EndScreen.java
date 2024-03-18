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
import java.text.DecimalFormat;

//Trieda ktora ukonci hru a vykresli na panel vysledok
public class EndScreen implements IPanel {
    private final Frame frame;
    private final Game game;
    private Stats statistics = new Stats();
    private final int[] numOfObj;

    private final Button menuButton;
    private final Button playButton;
    private final Button statsButton;
    private final Button exitButton;

    private boolean stats;
    private String winner;

    public EndScreen(Frame frame, Game game, int[] numOfObj) {
        this.frame = frame;
        this.game = game;
        this.numOfObj = new int[]{numOfObj[0], numOfObj[1], numOfObj[2]};
        this.menuButton = new Button(EButtons.MENU, this, ((this.frame.getScreenWidth() / 2 - 50) - (500 / 2) + 30), 120, 540, 150, "assets/BUTTONS/rpsMainLogo.png", 1);
        this.playButton = new Button(EButtons.PLAY, this, ((this.frame.getScreenWidth() / 2) - (120)), 535, 235, 75, "PLAY AGAIN", 0);
        this.statsButton = new Button(EButtons.OPTIONS, this, ((this.frame.getScreenWidth() / 2) - (115)), 620, 225, 75, "STATS", 0);
        this.exitButton = new Button(EButtons.EXIT, this, ((this.frame.getScreenWidth() / 2) - (115)), 705, 225, 75, "EXIT", 0);
    }


    //Dizajn a funkcie
    public void paintEndScreen(Graphics2D g2d, String pWinner, Stats pStatistics) {
        if (this.winner == null) {
            this.winner = pWinner;
        }
        this.statistics = pStatistics;

        this.drawEndScreen(g2d);
    }

    public void drawStats(Graphics2D g2d) {
        this.drawEndScreen(g2d);

        g2d.setFont(new Font("Arial", Font.PLAIN, 15));
        g2d.drawString("Total time: " + new DecimalFormat("0.0").format(this.game.getElapsedTime()) + " sec.", (this.frame.getScreenWidth() / 2 - 110), 560);
        g2d.drawString("Total touches: " + this.statistics.getTouches(), (this.frame.getScreenWidth() / 2 - 110), 585);
        g2d.drawString("Total conversions: ", (this.frame.getScreenWidth() / 2 - 110), 610);
        g2d.drawString("| Rock: " + this.statistics.getKills()[0] + " | | Paper: " + this.statistics.getKills()[1] + " | | Scissors: " + this.statistics.getKills()[2] + " |", (this.frame.getScreenWidth() / 2 - 110), 635);
        g2d.drawString("Total distance(in pixels): " + this.statistics.getDistanceTravelled(), (this.frame.getScreenWidth() / 2 - 110), 660);

    }

    private void drawEndScreen(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(510, 100, 580, 700, 10, 10);

        g2d.setColor(Color.ORANGE);
        Stroke tmp = g2d.getStroke();
        g2d.setStroke(new BasicStroke(20));
        g2d.drawRoundRect(510, 100, 580, 700, 10, 10);
        g2d.setStroke(tmp);
        this.setupButtons();
        g2d.drawRoundRect((this.frame.getScreenWidth() / 2 - 80), 375, 150, 150, 0, 0);

        g2d.setFont(new Font("Arial Bold", Font.BOLD, 40));
        g2d.drawString("WINNER IS:", this.frame.getScreenWidth() / 2 - 115, 355);
        switch (this.winner) {
            case "ROCK" ->
                    g2d.drawImage(new ImageIcon(this.game.getSkinPaths()[0]).getImage(), this.frame.getScreenWidth() / 2 - 55, 400, null);
            case "PAPER" ->
                    g2d.drawImage(new ImageIcon(this.game.getSkinPaths()[2]).getImage(), this.frame.getScreenWidth() / 2 - 55, 400, null);
            case "SCISSORS" ->
                    g2d.drawImage(new ImageIcon(this.game.getSkinPaths()[4]).getImage(), this.frame.getScreenWidth() / 2 - 55, 400, null);
            default -> System.out.println("err: WINNER NOT FOUND!");
        }
    }

    public void setupButtons() {
        if (!this.stats) {
            this.game.add(this.playButton);
            this.game.add(this.statsButton);
            this.game.add(this.exitButton);
            this.game.add(this.menuButton);
        } else {
            this.game.remove(this.playButton);
            this.game.remove(this.statsButton);
        }
        this.playButton.repaint();
        this.statsButton.repaint();
        this.exitButton.repaint();
        this.menuButton.repaint();
    }

    //Funkcie jednotlivych tlacidiel
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
            case OPTIONS -> {
                this.stats = true;
                this.exitButton.setText("BACK");
                this.setupButtons();
                this.drawStats((Graphics2D)this.game.getGraphics());
            }
            case EXIT -> {
                if (!this.stats) {
                    System.exit(0);
                } else {
                    this.stats = false;
                    this.exitButton.setText("EXIT");
                    this.drawEndScreen((Graphics2D)this.game.getGraphics());
                    this.setupButtons();
                }
            }
        }
    }
}

