module rps {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens rps to javafx.fxml;
    exports rps;

}