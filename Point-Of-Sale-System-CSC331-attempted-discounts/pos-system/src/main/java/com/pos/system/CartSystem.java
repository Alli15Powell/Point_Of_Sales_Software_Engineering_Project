package com.pos.system;

import java.text.DecimalFormat;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CartSystem {
    private final StringProperty action = new SimpleStringProperty();
    private final StringProperty ItemName = new SimpleStringProperty();
    private final DoubleProperty Price = new SimpleDoubleProperty();
    private static double AddToCart = 0;
    private static double total = 0;
    private static double Taxed = 0;
    static DecimalFormat df = new DecimalFormat("#.00");

    // Constructor
    public CartSystem(String ActionTaken, String ItemName, double PriceAction) {
        this.action.set(ActionTaken);
        this.ItemName.set(ItemName);
        this.Price.set(PriceAction);
    }

    // Getters and Setters
    public String getAction() {
        return action.get();
    }

    public String getItem() {
        return ItemName.get();
    }

    public double getPrice() {
        return Price.get();
    }

    public static double getTotal() {
        return total; // Return the current total
    }

    public void setAction(String Action) {
        this.action.set(Action);
    }

    public void SetItemName(String ItemName) {
        this.ItemName.set(ItemName);
    }

    public void SetPrice(double price) {
        this.Price.set(price);
    }

    public StringProperty actionProperty() {
        return action;
    }

    // Math Methods
    public static double Add(double amount) {
        AddToCart = amount;
        total += AddToCart;
        return total;
    }

    public static double Remove(double amount) {
        if (total > 0) {
            total -= amount; // Subtract the specified amount from the total
        }
        return total;
    }

    // New method to remove the last added item (if you keep track of added items)
    public static void RemoveLast() {
        // Logic to remove the last item added to the cart
        // For example, you may want to keep a list of prices for items added to the cart
        // Here, we can assume the last added item's price was stored in a variable
        // You would need to manage a list of added items to implement this correctly
        // For now, let's assume we remove a fixed amount (you can customize this)
        double lastAddedPrice = 0; // Replace with the actual last added item's price
        total -= lastAddedPrice; // Adjust this to the actual last added item's price
        if (total < 0) {
            total = 0; // Prevent total from going negative
        }
    }

    public static String CalculateTax() {
        Taxed = total * 0.1;

        String TaxStatement = ("Tax(%10): $ " + df.format(Taxed));
        return TaxStatement;
    }

    public static String ShowTotal() {
        String x = ("Subtotal: $" + df.format(total));
        return x;
    }

    public static String DisplayFinalTotal() {
        String Finale = ("Total: $" + String.format("%.2f", total + Taxed));
        System.out.println(Finale);
        return Finale;
    }
}