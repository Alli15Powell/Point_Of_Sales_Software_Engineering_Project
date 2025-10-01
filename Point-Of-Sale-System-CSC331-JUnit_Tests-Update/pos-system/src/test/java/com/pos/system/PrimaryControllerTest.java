package com.pos.system;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class PrimaryControllerTest {

    private PrimaryController controller;

    @BeforeEach
    public void setUp() {
        // Initialize JavaFX application thread
        Platform.startup(() -> {});

        controller = new PrimaryController();
        controller.primaryButton = new Button(); // Initialize the button
        controller.confirm = new CheckBox(); // Initialize the checkbox
    }

    @Test
    public void testConfirmTOS_CheckBoxSelected() {
        // Simulate checkbox being selected
        controller.confirm.setSelected(true);
        
        // Simulate the action event
        controller.confirm_TOS(new ActionEvent());

        // Verify that the button is enabled
        assertFalse(controller.primaryButton.isDisabled(), "Button should be enabled when checkbox is selected");
    }

    @Test
    public void testConfirmTOS_CheckBoxNotSelected() {
        // Simulate checkbox not being selected
        controller.confirm.setSelected(false);
        
        // Simulate the action event
        controller.confirm_TOS(new ActionEvent());

        // Verify that the button is disabled
        assertTrue(controller.primaryButton.isDisabled(), "Button should be disabled when checkbox is not selected");
    }
}