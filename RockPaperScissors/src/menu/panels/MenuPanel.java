package menu.panels;

import menu.Frame;
import menu.buttons.EButtons;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private Frame frame;

    public MenuPanel(Frame pFrame) {
        this.frame = pFrame;

        this.setupPanel(Color.BLACK, 600, 800);
        this.buttonSetup();

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

    public void buttonSetup() {

        EButtons playButton = EButtons.PLAY;
        this.add(playButton.getButton());
        playButton.ButtonActions(this, null);

        EButtons optionsButton = EButtons.OPTIONS;
        this.add(optionsButton.getButton());
        optionsButton.ButtonActions(this, null);

        EButtons helpButton = EButtons.HELP;
        this.add(helpButton.getButton());
        helpButton.ButtonActions(this, null);

        EButtons exitButton = EButtons.EXIT;
        this.add(exitButton.getButton());
        exitButton.ButtonActions(this, null);

        EButtons menuButton = EButtons.MENU;
        this.add(menuButton.getButton());
        menuButton.ButtonActions(this, null);
    }

    public void Play() {
        new PreGameMenu(this.frame);
        super.removeAll();
    }

    public void Options() {
        new OptionsPanel(this.frame);
        super.removeAll();
    }

    public void Help() {
        new HelpPanel(this.frame);
        super.removeAll();
    }
}
