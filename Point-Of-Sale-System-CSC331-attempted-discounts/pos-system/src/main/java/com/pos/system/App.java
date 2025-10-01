package com.pos.system;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App extends Application {

    private static Scene scene;
    private static final Map<String, Parent> loadedFXML = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {
        //First Scene
        scene = new Scene(loadFXML("primary"));


        //Image for the program
        Image icon = new Image("file:pos-system\\src\\main\\Extra\\icon.jpg");
        stage.getIcons().add(icon);

        //Sets the scene and display the main menu
        stage.setTitle("Point of Sale System - Rykov's Redeemer");
        stage.setScene(scene);
        stage.show();
    }


    //I am guessing this is how you transition between scenes
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        
    }

    private static Parent loadFXML(String fxml) throws IOException {
        if (!loadedFXML.containsKey(fxml)) {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
            Parent root = fxmlLoader.load();
            loadedFXML.put(fxml, root); 
        }
        return loadedFXML.get(fxml);
    }

    public static void main(String[] args) {
        launch();
    }

}