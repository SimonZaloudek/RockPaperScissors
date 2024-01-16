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
        this.Buttons();


        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    public void Frame() {

        this.frame.setTitle("Rock | Paper | Scissors");
        this.frame.setResizable(true);
        this.frame.setVisible(true);

        ImageIcon icon = new ImageIcon("icon.jpg");
        this.frame.setIconImage(icon.getImage());
    }

    public void Panel() {

        this.panel.setPreferredSize(this.menuSize);
        this.panel.setBackground(Color.BLACK);
        this.panel.setVisible(true);
        this.panel.setLayout(null);

        this.frame.add(panel);

    }

    public void Buttons() {

        Button button1 = new Button();
        button1.createButton(80, 260,140, 60, "PLAY");

        Button button2 = new Button();
        button2.createButton(80, 370,140, 60, "OPTIONS");

        Button button3 = new Button();
        button3.createButton(80, 480,140, 60, "HELP");

        Button button4 = new Button();
        button4.createButton(80, 590,140, 60, "EXIT");

        Button mainButton = new Button();
        mainButton.createButton(30,30,640,170);


        this.panel.add(button1.getButton());
        this.panel.add(button2.getButton());
        this.panel.add(button3.getButton());
        this.panel.add(button4.getButton());
        this.panel.add(mainButton.getButton());
    }
}
