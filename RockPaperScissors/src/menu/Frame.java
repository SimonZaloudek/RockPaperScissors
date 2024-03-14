package menu;
import menu.panels.MenuPanel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

//Trieda ktora dedi JFrame, nastavuje zaklad herneho okna
public class Frame extends JFrame {

    private static final Dimension SCREEN_SIZE = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    public Frame() {
        super.setTitle("Rock | Paper | Scissors");
        super.setResizable(false);
        super.setVisible(true);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ImageIcon icon = new ImageIcon("assets/rpsIcon.png");
        super.setIconImage(icon.getImage());



        new MenuPanel(this, null, null);
    }

    public int getScreenWidth() {
        return (int)SCREEN_SIZE.getWidth();
    }

    public int getScreenHeight() {
        return (int)SCREEN_SIZE.getHeight();
    }
}
