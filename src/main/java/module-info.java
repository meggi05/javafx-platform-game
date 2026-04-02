module com.example.game_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.game_2 to javafx.fxml;
    exports com.example.game_2;
}