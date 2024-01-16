package menu.buttons;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Button extends JButton {

    public Button() {
    }

    public void createButton(int x, int y, int width, int height, String text) {
        super.setBounds(x, y, width, height);
        super.setText(text);
        super.setFont(new Font("Arial Bold", Font.BOLD, 30));
        super.setBackground(new Color(212,174,4));
        super.setForeground(Color.BLACK);
        super.setBorder(new LineBorder(Color.ORANGE.darker(), 4));
        super.setFocusable(false);
    }

    public void createButton(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        super.setBorder(BorderFactory.createEmptyBorder());
        super.setFocusable(false);

        ImageIcon buttonIcon = new ImageIcon("peto.png");
        super.setIcon(buttonIcon);
    }
}
