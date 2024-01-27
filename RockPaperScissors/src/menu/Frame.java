package menu;
import menu.panels.MenuPanel;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        super.setTitle("Rock | Paper | Scissors");
        super.setResizable(false);
        super.setVisible(true);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ImageIcon icon = new ImageIcon("assets/rpsIcon.png");
        super.setIconImage(icon.getImage());

        new MenuPanel(this, null, null);
    }
}
