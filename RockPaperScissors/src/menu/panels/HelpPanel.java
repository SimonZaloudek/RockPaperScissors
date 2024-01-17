package menu.panels;

import menu.Frame;

import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {
    Frame frame;

    HelpPanel(Frame pFrame) {
        this.frame = pFrame;

        this.setupPanel(Color.ORANGE, 600, 800);
        this.frame.add(this);
        this.frame.pack();
    }

    public void setupPanel(Color color, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(color);
        this.setLayout(null);
        this.setVisible(true);
    }
}
