/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;
import GUI.Vychody;

/**
 * Třída MenuLista - třída používa návrhový vzor Observer a implementuje listu s
 * moznostami.
 * 
 * Tato třída vytváří instanci třídy MenuLista, 
 * ktera obsahuje nekolik funkcionalit na start novej hry, konec hry, napovedu
 * a moznost o programe.
 * @author xzenj02, Marcel Češelka
 */
public class MenuLista extends MenuBar{
    
    private IHra hra;
    private Main main;
    private Vychody seznamVychodu;
    /*
    * Kontruktor tridy.
    */    
    public MenuLista(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        init();
    }
    
    /*
    * Inicializace listy. Vytvori boxy s moznostou spustit Novu hru, skoncit hru
    * zobrazit info o programe a napovedu.    
    */    
    private void init(){
        
        Menu novySoubor = new Menu("Adventura");
        Menu napoveda = new Menu("Pomoc");
        
        MenuItem novaHra = new MenuItem("Nova hra");
        //, new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/ikona.png")))
        
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        MenuItem konecHry = new MenuItem("Koniec hry");
        konecHry.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        
        novySoubor.getItems().addAll(novaHra, konecHry);
        
        MenuItem oProgramu = new MenuItem("O programe");
        MenuItem napovedaItem = new MenuItem("Napoveda");
        
        napoveda.getItems().addAll(oProgramu, napovedaItem);
        
        this.getMenus().addAll(novySoubor, napoveda);
        
        konecHry.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        novaHra.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                hra = new Hra();
                main.getMapa().newGame(hra);
                main.setHra(hra);
                main.getCentralText().setText(hra.vratUvitani());
                main.getVychody().newGame(hra);
                main.getVeci().newGame(hra);
                main.getBatoh().newGame(hra);
            }
        });
        
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);
                
                oProgramuAlert.setTitle("O programe");
                oProgramuAlert.setHeaderText("Adventúra o statočnom farmárovi a drakulovi.");
                oProgramuAlert.setContentText("Rozšírená adventúra odovzdaná na Programování v Jave o grafické prostredie.");
                oProgramuAlert.initOwner(main.getStage());
                
                oProgramuAlert.showAndWait();
            }
        });
        
        napovedaItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Stage stage = new Stage();
                stage.setTitle("Napoveda");
                
                WebView webView = new WebView();
                
                webView.getEngine().load(Main.class.getResource("/zdroje/upravenaNapoveda.html").toExternalForm());
                
                stage.setScene(new Scene(webView, 500,500));
                stage.show();
            
            }
        });
        
        
    }
    
}
