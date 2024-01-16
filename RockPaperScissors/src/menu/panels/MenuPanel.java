package menu.panels;

import menu.Frame;
import menu.buttons.EButtons;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private Frame frame;

    public MenuPanel(Frame pFrame) {
        this.frame = pFrame;

        this.SetupPanel(Color.BLACK, 600, 800);
        this.ButtonSetup();
    }

    public void SetupPanel(Color color, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(color);
        this.setLayout(null);
        this.setVisible(true);

    }

    public void ButtonSetup() {

        EButtons playButton = EButtons.PLAY;
        this.add(playButton.getButton());

        EButtons optionsButton = EButtons.OPTIONS;
        this.add(optionsButton.getButton());

        EButtons helpButton = EButtons.HELP;
        this.add(helpButton.getButton());

        EButtons exitButton = EButtons.EXIT;
        this.add(exitButton.getButton());

        EButtons menuButton = EButtons.MENU;
        this.add(menuButton.getButton());

    }
}
