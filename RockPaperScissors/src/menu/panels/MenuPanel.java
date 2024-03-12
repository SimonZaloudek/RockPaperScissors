package menu.panels;

import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


//Trieda MenuPanel, ktora tvori hlavne menu hry
public class MenuPanel extends JPanel implements IPanel {

    //Nastavuje a z parametra prijma frame na ktorom bezi panel
    private final Frame frame;
    //Uklada cestu k mape
    private String mapPath;
    //Uklada cestu k "skinom" pre k,p,s ktore sa vykresluju na hlavnej obrazovke
    private String[] skinPaths;
    public MenuPanel(Frame pFrame, String mapPath, String[] skinPaths) {
        this.frame = pFrame;
        this.mapPath = mapPath;
        //Nastavuje cestu k mape, v pripade startu programu (hodnota null) sa nastavi na defaultnu hodnotu.
        if (this.mapPath == null || this.mapPath.isEmpty()) {
            this.mapPath = "assets/ARENAS/mainArena.png";
        }
        //Nastavuje "skiny" k,p,s, ktore sa v pripade startu programu(hodnota null) nastavia na defaultne.
        this.skinPaths = skinPaths;
        if (this.skinPaths == null) {
            this.skinPaths = new String[]{"assets/RPS/menuRock.png", "assets/RPS/rock.png", "assets/RPS/menuPaper.png", "assets/RPS/paper.png", "assets/RPS/menuScissors.png", "assets/RPS/scissors.png"};
        }

        //Nastavenie panelu
        this.setupPanel(Color.BLACK, 600, 800);
        //Nastavenie buttonov
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

    public void setupButtons() {
        Button menuButton = new Button(EButtons.MENU, this, 30, 30, 540, 150, "assets/BUTTONS/rpsMainLogo.png", 1);
        this.add(menuButton);

        Button playButton = new Button(EButtons.PLAY, this, 40, 235, 175, 75, "PLAY", 0);
        this.add(playButton);

        Button helpButton = new Button(EButtons.HELP, this, 40, 365, 175, 65, "HELP", 0);
        this.add(helpButton);

        Button optionsButton = new Button(EButtons.OPTIONS, this, 40, 495, 175, 65, "OPTIONS", 0);
        this.add(optionsButton);

        Button exitButton = new Button(EButtons.EXIT, this, 40, 680, 175, 65, "EXIT", 0);
        this.add(exitButton);
    }

    //Automaticky sa volajuca metoda pri starte programu
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;

        //vykreslenie obrazkov
        this.menuDesign(g2d);
    }

    public void menuDesign(Graphics2D g2d) {
        g2d.drawImage(new ImageIcon("assets/BUTTONS/imageFrame.png").getImage(), 380, 235, 140, 140, null);
        g2d.drawImage(new ImageIcon("assets/BUTTONS/imageFrame.png").getImage(), 380, 420, 140, 140, null);
        g2d.drawImage(new ImageIcon("assets/BUTTONS/imageFrame.png").getImage(), 380, 605, 140, 140, null);

        g2d.drawImage(new ImageIcon(this.skinPaths[0]).getImage(), 400, 255, 100, 100, null);
        g2d.drawImage(new ImageIcon(this.skinPaths[2]).getImage(), 400, 440, 100, 100, null);
        g2d.drawImage(new ImageIcon(this.skinPaths[4]).getImage(), 400, 625, 100, 100, null);
    }

    //Funkcie jednotlivych tlacidiel
    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case PLAY -> {
                this.frame.remove(this);
                this.frame.add(new PreGameMenu(this.frame, this.mapPath, this.skinPaths));
            }
            case HELP -> {
                this.frame.remove(this);
                this.frame.add(new HelpPanel(this.frame, this.mapPath, this.skinPaths));
            }
            case OPTIONS -> {
                this.frame.remove(this);
                this.frame.add(new OptionsPanel(this.frame));
            }
            case EXIT -> System.exit(0);
        }
    }
}
