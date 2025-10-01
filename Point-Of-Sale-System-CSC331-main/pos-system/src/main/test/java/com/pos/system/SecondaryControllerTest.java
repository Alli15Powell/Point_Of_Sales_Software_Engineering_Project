package com.pos.system;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class SecondaryControllerTest {

    private SecondaryController controller;
    private TableView<CartSystem> cartDisplay;
    private Label subtotalLabel;
    private Label taxLabel;
    private Label discountLabel;
    private Label totalLabel;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialize JavaFX Application Thread
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {});
        }

        // Initialize the controller and UI components
        controller = new SecondaryController();
        cartDisplay = new TableView<>();
        subtotalLabel = new Label();
        taxLabel = new Label();
        discountLabel = new Label();
        totalLabel = new Label();

        // Use reflection to set the private fields
        setField(controller, "CartDisplay", cartDisplay);
        setField(controller, "SubTotal", subtotalLabel);
        setField(controller, "Tax", taxLabel);
        setField(controller, "Discount", discountLabel);
        setField(controller, "Total", totalLabel);
    }

    private void setField(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    private void invokeMethod(Object obj, String methodName) throws Exception {
        Method method = obj.getClass().getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(obj);
    }

    @Test
    public void testUpdatePrices() throws Exception {
        CartSystem item1 = new CartSystem("ADD", "Item 1", 10.00);
        CartSystem item2 = new CartSystem("ADD", "Item 2", 20.00);
        
        cartDisplay.getItems().add(item1);
        cartDisplay.getItems().add(item2);

        // Use reflection to invoke the updatePrices method
        invokeMethod(controller, "updatePrices");

        assertEquals("Subtotal: $30.00", subtotalLabel.getText());
        assertEquals("Tax (10%): $3.00", taxLabel.getText());
        assertEquals("Discount: $0.00", discountLabel.getText());
        assertEquals("Total: $33.00", totalLabel.getText());
    }
    @Test
    public void testRemoveItem() throws Exception {
        // Create and add an item to the cart
        CartSystem item = new CartSystem("ADD", "Item to Remove", 15.00);
        cartDisplay.getItems().add(item);
        
        // Ensure the item is added
        assertEquals(1, cartDisplay.getItems().size(), "Item should be added to the cart.");
    
        // Simulate selecting the item to remove
        cartDisplay.getSelectionModel().select(item);
    
        // Call the remove method
        controller.Remove(null); // Assuming this method removes the selected item
    
        // Check that the item has been removed
        assertEquals(0, cartDisplay.getItems().size(), "Item should be removed from the cart.");
    }

    private Object getField(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}