package com.pos.system;

import java.text.DecimalFormat;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CartSystem{
    private final StringProperty action = new SimpleStringProperty();
    private final StringProperty ItemName = new SimpleStringProperty();
    private final DoubleProperty Price = new SimpleDoubleProperty();
    private static double AddToCart = 0;
    private static double total = 0;
    private static double Taxed = 0;
    static DecimalFormat df = new DecimalFormat("#.00");
    
        //Constructor
        public CartSystem(String ActionTaken, String ItemName, double PriceAction){
            this.action.set(ActionTaken);
            this.ItemName.set(ItemName); 
            this.Price.set(PriceAction);
    
        }
        
        //Getters and Setters
        public String getAction(){
            return action.get();
        }
    
        public String getItem(){
            return ItemName.get();
        }
    
        public double getPrice(){
            return Price.get();
        }
    
        public void setAction(String Action){
            this.action.set(Action);
        }
        
        public void SetItemName(String ItemName){
            this.ItemName.set(ItemName);
        }
    
        public void SetPrice(double price){
            this.Price.set(price);
        }
        
    
        public StringProperty actionProperty(){
            return action;
        }
    
    
        //Math Methods
        public static double addToCart(double amount){
            AddToCart = amount;
            total += AddToCart;
            return total;
        }
    
        public static String CalculateTax(){
            Taxed = total * 0.1;
            
            String TaxStatement = ("Tax(%10): $ " + df.format(Taxed));
            return TaxStatement;
    }
        public static void ShowTotal(){ 
            System.out.println("Subtotal: $" + df.format(total));
        }

        public static String DisplayFinalTotal(){
            String Finale = ("Total: $" + String.format("%.2f", total + Taxed));
            System.out.println(Finale);
            return Finale;
        }

        public void updatePrice(double newPrice) {
            System.out.println("This is the new price: " + newPrice);
            total = newPrice;
            this.Price.set(newPrice);
        }

        public double payoff(double pay){
            total = total - pay;
            System.out.println("The total is " + String.format("%.2f",total));
            return total;
        }

        public String saveReceipt(String ID){
            String savedConfirm = ID;
            return savedConfirm;
            
        }

}