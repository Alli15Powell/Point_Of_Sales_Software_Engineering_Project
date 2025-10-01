package com.pos.system;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {
    public Button primaryButton;
    @FXML
    private void confirm_TOS(ActionEvent event) {
        primaryButton.setDisable(false);
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
