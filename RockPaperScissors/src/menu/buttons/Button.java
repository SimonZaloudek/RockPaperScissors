package menu.buttons;

import menu.panels.IPanel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Trieda ktora nastavuje tlacididla pouzivane v programe
public class Button extends JButton implements MouseListener {

    private final IPanel panel;
    private final EButtons button;

    public Button(EButtons pButton, IPanel pPanel, int x, int y, int pWidth, int pHeight, String text, int var) {
        super.addMouseListener(this);
        this.panel = pPanel;
        this.button = pButton;
        //Vyberam z dvoch pouzivanych moznosti a nastavujem parametre a dizajn tlacidiel
        if (var == 0) {
            this.genericButton(x, y, pWidth, pHeight, text);
        }
        if (var == 1) {
            this.imageButton(x, y, pWidth, pHeight, text);
        }
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

    public void imageButton(int x, int y, int width, int height, String path) {
        super.setBounds(x, y, width, height);
        super.setBorder(BorderFactory.createEmptyBorder());
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
    //Kliknutim na lubovolne tlacidlo sa vykona interface metoda ktora patri k danej triede a vykona danu funkcionalitu
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