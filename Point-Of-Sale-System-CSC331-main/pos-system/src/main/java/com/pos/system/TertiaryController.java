package com.pos.system;
import java.io.File;
import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
@SuppressWarnings("unused")

public class TertiaryController{
    double checkoutFinale = 0.00;
    CartSystem pay = new CartSystem(null, null, 0);
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
        
    }
    
    @FXML 
    public Label checkoutTotal;
    @FXML
    private TextField payment;

    @FXML 
    public void setFinalTotal(double total) {
        checkoutTotal.setText("Total: $" + String.format("%.2f",total));
        checkoutFinale = total;
        pay.updatePrice(checkoutFinale);
    }
   
    @FXML
    void payment(ActionEvent event){
        double cash_payment = pay.payoff(Double.parseDouble(payment.getText()));
        checkoutTotal.setText("Total: $" + String.format("%.2f", cash_payment));

        //You paid your debt, you are free
        if(cash_payment <= 0){
            checkoutTotal.setText("Total: $" + String.format("%.2f", 0.0));
            double change = Math.abs(cash_payment);
            System.out.println("Your change is " + String.format("%.2f",change));
            
            Dialog<String> Conclusion = new Dialog<>();
            Conclusion.setTitle("Congratulations!");
            Conclusion.setHeaderText("You have fully paid off your order and your change is $" + String.format("%.2f",change) + "! The receipt is in a text file somewhere in here...");
            ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            Conclusion.getDialogPane().getButtonTypes().addAll(okButtonType);
            
            Conclusion.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                try {
                    App.setRoot("secondary");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        });
        Conclusion.showAndWait();
        
        }
        
    }
    
}