package menu.buttons;

import menu.panels.MenuPanel;
import menu.panels.PreGameMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public enum EButtons {
    MENU(30, 30, 540, 150, "MENU", true, 0),
    PLAY(50, 270, 160, 65, "PLAY", false,1),
    OPTIONS(50, 390, 160, 65, "OPTIONS", false, 2),
    HELP(50, 510, 160, 65, "HELP", false, 3),
    EXIT(50, 630, 160, 65, "EXIT", false, 4),
    BACK(50, 630, 160, 65, "BACK", false, 5);

    final Button button = new Button();
    int function;

    EButtons(int x, int y, int width, int height, String text, boolean img, int pFunction) {
        this.button.addMouseListener(this.button);
        this.function = pFunction;
        if(!img) {
            this.button.createButton(x, y, width, height, text);
        } else {
            this.button.createButton(x, y, width, height);
        }
    }

    public void ButtonActions(MenuPanel panel, PreGameMenu prePanel) {
        switch (this.function) {
            case 0 -> this.MenuButton(prePanel);
            case 1 -> this.PlayButton(panel);
            case 2 -> this.OptionsButton(panel);
            case 3 -> this.HelpButton(panel);
            case 4 -> this.ExitButton();
            case 5 -> this.BackButton(prePanel);
        }
    }

    public void MenuButton(PreGameMenu panel) {
        if (panel != null) {
            this.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.Back();
                }
            });
        }
    }

    private void PlayButton(MenuPanel panel) {
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.Play();
            }
        });
    }

    private void OptionsButton(MenuPanel panel) {
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.Options();
            }
        });
    }

    private void HelpButton(MenuPanel panel) {
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.Help();
            }
        });
    }

    private void ExitButton() {
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void BackButton(PreGameMenu panel) {
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.Back();
            }
        });
    }

    public Button getButton() {
        return this.button;
    }
}
