package Menu;
import javax.swing.*;
import java.awt.*;

public class MainMenu {

    Dimension menuSize = new Dimension(700, 800);
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    public MainMenu() {
        this.Frame();
        this.Panel();
    }

    public void Frame() {

        this.frame.setTitle("Rock | Paper | Scissors");
        this.frame.setSize(this.menuSize);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.setVisible(true);

        ImageIcon icon = new ImageIcon("icon.jpg");
        this.frame.setIconImage(icon.getImage());
    }

    public void Panel() {

        this.panel.setSize(this.menuSize);
        this.panel.setBackground(Color.BLACK);
        this.panel.setVisible(true);

        this.frame.add(panel);
    }
}
