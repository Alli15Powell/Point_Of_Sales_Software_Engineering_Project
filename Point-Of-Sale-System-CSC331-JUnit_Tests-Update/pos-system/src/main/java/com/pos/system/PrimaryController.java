package com.pos.system;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class PrimaryController {
    public Button primaryButton; // Button to be enabled/disabled
    public CheckBox confirm; // CheckBox to confirm TOS

    @FXML
    public void confirm_TOS(ActionEvent event) { // Method to handle CheckBox action
        primaryButton.setDisable(!confirm.isSelected()); // Enable/disable button based on checkbox state
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    // Public method to test switchToSecondary indirectly
    public void triggerSwitchToSecondary() throws IOException {
        switchToSecondary();
    }
}