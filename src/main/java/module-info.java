module project.team.pro100.controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires com.almasb.fxgl.all;


    opens project.team.pro100 to javafx.fxml;
    exports project.team.pro100;
    exports project.team.pro100.controller;
    opens project.team.pro100.controller to javafx.fxml;
}