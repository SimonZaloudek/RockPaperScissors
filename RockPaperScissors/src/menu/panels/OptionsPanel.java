package menu.panels;

import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel implements IPanel {

    private final Frame frame;
    private final String[] mapList = {"assets/gamePanelMain.png", "assets/icon.jpg"};

    private int mapNumber = 0;

    public OptionsPanel(Frame pFrame) {
        this.frame = pFrame;

        this.setupPanel(Color.BLACK, 600, 800);
        this.setupButtons();

        this.frame.add(this);
        this.frame.pack();
        this.repaint();
    }

    public void setupPanel(Color color, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(color);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void setupButtons() {

        Button menuButton = new Button(EButtons.MENU,this, 30, 30, 540, 150, 1);
        this.add(menuButton);

        Button backButton = new Button(EButtons.BACK,this, 40, 695, 175, 65, "BACK");
        this.add(backButton);

        Button mapLButton = new Button(EButtons.FL, this, 25, 310, 100, 58, 2);
        this.add(mapLButton);

        Button mapRButton = new Button(EButtons.FR, this, 475, 310, 100, 58, 3);
        this.add(mapRButton);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;

        this.drawScreen(g2d);
    }

    public void drawScreen(Graphics2D g2d) {

        g2d.setColor(Color.ORANGE);
        g2d.setFont(new Font("Arial Bold", Font.BOLD, 30));
        g2d.drawString("ARENA:", 230, 240);

        g2d.drawImage(new ImageIcon(this.mapList[this.mapNumber]).getImage(), 150, 260,300, 150, null);

        g2d.drawString("SKINS:", 230, 490);
    }

    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case MENU, BACK -> {
                this.frame.remove(this);
                this.frame.add(new MenuPanel(this.frame, this.mapList[mapNumber]));
            }
            case FL -> {
                if(this.mapNumber > 0) {
                    this.mapNumber--;
                    this.repaint();
                }
            }
            case FR -> {
                if(this.mapNumber < 1) {
                    this.mapNumber++;
                    this.repaint();
                }
            }
        }
    }
}