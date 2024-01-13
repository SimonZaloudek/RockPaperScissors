module rps {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens RPS to javafx.fxml;
    exports RPS;

}