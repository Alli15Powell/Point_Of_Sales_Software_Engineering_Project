package com.pos.system;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


public class SecondaryController implements Initializable {
    @FXML
    private Button priceChangeButton;
    @FXML
    private Button Remove;
    @FXML
    private Label SubTotal;
    @FXML
    private Label Tax;
    @FXML
    private Label Discount;
    @FXML
    private Label Total;
    double changedPrice = 0.00;
    double subtotal = 0.00;
    double displayDiscount = 0.00;
    double discountGlobal = 0.00;
    double total = 0.00;

    //Methods Start Here
    //------------------------------------------------------

    //Calling all instances of the class here. I figured that it would be redundant if we called an instance EVERY time a button was pressed.
    //Foods
    CartSystem BECC = new CartSystem("ADD", "Bacon, Egg, and Cheese Croissant", 7.25);
    CartSystem Bagel = new CartSystem("ADD", "Bagel", 3.25);
    CartSystem BM = new CartSystem("ADD", "Blueberry Muffin", 3.75);
    CartSystem Brownie = new CartSystem( "ADD", "Brownie", 3);
    CartSystem CS = new CartSystem("ADD", "Cake Slice", 3.5);
    CartSystem CCC = new CartSystem("ADD", "Chocolate Chip Cookie", 1.75);
    //Drinks
    CartSystem Acid = new CartSystem(null, "Acid", 0);
    CartSystem BC = new CartSystem(null, null, 0);
    CartSystem BO = new CartSystem(null, null, 0);
    CartSystem FT = new CartSystem(null, null, 0);
    CartSystem Latte = new CartSystem(null, null, 0);
    CartSystem RC = new CartSystem(null, null, 0);

    //Housekeeping 
    //I am guessing this is how we transition to the previous scene
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    
    }

    //Cart System Itself
    //Food Items
    //Bacon, Egg, and Cheese Croissant
    @FXML
    void BECC(ActionEvent event) {
        priceChangeButton.setVisible(false);
        CartDisplay.getItems().add(BECC);
        CartSystem.addToCart(7.25);
        System.out.println("Added Bacon, Egg, and Cheese Croissant");
        updatePrices();
        
    }

    //Bagel
    @FXML
    void Bagel(ActionEvent event) {
        priceChangeButton.setVisible(false);
        CartDisplay.getItems().add(Bagel);
        CartSystem.addToCart(3.25);
        System.out.println("Added Bagel");
        updatePrices();
    }

    //Blueberry Muffin
    @FXML
    void BM(ActionEvent event) {
        priceChangeButton.setVisible(false);
        CartDisplay.getItems().add(BM);
        CartSystem.addToCart(3.75);
        System.out.println("Added Blueberry Muffin");
        updatePrices();
    }

    //Brownie
    @FXML
    void Brownie(ActionEvent event) {
        priceChangeButton.setVisible(false);
        CartDisplay.getItems().add(Brownie);
        CartSystem.addToCart(3);
        System.out.println("Added Brownie");
        updatePrices();
    }

    //Cake Slice
    @FXML
    void CS(ActionEvent event) {
        priceChangeButton.setVisible(false);
        CartDisplay.getItems().add(CS);
        CartSystem.addToCart(3.5);
        System.out.println("Added Cake Slice");
        updatePrices();
    }


    //Chocolate Chip Cookie
    @FXML
    void CCC(ActionEvent event) {
        priceChangeButton.setVisible(false);
        CartDisplay.getItems().add(CCC);
        CartSystem.addToCart(1.75);
        System.out.println("Added Chocolate Chip Cookie");
        updatePrices();
    }

    //DRINKS START HERE
    //-------------------------------------------------------
    @FXML
    void additions(String itemName, double basePrice){
        boolean modified = false;
        double strikePrice = 0;
        String modification1 = "", modification2 = "", sizePlacement = " ";

        //Formatting
        Dialog<ButtonType> askForAdditions = new Dialog<>();
        askForAdditions.setTitle("Adding a drink...");
        askForAdditions.setHeaderText("Anything else you want to add?");
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        askForAdditions.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);

        //Things inside the dialogue box
        Label sizeLabel = new Label("Size");
        ChoiceBox<String> size = new ChoiceBox<>();
        Label iceLabel = new Label("Ice");
        ChoiceBox<String> ice = new ChoiceBox<>();
        Label sweetLabel = new Label("Sweetness");
        ChoiceBox<String> sweets = new ChoiceBox<>();
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(sizeLabel,size,iceLabel,ice,sweetLabel,sweets);
        askForAdditions.getDialogPane().setContent(vbox);

        //Options to choose from
        String[] sizeOptions = {"Small - +($1.00)", "Medium - +($1.50)", "Large - +($2.00)"};
        String[] iceOptions = {"Low Ice - +($1.00)", "More Ice - +($1.50)", "Lots Ice - +($2.00)"};
        String[] sweetOptions = {"Less Sweet - +($1.00)", "More Sweet - +($1.50)", "Diabetes - +($2.00)"};
        size.getItems().addAll(sizeOptions);
        ice.getItems().addAll(iceOptions);
        sweets.getItems().addAll(sweetOptions);

        //Deals with price calculations
        Optional<ButtonType> response = askForAdditions.showAndWait();
        if (response.get()==okButtonType) {
            String sizeInquiry = size.getValue();
            if (sizeInquiry != null) {
                switch (sizeInquiry) {
                    case "Small - +($1.00)":
                        sizePlacement = "(Small)";
                        strikePrice += 1;
                        break;
                    case "Medium - +($1.50)":
                        sizePlacement = "(Medium)";
                        strikePrice += 1.5;
                        break;
                    case "Large - +($2.00)":
                        sizePlacement = "(Large)";
                        strikePrice += 2;
                        break;
                    default:
                        return;
                }
            }
            String iceInquiry = ice.getValue();
            if (iceInquiry != null) {
                modified = true; 
                switch (iceInquiry) {
                    case "Low Ice - +($1.00)":
                        modification1 = " -Less Ice";
                        strikePrice += 1;
                        break;
                    case "More Ice - +($1.50)":
                        modification1 = " -More Ice";
                        strikePrice += 1.5;
                        break;
                    case "Lots Ice - +($2.00)":
                        modification1 = " -Lots Ice";
                        strikePrice += 2;
                        break;
                    default:
                        return;
                }
            }
            String sweetInquiry = sweets.getValue();
            if (sweetInquiry != null) {
                modified = true;
                switch (sweetInquiry) {
                    case "Less Sweet - +($1.00)":
                        modification2 = " -Less Sweet";
                        strikePrice += 1;
                        break;
                    case "More Sweet - +($1.50)":
                        modification2 = " -More Sweet";
                        strikePrice += 1.5;
                        break;
                    case "Diabetes - +($2.00)":
                        modification2 = " -Diabetes";
                        strikePrice += 2;
                        break;
                    default:
                        return;
                }
                
            }  
            if(modified == true){
                CartSystem Modified = new CartSystem("MODIFIED",sizePlacement + itemName + modification1 + modification2, basePrice + strikePrice);
                CartDisplay.getItems().add(Modified);
                modified = false;
            }
            else{   
                CartSystem Original = new CartSystem("ADD",sizePlacement + itemName, basePrice);
                CartDisplay.getItems().add(Original);
            }
        } else{
            System.out.println("No Action Taken");
        }
    } 

        
    
    @FXML
    void Acid(ActionEvent event){
        priceChangeButton.setVisible(false);
        additions("Acid",2.5);
        CartSystem.addToCart(7.25);
        System.out.println("Added Acid"); 
        updatePrices();
    }
    @FXML
    void BC(ActionEvent event){
        priceChangeButton.setVisible(false);
        additions("Black Coffee",1.25);
        System.out.println("Added Black Coffee"); 
        updatePrices();
    }
    @FXML
    void BO(ActionEvent event){
        priceChangeButton.setVisible(false);
        additions("Blind Obedience",10);
        System.out.println("Added Blind Obedience"); 
        updatePrices();
    }
    @FXML
    void FT(ActionEvent event){
        priceChangeButton.setVisible(false);
        additions("Fun Times",4.25);
        System.out.println("Added Fun Times"); 
        updatePrices();
    }
    @FXML
    void Latte(ActionEvent event){
        priceChangeButton.setVisible(false);
        additions("Latte",3.75);
        System.out.println("Added Latte"); 
        updatePrices();
    }
    @FXML
    void RC(ActionEvent event){
        priceChangeButton.setVisible(false);
        additions("Russian Casket",500);
        System.out.println("Added Russian Casket"); 
        updatePrices();
    }

    //Cart GUI
    //------------------------------------------------------
    @FXML
    private TableView<CartSystem> CartDisplay;
    @FXML
    private TableColumn<CartSystem, Integer> ID = new TableColumn<>("ID");
    @FXML
    private TableColumn<CartSystem, String> Action = new TableColumn<>("Action");
    @FXML
    private TableColumn<CartSystem, String> Item = new TableColumn<>("Item");
    @FXML
    private TableColumn<CartSystem, Double> Price = new TableColumn<>("Price");

    //Discounts have to be created here because we need the controller to implement an initializable. We can't do it in CartSystem otherwise.
    @FXML
    private ChoiceBox<String> discountsChoiceBox;

    ObservableList<CartSystem>initializeData(){
        return FXCollections.observableArrayList();
    }
    
    //Very important for the cart list and discount list
    @Override  
    public void initialize(URL URL, ResourceBundle ResourceBundle) {
        
        ID.setCellValueFactory(new PropertyValueFactory<CartSystem, Integer>("ID"));
        Action.setCellValueFactory(new PropertyValueFactory<CartSystem, String>("Action"));
        Item.setCellValueFactory(new PropertyValueFactory<CartSystem, String>("Item"));
        Price.setCellValueFactory(new PropertyValueFactory<CartSystem, Double>("Price"));
        CartDisplay.setItems(initializeData());

        discountsChoiceBox.getItems().addAll(Discounts);
        discountsChoiceBox.setOnAction(this::applyDiscount);
        
    } 
    
    

    //"Admin Panel"
    @FXML
    //Needsto be here for remove function to work, haven't gotten it to work elsewhere.
    void Remove(ActionEvent evnet){
        @SuppressWarnings("unused")
        //x is just to call an instance of the class, an empty constructor helps
        //Anyway, this part actially removes selected items in the list.
        CartSystem x = new CartSystem(null, null, 0);
        int RemoveTag = CartDisplay.getSelectionModel().getSelectedIndex();
        if(RemoveTag == -1){
            System.out.println("Select something before removing it");
        }
        else{
        CartDisplay.getItems().remove(RemoveTag);
        }

        updatePrices();
        if(total < 0){
            total = 0;
            displayDiscount = 0;
            discountGlobal = 0;
            Total.setText(String.format("Total: $%.2f", total)); 

        }

    }

    //Price Updates
    private void updatePrices(){
        subtotal = 0;
        //Iterates through the list to update price
        for (CartSystem item : CartDisplay.getItems()) {
            
            subtotal += item.getPrice();
        }
        // Calculate tax and total
        double tax = subtotal * 0.10; // Assuming a 10% tax rate
        displayDiscount = subtotal * discountGlobal;
        total = (subtotal + tax) - displayDiscount;

        SubTotal.setText(String.format("Subtotal: $%.2f", subtotal));
        Tax.setText(String.format("Tax (10%%): $%.2f", tax));
        Discount.setText(String.format("Discount: $%.2f", displayDiscount));
        Total.setText(String.format("Total: $%.2f", total));  
      
    }


    //Discounts
    private String[] Discounts = {"Discount (-10%): $", "Discount (-15%): $", "Discount (-50%): $"};
    public void applyDiscount(ActionEvent event){
        String discountedPercent = discountsChoiceBox.getValue();
        switch(discountedPercent){
            case "Discount (-10%): $":
            discountGlobal = 0.1;
            updatePrices();
            break;

            case "Discount (-15%): $":
            discountGlobal = 0.15;
            updatePrices();
            break;

            case "Discount (-50%): $":
            discountGlobal = 0.5;
            updatePrices();
            break;

            default:
            return;
        }
        Discount.setText(String.format("Discount: $%.2f", displayDiscount));
    }

    
    //Price Changes
    //Needs to be here, breaks if I try to put in CartSystem or a new file
    @FXML
    private void priceChange(){
        //Asks which button, or food item, you want to change
        TextInputDialog inquireChange = new TextInputDialog();
        inquireChange.setTitle("Choose an item.");
        inquireChange.setHeaderText("Choose a button");
        inquireChange.setContentText("The item number is:");

        Optional<String> responseChange = inquireChange.showAndWait();
        if(responseChange.isPresent()){
            try{
                int index = Integer.parseInt(responseChange.get().trim());
                CartSystem selectedItem = null;
                switch (index){
                    case 1:
                    selectedItem = BECC;
                    break;
                    case 2:
                    selectedItem = Bagel;
                    break;
                    case 3:
                    selectedItem = BM;
                    break;
                    case 4:
                    selectedItem = Brownie;
                    break;
                    case 5:
                    selectedItem = CCC;
                    break;
                    case 6:
                    selectedItem = CS;
                    break;
                    default:
                    System.out.println("Choose an actual item");
                    return;
                }

                if(selectedItem != null){
                    TextInputDialog priceChangeInquiry = new TextInputDialog();
                    priceChangeInquiry.setTitle("Setting a new price");
                    priceChangeInquiry.setHeaderText("Enter a new price for the item: " + selectedItem.getItem());
                    priceChangeInquiry.setContentText("New Price: $");

                    Optional<String> priceInput = priceChangeInquiry.showAndWait();
                    if(priceInput.isPresent()){
                        double NewPrice = Double.parseDouble(priceInput.get().trim());
                        changedPrice = NewPrice;
                        selectedItem.updatePrice(NewPrice);

                        for(CartSystem item : CartDisplay.getItems()){
                            if(item.getItem().equals(selectedItem.getItem())){
                                item.updatePrice(NewPrice);
                                break;
                            }
                        }

                        updatePrices();
                        System.out.println("The price for " + selectedItem.getItem() + " is updated to $" + NewPrice);
                    }
                }
                
            } catch (NumberFormatException e){
                System.out.println("Invalid Input");
            }
        }
    }
}