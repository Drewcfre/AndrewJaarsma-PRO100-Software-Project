package project.team.pro100.controller;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class AudioController extends Application {
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;

@Override
    public void start(Stage stage) throws Exception {
        String mediaLocation = "src/main/resources/assets/music/RickRoll.wav";
        Media media = new Media(new File(mediaLocation).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        NumberAxis xAxis = new NumberAxis(0,75,5);
        NumberAxis yAxis = new NumberAxis(0, 50, 5);
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        lineChart.getData().add(series);
        Scene scene = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.show();
        mediaPlayer.setAudioSpectrumListener(((timestamp, duration, magnitudes, phases) -> {
            series.getData().clear();
            for (int i = 0; i < magnitudes.length; i++) {
                series.getData().add(new XYChart.Data<>(i+1, magnitudes[i]+60));
//                System.out.println(magnitudes[i]);
                if (magnitudes[i] > -60 && magnitudes[i] < -50) {
                    if (a >= 1) {
                        System.out.print("A");
                    } else System.out.print("\nA");
                    a++;
                    b=0;
                    c=0;
                    d=0;
                } else if (magnitudes[i] >= -50 && magnitudes[i] < -40) {
                    if (b >= 1) {
                        System.out.print("B");
                    } else System.out.print("\nB");
                    b++;
                    a=0;
                    c=0;
                    d=0;
                } else if (magnitudes[i] >= -40 && magnitudes[i] < -30) {
                    if (c >= 1) {
                        System.out.print("C");
                    } else System.out.print("\nC");
                    c++;
                    b=0;
                    a=0;
                    d=0;
                } else if (magnitudes[i] >= -30 && magnitudes[i] < -20) {
                    if (d >= 1) {
                        System.out.print("D");
                    } else System.out.print("\nD");
                    d++;
                    b=0;
                    c=0;
                    a=0;
                }
            }
        }));

        mediaPlayer.play();
    }
}
