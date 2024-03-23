package menu.panels;

import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;

//Jednoducha "filler" trieda ktora vykresluje panel s obrazkom ktora vysvetluje priebeh simulatora
public class HelpPanel extends Panels {

    HelpPanel(Frame pFrame, String mapPath, String[] skinPaths) {
        super(pFrame, mapPath, skinPaths);

        super.setupPanel();
    }

    public void setupButtons() {

        Button menuButton = new Button(EButtons.MENU, this, 30, 30, 540, 150, "assets/BUTTONS/rpsMainLogo.png", 1);
        this.add(menuButton);

        Button backButton = new Button(EButtons.BACK, this, 40, 695, 175, 65, "BACK", 0);
        this.add(backButton);
    }

    protected void drawScreen(Graphics2D g2d) {
        g2d.drawImage(new ImageIcon("assets/helpPanel.png").getImage(), 50, 230, 500, 415, null);
    }

    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case MENU, BACK -> {
                super.getFrame().remove(this);
                super.getFrame().add(new MenuPanel(super.getFrame(), super.getMapPath(), super.getSkinPaths()));
            }
        }
    }
}
