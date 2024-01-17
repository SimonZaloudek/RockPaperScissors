package menu.panels;

import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel implements IPanel {

    private final Frame frame;

    public MenuPanel(Frame pFrame) {
        this.frame = pFrame;

        this.setupPanel(Color.BLACK, 600, 800);
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

        Button menuButton = new Button(EButtons.MENU,this, 30, 30, 540, 150);
        this.add(menuButton);

        Button playButton = new Button(EButtons.PLAY,this, 30, 250, 175, 65, "PLAY");
        this.add(playButton);

        Button optionsButton = new Button(EButtons.OPTIONS,this, 30, 370, 175, 65, "OPTIONS");
        this.add(optionsButton);

        Button helpButton = new Button(EButtons.HELP,this, 30, 490, 175, 65, "HELP");
        this.add(helpButton);

        Button exitButton = new Button(EButtons.EXIT,this, 30, 610, 175, 65, "EXIT");
        this.add(exitButton);
    }

    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case PLAY -> {
                this.frame.remove(this);
                this.frame.add(new PreGameMenu(this.frame));
            }
            case OPTIONS -> {
                this.frame.remove(this);
                this.frame.add(new OptionsPanel(this.frame));
            }
            case HELP -> {
                this.frame.remove(this);
                this.frame.add(new HelpPanel(this.frame));
            }
            case EXIT -> System.exit(0);
        }
    }
}
