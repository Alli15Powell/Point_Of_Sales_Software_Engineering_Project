package com.pos.system;

import javafx.fxml.Initializable; // Import for Initializable
import javafx.scene.control.Button; // Import for Button
import javafx.scene.control.ComboBox; // Import for ComboBox
import javafx.scene.control.Label; // Import for Label
import javafx.scene.control.TableColumn; // Import for TableColumn
import javafx.scene.control.TableView; // Import for TableView
import javafx.fxml.FXML; // Import for FXML
import javafx.event.ActionEvent; // Import for ActionEvent
import javafx.collections.FXCollections; // Import for FXCollections
import javafx.collections.ObservableList; // Import for ObservableList

import java.net.URL; // Import for URL
import java.util.ResourceBundle; // Import for ResourceBundle
import java.io.IOException; // Import for IOException

public class SecondaryController implements Initializable {
    // Existing variables
    @FXML
    private Button BECC, Bagel, BM, Brownie, CS, CCC, Remove; // Buttons for items
    @FXML
    private Label SubTotal, Tax, Total, DiscountLabel; // Labels for displaying totals
    @FXML
    private TableView<CartSystem> CartDisplay; // Table for displaying cart items
    
    @FXML
    private ComboBox<String> discountComboBox; // ComboBox for discounts

    // Initialize method
    @Override  
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize discount ComboBox
        ObservableList<String> discounts = FXCollections.observableArrayList("No Discount", "5% Discount", "10% Discount", "15% Discount");
        discountComboBox.setItems(discounts);
        discountComboBox.setValue("No Discount"); // Default selection
        discountComboBox.setOnAction(this::applyDiscount); // Apply discount when selection changes
    }

    // Method to apply discount
    private void applyDiscount(ActionEvent event) {
        if (CartDisplay.getItems().isEmpty()) {
            // Show message if cart is empty
            DiscountLabel.setText("Select something before applying a discount");
            DiscountLabel.setVisible(true);
            return;
        }

        double discount = 0;
        String selectedDiscount = discountComboBox.getValue();
        switch (selectedDiscount) {
            case "5% Discount":
                discount = 0.05;
                break;
            case "10% Discount":
                discount = 0.10;
                break;
            case "15% Discount":
                discount = 0.15;
                break;
            default:
                discount = 0; // No discount
        }

        // Calculate new total
        double subtotal = CartSystem.getTotal(); // Assuming you have a method to get the total
        double tax = subtotal * 0.1; // Assuming tax is 10%
        double finalTotal = subtotal + tax - (subtotal * discount);

        // Update labels
        SubTotal.setText("Subtotal: $" + String.format("%.2f", subtotal));
        Tax.setText("Tax (%10): $" + String.format("%.2f", tax));
        Total.setText("Total: $" + String.format("%.2f", finalTotal));
        DiscountLabel.setText("Discount Applied: " + (discount * 100) + "%");
        DiscountLabel.setVisible(true); // Show discount label
    }

    // Method to switch back to primary view
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary"); // Switch to the primary view
    }

    // Method for BECC button
    @FXML
    private void BECC(ActionEvent event) {
        // Logic for adding a Bacon, Egg, and Cheese Croissant to the cart
        double price = 7.25; // Example price
        CartSystem.Add(price); // Update the total in the CartSystem
        // Optionally update the cart display here
        System.out.println("Added Bacon, Egg, and Cheese Croissant to cart.");
    }

    // Method for Bagel button
    @FXML
    private void Bagel(ActionEvent event) {
        // Logic for adding a Bagel to the cart
        double price = 2.50; // Example price
        CartSystem.Add(price); // Update the total in the CartSystem
        // Optionally update the cart display here
        System.out.println("Added Bagel to cart.");
    }

    // Method for BM button
    @FXML
    private void BM(ActionEvent event) {
        // Logic for adding a Breakfast Muffin to the cart
        double price = 3.00; // Example price
        CartSystem.Add(price); // Update the total in the CartSystem
        // Optionally update the cart display here
        System.out.println("Added Breakfast Muffin to cart.");
    }

    // Method for Brownie button
    @FXML
    private void Brownie(ActionEvent event) {
        // Logic for adding a Brownie to the cart
        double price = 2.75; // Example price
        CartSystem.Add(price); // Update the total in the CartSystem
        // Optionally update the cart display here
        System.out.println("Added Brownie to cart.");
    }

    // Method for CS button
    @FXML
    private void CS(ActionEvent event) {
        // Logic for adding a Chocolate Chip Cookie to the cart
        double price = 1.50; // Example price
        CartSystem.Add(price); // Update the total in the CartSystem
        // Optionally update the cart display here
        System.out.println("Added Chocolate Chip Cookie to cart.");
    }
    
    @FXML
    private void CinnamonRoll(ActionEvent event) {
        // Logic for adding a Cinnamon Roll to the cart
        double price = 3.00; // Example price
        CartSystem.Add(price); // Update the total in the CartSystem
        System.out.println("Added Cinnamon Roll to cart.");
    }

    // Method for CCC button
    @FXML
    private void CC(ActionEvent event) {
        // Logic for adding a Chocolate Chip Cookie to the cart
        double price = 1.50; // Example price
        CartSystem.Add(price); // Update the total in the CartSystem
        // Optionally update the cart display here
        System.out.println("Added another Chocolate Chip Cookie to cart.");
    }

    // Method for Remove button
// Method for Remove button
@FXML
private void Remove(ActionEvent event) {
    // Logic for removing the last item from the cart
    CartSystem.RemoveLast(); // Call the new method to remove the last item
    // Optionally update the cart display here
    System.out.println("Removed the last item from the cart.");
}
}