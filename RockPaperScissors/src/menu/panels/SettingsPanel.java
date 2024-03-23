package menu.panels;

import menu.Frame;
import menu.buttons.Button;
import menu.buttons.EButtons;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

//Trieda ktora spravuje funkcionalitu panelu nastaveni
public class SettingsPanel extends Panels {

    //Zoznam ciest na vsetkych implementovane mapy
    private final String[] mapList = {"assets/ARENAS/mainArena.png", "assets/ARENAS/redArena.png", "assets/ARENAS/stoneArena.png", "assets/ARENAS/woodArena.png", "assets/ARENAS/mcArena.png"};

    //Zoznam ciest na vsetky implementovane "skiny"
    private final String[] rockSkins = { "assets/RPS/menuRock.png" , "assets/RPS/rock.png", "assets/RPS/menuMcRock.png", "assets/RPS/McRock.png", "assets/RPS/menuHandRock.png", "assets/RPS/handRock.png" };
    private final String[] paperSkins = { "assets/RPS/menuPaper.png" , "assets/RPS/paper.png", "assets/RPS/menuMcPaper.png", "assets/RPS/McPaper.png", "assets/RPS/menuHandPaper.png", "assets/RPS/handPaper.png" };
    private final String[] scissorSkins = { "assets/RPS/menuScissors.png" , "assets/RPS/scissors.png", "assets/RPS/menuMcScissors.png", "assets/RPS/McScissors.png", "assets/RPS/menuHandScissors.png", "assets/RPS/handScissors.png" };

    private int menuRockNumber = 0;
    private int menuPaperNumber = 0;
    private int menuScissorsNumber = 0;

    private int mapNumber = 0;

    public SettingsPanel(Frame pFrame) {
        super(pFrame, null, null);

        //Nastavuje panel
        super.setupPanel();
    }

    public void setupButtons() {

        Button menuButton = new Button(EButtons.MENU, this, 30, 30, 540, 150, "assets/BUTTONS/rpsMainLogo.png", 1);
        this.add(menuButton);

        Button backButton = new Button(EButtons.BACK, this, 40, 695, 175, 65, "BACK", 0);
        this.add(backButton);

        Button mapLButton = new Button(EButtons.FL, this, 25, 310, 100, 58, "assets/BUTTONS/buttonL.png", 1);
        this.add(mapLButton);

        Button mapRButton = new Button(EButtons.FR, this, 475, 310, 100, 58, "assets/BUTTONS/buttonR.png", 1);
        this.add(mapRButton);

        Button rockButton = new Button(EButtons.SL, this, 75, 520, 100, 100, null, 0);
        this.add(rockButton);
        rockButton.setOpaque(false);
        rockButton.setContentAreaFilled(false);

        Button paperButton = new Button(EButtons.SR, this, 250, 520, 100, 100, null, 0);
        this.add(paperButton);
        paperButton.setOpaque(false);
        paperButton.setContentAreaFilled(false);

        Button scissorsButton = new Button(EButtons.TL, this, 425, 520, 100, 100, null, 0);
        this.add(scissorsButton);
        scissorsButton.setOpaque(false);
        scissorsButton.setContentAreaFilled(false);
    }

    public void drawScreen(Graphics2D g2d) {

        g2d.setColor(Color.ORANGE);
        g2d.setFont(new Font("Arial Bold", Font.BOLD, 30));
        g2d.drawString("ARENA:", 240, 240);

        g2d.drawImage(new ImageIcon(this.mapList[this.mapNumber]).getImage(), 150, 260, 300, 150, null);

        g2d.drawString("SKINS:", 248, 490);

        g2d.drawImage(new ImageIcon(this.rockSkins[this.menuRockNumber]).getImage(), 75, 520, 100, 100, null);
        g2d.drawImage(new ImageIcon(this.paperSkins[this.menuPaperNumber]).getImage(), 250, 520, 100, 100, null);
        g2d.drawImage(new ImageIcon(this.scissorSkins[this.menuScissorsNumber]).getImage(), 425, 520, 100, 100, null);
    }

    public String[] setupSkins() {
        return new String[]{
                this.rockSkins[this.menuRockNumber], this.rockSkins[this.menuRockNumber + 1],
                this.paperSkins[this.menuPaperNumber], this.paperSkins[this.menuPaperNumber + 1],
                this.scissorSkins[this.menuScissorsNumber], this.scissorSkins[this.menuScissorsNumber + 1]};
    }

    //Funkcie jednotlivych tlacidiel
    //Taktiez system zmeny vykreslovania "skinov" a map
    @Override
    public void onButtonClick(EButtons button) {
        switch (button) {
            case MENU, BACK -> {
                this.setupSkins();
                super.getFrame().remove(this);
                //Pri opusteni nastaveni sa vytvara novy panel v ktorom uz budu implementovane nastavene "skiny" a mapy.
                super.getFrame().add(new MenuPanel(super.getFrame(), this.mapList[this.mapNumber], this.setupSkins()));
            }
            case FL -> {
                if (this.mapNumber > 0) {
                    this.mapNumber--;
                    this.repaint();
                }
            }
            case FR -> {
                if (this.mapNumber < 4) {
                    this.mapNumber++;
                    this.repaint();
                }
            }
            case SL -> {
                if (this.menuRockNumber < 4) {
                    this.menuRockNumber = this.menuRockNumber + 2;
                    this.repaint();
                } else {
                    this.menuRockNumber = 0;
                }
            }
            case SR -> {
                if (this.menuPaperNumber < 4) {
                    this.menuPaperNumber = this.menuPaperNumber + 2;
                    this.repaint();
                } else {
                    this.menuPaperNumber = 0;
                }
            }
            case TL -> {
                if (this.menuScissorsNumber < 4) {
                    this.menuScissorsNumber = this.menuScissorsNumber + 2;
                    this.repaint();
                } else {
                    this.menuScissorsNumber = 0;
                }
            }
        }
    }
}