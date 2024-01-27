package menu.panels;

import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;

import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel implements IPanel {
    private final Frame frame;
    private final String mapPath;
    private final String[] skinPaths;

    HelpPanel(Frame pFrame, String mapPath, String[] skinPaths) {
        this.frame = pFrame;
        this.mapPath = mapPath;
        this.skinPaths = skinPaths;

        this.setupPanel(Color.BLACK, 600, 800);
        this.setupButtons();

        this.frame.add(this);
        this.frame.pack();
    }

    public void setupPanel(Color color, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(color);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void setupButtons() {

        Button menuButton = new Button(EButtons.MENU,this, 30, 30, 540, 150, "assets/BUTTONS/rpsMainLogo.png",1);
        this.add(menuButton);

        Button backButton = new Button(EButtons.BACK,this, 40, 695, 175, 65, "BACK", 0);
        this.add(backButton);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(new ImageIcon("assets/helpPanel.png").getImage(), 50, 230, 500, 415, null);
    }

    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case MENU, BACK -> {
                this.frame.remove(this);
                this.frame.add(new MenuPanel(this.frame, this.mapPath, this.skinPaths));
            }
        }
    }
}
