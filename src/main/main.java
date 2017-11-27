/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.BatohGUI;
import GUI.Mapa;
import GUI.MenuLista;
import GUI.Veci;
import GUI.Vychody;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.border.TitledBorder;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author xzenj02
 */
public class Main extends Application {

    private TextArea centralText;   
    private IHra hra;

    public void setHra(IHra hra) {
        this.hra = hra;
    }
    private TextField zadejPrikazTextArea;
    
    private Mapa mapa;
    private MenuLista menuLista;
    private Vychody vychody;
    private BatohGUI batohGUI;
    private Veci veci;
    
    private Stage stage;
 


    
    @Override
    public void start(Stage primaryStage) {
        this.setStage(primaryStage);
        
        hra = new Hra();
        
        mapa = new Mapa(hra);
        menuLista = new MenuLista(hra, this);

        
        BorderPane borderPane = new BorderPane();
        
        // Text s prubehem hry
        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);

  
        vychody = new Vychody(hra, centralText, zadejPrikazTextArea);        
        batohGUI = new BatohGUI(hra, centralText);
        veci = new Veci(hra, centralText);        
        //label s textem zadej prikaz
        Label zadejPrikazLabel = new Label("Zadaj príkaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        // text area do ktere piseme prikazy
        zadejPrikazTextArea = new TextField("");
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (! hra.konecHry() )
                {
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
                
            }
        });
 
        Label batohLbl = new Label("Batoh: ");
        Label veciLbl = new Label("Veci v priestore: ");
        Label vychodyLbl = new Label("Východy: ");
        
        // Vytvaram label pre batoh
        VBox batohVyber = new VBox();
        batohVyber.setSpacing(10);
        batohVyber.getChildren().addAll(batohLbl, batohGUI.getList());
        // Vytvaram label pre veci v priestore
        VBox veciVyber = new VBox();
        veciVyber.setSpacing(10);
        veciVyber.getChildren().addAll(veciLbl, veci.getList());        
         // Vytvaram label pre vychody z priestoru
        VBox vychodyVyber = new VBox();
        vychodyVyber.setSpacing(10);
        vychodyVyber.getChildren().addAll(vychodyLbl, vychody.getList());      
        
        
        //dolni lista s elementy
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel,zadejPrikazTextArea);
        
        borderPane.setLeft(mapa);
        borderPane.setBottom(dolniLista);
        borderPane.setRight(vychodyVyber);       
        borderPane.setTop(menuLista);
        
        BorderPane strednyBorderPane = new BorderPane();       
        strednyBorderPane.setCenter(centralText);

        BorderPane strednySpodnyBorderPane = new BorderPane();
        strednySpodnyBorderPane.setRight(batohVyber);
        strednySpodnyBorderPane.setBorder(Border.EMPTY);
        strednySpodnyBorderPane.setLeft(veciVyber);  
        strednySpodnyBorderPane.setPrefHeight(300);
        strednySpodnyBorderPane.topProperty();
        
        strednyBorderPane.setBottom(strednySpodnyBorderPane);        
        borderPane.setCenter(strednyBorderPane);

        
        Scene scene = new Scene(borderPane, 1050, 600);
        primaryStage.setTitle("Adventúra");

        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }

    public TextArea getCentralText() {
        return centralText;
    }

    public Mapa getMapa() {
        return mapa;
    }
    public Vychody getVychody() {
        return vychody;
    }  
    public Veci getVeci() {
        return veci;
    } 
    public BatohGUI getBatoh() {
        return batohGUI;
    }        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatný parameter");
                System.exit(1);
            }
        }
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
