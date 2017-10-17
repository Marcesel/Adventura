/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;



/**
 *
 * @author john
 */
public class main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        IHra hra = new Hra();
        BorderPane borderPane = new BorderPane();
        
        
        TextArea centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);        
        borderPane.setCenter(centralText);
        
        Label zadejPrikaz = new Label("zadej prikaz");
        zadejPrikaz.setFont(Font.font("Arial",FontWeight.BOLD, 14));
        
        TextField zadejPrikazTextArea = new TextField();
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {                
                String vstupniPrikaz = zadejPrikazTextArea.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                
                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");
                
                zadejPrikazTextArea.setText("");
                
                if (hra.konecHry()) {
                    zadejPrikazTextArea.setEditable(false);
                    centralText.appendText(hra.vratEpilog());
                }
            }
        });
        
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikaz, zadejPrikazTextArea);
        borderPane.setBottom(dolniLista);
        
        Scene scene = new Scene(borderPane, 600, 400);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
