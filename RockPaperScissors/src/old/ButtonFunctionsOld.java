package old;

import menu.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ButtonFunctionsOld extends Frame {

    JButton aButton = new JButton();

    public void setButton(JButton pButton) {
        this.aButton = pButton;
    }

    public void ButtonAction(int function) {

        switch (function) {
            case 0 -> this.ButtonPlay();
            case 1 -> this.ButtonOptions();
            case 2 -> this.ButtonHelp();
            case 3 -> this.ButtonExit();
            case 4 -> this.ButtonMenu();
        }
    }

    public void ButtonPlay() {
        this.aButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("0");
            }
        });
    }

    public void ButtonOptions() {
        this.aButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("1");
            }
        });
    }

    public void ButtonHelp() {
        this.aButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("2");
            }
        });
    }

    public void ButtonExit() {
        this.aButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void ButtonMenu() {
        this.aButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("4");
            }
        });
    }


}
