package Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Button implements IButtons {

    JButton button = new JButton();

    public Button() {
    }

    @Override
    public void createButton(int x, int y, int pWidth, int pHeight, String pText) {
        this.button.setBounds(x, y, pWidth, pHeight);
        this.button.setText(pText);
        this.button.setBackground(Color.YELLOW);

    }

    @Override
    public void createButton(int x, int y, int pWidth, int pHeight) {
        this.button.setBounds(x,y,pWidth,pHeight);
        this.button.setBackground(Color.GREEN);

        ImageIcon buttonIcon = new ImageIcon("peto.png");
        this.button.setIcon(buttonIcon);

        this.button.setBorder(BorderFactory.createEmptyBorder());
        this.button.setVisible(true);
        //this.button.
    }

    public JButton getButton() {
        return this.button;
    }

}
