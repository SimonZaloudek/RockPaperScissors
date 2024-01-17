package menu.buttons;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements IButtons, MouseListener {

    public Button() {
    }

    public void createButton(int x, int y, int pWidth, int pHeight, String text) {
        super.setBounds(x, y, pWidth, pHeight);
        super.setText(text);
        super.setFont(new Font("Arial Bold", Font.BOLD, 30));
        this.setDefaultColor();
        super.setForeground(Color.BLACK);
        super.setBorder(new LineBorder(Color.ORANGE.darker(), 4));
        super.setVisible(true);
    }

    public void createButton(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        super.setBorder(BorderFactory.createEmptyBorder());

        ImageIcon buttonIcon = new ImageIcon("peto.png");
        super.setIcon(buttonIcon);
        super.setVisible(true);
    }

    public void setDefaultColor() {
        super.setBackground(new Color(222, 170, 15));
    }

    public void setEventColor() {
        super.setBackground(new Color(252, 220, 62));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.setEventColor();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setEventColor();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setDefaultColor();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setEventColor();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setDefaultColor();
    }
}
