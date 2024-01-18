package menu.panels;

import game.Game;
import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;

import javax.swing.*;
import java.awt.*;

public class PreGameMenu extends JPanel implements IPanel {

    Frame frame;

    PreGameMenu(Frame pFrame) {
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

    private void setupButtons() {
        Button menuButton = new Button(EButtons.MENU,this, 30, 30, 540, 150);
        this.add(menuButton);

        Button backButton = new Button(EButtons.BACK,this, 40, 695, 175, 65, "BACK");
        this.add(backButton);

        Button startButton = new Button(EButtons.PLAY, this, 385, 695, 175, 65, "START");
        this.add(startButton);
    }

    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case MENU, BACK -> {
                this.frame.remove(this);
                this.frame.add(new MenuPanel(this.frame));
            }
            case PLAY -> {
                this.frame.remove(this);
                this.frame.add(new Game(this.frame));
            }
        }
    }
}

