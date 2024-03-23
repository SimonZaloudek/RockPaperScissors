package menu.panels;

import menu.Frame;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Panels extends JPanel implements IPanel {
    
    private final Frame frame;
    private String mapPath;
    private String[] skinPaths;
    
    Panels(Frame pFrame, String mapPath, String[] skinPaths) {
        this.frame = pFrame;

        this.mapPath = mapPath;
        if (this.mapPath == null || this.mapPath.isEmpty()) {
            this.mapPath = "assets/ARENAS/mainArena.png";
        }

        this.skinPaths = skinPaths;
        if (this.skinPaths == null) {
            this.skinPaths = new String[]{"assets/RPS/menuRock.png", "assets/RPS/rock.png", "assets/RPS/menuPaper.png", "assets/RPS/paper.png", "assets/RPS/menuScissors.png", "assets/RPS/scissors.png"};
        }
    }

    //Nastavuje okno hry
    protected void setupPanel() {
        this.setPreferredSize(new Dimension(600, 800));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setVisible(true);

        //Nastavenie tlacidiel
        this.setupButtons();

        this.frame.add(this);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    //Automaticky volana metoda triedy JComponent
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;

        //vykreslenie dizajnu
        this.drawScreen(g2d);
    }

    //Nastavenie tlacidiel
    protected abstract void setupButtons();

    //Vykresluje dizajn
    protected abstract void drawScreen(Graphics2D g2d);


    protected Frame getFrame() {
        return this.frame;
    }

    protected String getMapPath() {
        return this.mapPath;
    }

    protected String[] getSkinPaths() {
        return this.skinPaths;
    }
}
