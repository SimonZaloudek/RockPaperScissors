package menu.panels;

import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;

import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel implements IPanel {
    Frame frame;

    HelpPanel(Frame pFrame) {
        this.frame = pFrame;

        this.setupPanel(Color.ORANGE, 600, 800);
        this.setupButtons();

        this.frame.add(this);
        this.frame.pack();
    }

    public void setupPanel(Color color, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(color);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void setupButtons() {

        menu.buttons.Button menuButton = new menu.buttons.Button(EButtons.MENU,this, 30, 30, 540, 150);
        this.add(menuButton);

        menu.buttons.Button backButton = new Button(EButtons.BACK,this, 30, 610, 175, 65, "BACK");
        this.add(backButton);
    }

    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case MENU, BACK -> {
                this.frame.remove(this);
                this.frame.add(new MenuPanel(this.frame));
            }
        }
    }
}
