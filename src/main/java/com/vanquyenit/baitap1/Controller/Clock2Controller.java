package com.vanquyenit.baitap1.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Clock2Controller {
    @FXML
    private Label label;

    private ZoneId zone;

    public void setZone(ZoneId zone) {
        this.zone = zone;
    }

    public void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Thread clockThread = new Thread(() -> {
            try {
                while (true) {
                    LocalTime time = LocalTime.now(zone);
                    String formattedTime = formatter.format(time);

                    javafx.application.Platform.runLater(() -> label.setText(formattedTime));

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        clockThread.setDaemon(true);
        clockThread.start();
    }
}
