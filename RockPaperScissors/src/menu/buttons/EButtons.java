package menu.buttons;

import javax.swing.*;

public enum EButtons {
    MENU(25, 25, 550, 150, "MENU", true, 1),
    PLAY(50, 300, 160, 60, "PLAY", false,0),
    OPTIONS(50, 400, 160, 60, "OPTIONS", false, 2),
    HELP(50, 500, 160, 60, "HELP", false, 3),
    EXIT(50, 600, 160, 60, "EXIT", false, 4);

    Button button = new Button();

    EButtons(int x, int y, int width, int height, String text, boolean img, int pFunction) {
        if(!img) {
            this.button.createButton(x, y, width, height, text);
        } else {
            this.button.createButton(x, y, width, height);
        }

        switch (pFunction) {
            case 0 -> System.out.println("0");
            case 1 -> System.out.println("1");
            case 2 -> System.out.println("2");
            case 3 -> System.out.println("3");
            case 4 -> System.out.println("4");
        }
    }

    public Button getButton() {
        return this.button;
    }


}
