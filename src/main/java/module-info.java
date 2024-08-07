open module project.team.pro100.controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires com.almasb.fxgl.all;

    //TODO: Had to comment out these lines in order for the graphics to work properly. Need to fix.
    //opens project.team.pro100 to javafx.fxml;
    exports project.team.pro100;
    exports project.team.pro100.controller;
    //opens project.team.pro100.controller to javafx.fxml;
}