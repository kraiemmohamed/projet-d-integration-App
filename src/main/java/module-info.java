module com.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.net.http;
    requires java.desktop;


    opens com.example.demo3 to javafx.fxml;
    exports com.example.demo3;
    exports com.example.demo3._Constantes;
    opens com.example.demo3._Constantes to javafx.fxml;
    exports com.example.demo3.ConnectionRobot;
    opens com.example.demo3.ConnectionRobot to javafx.fxml;
}