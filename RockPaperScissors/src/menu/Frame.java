package menu;
import menu.panels.MenuPanel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

//Trieda ktora dedi JFrame, nastavuje zaklad herneho okna
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
