package menu.panels;

import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;


//Trieda MenuPanel, ktora tvori hlavne menu hry
public class MenuPanel extends Panels {

    public MenuPanel(Frame pFrame, String mapPath, String[] skinPaths) {
        super(pFrame, mapPath, skinPaths);

        //Nastavenie panelu
        super.setupPanel();
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

    public void drawScreen(Graphics2D g2d) {
        g2d.drawImage(new ImageIcon("assets/BUTTONS/imageFrame.png").getImage(), 380, 235, 140, 140, null);
        g2d.drawImage(new ImageIcon("assets/BUTTONS/imageFrame.png").getImage(), 380, 420, 140, 140, null);
        g2d.drawImage(new ImageIcon("assets/BUTTONS/imageFrame.png").getImage(), 380, 605, 140, 140, null);

        g2d.drawImage(new ImageIcon(super.getSkinPaths()[0]).getImage(), 400, 255, 100, 100, null);
        g2d.drawImage(new ImageIcon(super.getSkinPaths()[2]).getImage(), 400, 440, 100, 100, null);
        g2d.drawImage(new ImageIcon(super.getSkinPaths()[4]).getImage(), 400, 625, 100, 100, null);
    }


    //Funkcie jednotlivych tlacidiel
    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case PLAY -> {
                super.getFrame().remove(this);
                super.getFrame().add(new PreGameMenu(super.getFrame(), super.getMapPath(), super.getSkinPaths()));
            }
            case HELP -> {
                super.getFrame().remove(this);
                super.getFrame().add(new HelpPanel(super.getFrame(), super.getMapPath(), super.getSkinPaths()));
            }
            case OPTIONS -> {
                super.getFrame().remove(this);
                super.getFrame().add(new SettingsPanel(super.getFrame()));
            }
            case EXIT -> System.exit(0);
        }
    }
}
