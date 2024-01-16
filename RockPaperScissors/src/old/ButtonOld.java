package old;

import old.ButtonFunctionsOld;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ButtonOld extends ButtonFunctionsOld {

    JButton button = new JButton();

    public ButtonOld() {
    }


    public void createButton(int x, int y, int pWidth, int pHeight, String pText, int function) {
        this.button.setBounds(x, y, pWidth, pHeight);
        this.button.setText(pText);
        this.button.setBackground(new Color(212,174,4));
        this.button.setForeground(Color.BLACK);
        this.button.setFont(new Font("Arial Bold", Font.BOLD,30));
        this.button.setBorder(new LineBorder(Color.RED.darker(), 4));

        this.setButton(this.button);
        this.ButtonAction(function);
    }

    public void createButton(int x, int y, int pWidth, int pHeight, int function) {
        this.button.setBounds(x,y,pWidth,pHeight);

        ImageIcon buttonIcon = new ImageIcon("peto.png");
        this.button.setIcon(buttonIcon);

        this.button.setBorder(BorderFactory.createEmptyBorder());
        this.button.setVisible(true);

        this.setButton(this.button);
        this.ButtonAction(function);
    }

    public JButton getButton() {
        return this.button;
    }

}
