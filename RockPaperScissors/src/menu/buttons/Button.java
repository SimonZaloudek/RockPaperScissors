package menu.buttons;

import menu.panels.IPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements MouseListener {

    IPanel panel;
    EButtons button;

    public Button(EButtons pButton, IPanel pPanel, int x, int y, int pWidth, int pHeight, String text) {
        super.addMouseListener(this);
        this.panel = pPanel;
        this.button = pButton;

        this.genericButton(x, y, pWidth, pHeight, text);
    }

    public Button(EButtons pButton, IPanel pPanel, int x, int y, int pWidth, int pHeight, int var) {
        super.addMouseListener(this);
        this.panel = pPanel;
        this.button = pButton;

        this.imageButton(x, y, pWidth, pHeight, var);
    }

    public void genericButton(int x, int y, int pWidth, int pHeight, String text) {
        super.setBounds(x, y, pWidth, pHeight);
        super.setText(text);
        super.setFont(new Font("Arial Bold", Font.BOLD, 35));
        this.setDefaultColor();
        super.setForeground(Color.BLACK);
        super.setBorder(new LineBorder(Color.ORANGE.darker(), 5));
        super.setVisible(true);
    }

    public void imageButton(int x, int y, int width, int height, int var) {
        super.setBounds(x, y, width, height);
        super.setBorder(BorderFactory.createEmptyBorder());
        String path;
        if (var == 1) {
            path = "assets/peto.png";
        } else if (var == 2) {
            path = "assets/buttonL.png";
        } else if (var == 3) {
            path = "assets/buttonR.png";
        } else {
            path = "";
        }
        ImageIcon buttonIcon = new ImageIcon(path);
        super.setIcon(buttonIcon);
        super.setVisible(true);
    }

    public void setDefaultColor() {
        super.setBackground(new Color(222, 170, 15));
    }

    public void setEventColor() {
        super.setBackground(new Color(252, 220, 62));
    }

    //MainClickMethod
    @Override
    public void mouseClicked(MouseEvent e) {
        this.setEventColor();
        this.panel.onButtonClick(this.button);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setEventColor();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setDefaultColor();
        //this.panel.onButtonClick(this.button);
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