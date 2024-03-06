package com.vanquyenit.baitap1.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ClockController {

    public Label label;
    public TextField textfield;
    public Button button;

    public void displayClock() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Thread clockThread = new Thread(() -> {
            try {
                ZoneId zone = ZoneOffset.UTC;
                while (true) {
                    LocalDateTime now = LocalDateTime.now(zone);
                    String formattedTime = formatter.format(now);

                    // Cập nhật giao diện trong luồng giao diện chính
                    javafx.application.Platform.runLater(() -> label.setText(formattedTime));

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                // Xử lý nếu luồng bị gián đoạn
            }
        });
        clockThread.setDaemon(true);
        clockThread.start();
    }

    public void showClock2() {
        String timezone = textfield.getText();

        try {
            ZoneId zone = ZoneId.of(timezone);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Clock2.fxml"));
            Parent root = loader.load();
            Clock2Controller clock2Controller = loader.getController();
            clock2Controller.setZone(zone);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (DateTimeException | IOException e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Lỗi");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Múi giờ không hợp lệ");
            errorAlert.showAndWait();
        }
    }

    public void initialize() {
        displayClock();
        button.setOnAction(event -> showClock2());
    }
}
