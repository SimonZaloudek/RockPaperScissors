package rps;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private int stageHeight = 800;
    private int stageWidth = 1200;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.setStage(stage);

        Group root = new Group();
        Scene scene = new Scene(root, this.stageWidth, this.stageHeight, Color.BLACK);

        stage.setScene(scene);
        stage.show();
    }

    public void setStage(Stage stage) {
        stage.setTitle("RPS | Rock Paper Scissors");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setHeight(this.stageHeight);
        stage.setWidth(this.stageWidth);
        Image icon = new Image("icon.jpg");
        stage.getIcons().add(icon);
    }
}