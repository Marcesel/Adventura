/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 * Třída BatohGUI - třída používa návrhový vzor Observer a implementuje grafickú správu batohu.
 * 
 * Tato třída vytváří instanci třídy BatohGUI, 
 * která inicializuje listview pro zobrazení batohua vytvára metody, ktere
 * s BatohGUI můžou pracovat.
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */
public class BatohGUI implements Observer{
    private IHra hra;
    ListView<Object> list;
    ObservableList<Object> data;
    private TextArea centralText;

    /**
     *
     * @param hra nacita instanci aktualnej hry
     * @param text nacita instanci textoveho pole pro prikazy
     */

    public BatohGUI(IHra hra, TextArea text) {
       this.hra = hra;
       hra.getHerniPlan().registerObserver(this);
       centralText = text;
       
        init();
    }
    /**
     *  Aktualizace predmetov v priestore.
     */
    @Override
    public void update() {
        Map<String, Vec> veci;
        veci = hra.getBatoh().getZoznamVeci();
        data.clear();
        
        for (Vec vec : hra.getBatoh().getZoznamVeci().values())
        {
            ImageView obrazok = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/"+vec.getObrazok()), 80,80, false, false));   
            data.add(obrazok);             
        }               
    }
    /*
    * Inicializace batohu. Vytvori novy listview a nacita veci, ktore sa v batohu
    * nachadzaju. Na kliknuti mysou sa dany predmet zahodi z inventaru do aktualneho
    * priestoru.
    */
    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(200);
 
         list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {                
                if (event.getClickCount() == 1) 
                {
                    if (! hra.konecHry() )
                    {
                        Map<String, Vec> veci;
                        veci = hra.getBatoh().getZoznamVeci();
                        Integer index = list.getSelectionModel().getSelectedIndex();
                        
                        Integer poradie = 0;
                        String nazovVeci = new String();
                        for (String i : veci.keySet() )
                        {
                            if (index.equals(poradie) )
                            {
                                String vstupnyPrikaz = "zahod "+ i;
                                String odpovedHry = hra.zpracujPrikaz("zahod "+i);               
                                centralText.appendText("\n" + vstupnyPrikaz);
                                centralText.appendText("\n" + odpovedHry + "\n");
                                
                                update();
                                break;
                            }
                            poradie++;
                        }
                    
        
                    
                    hra.getHerniPlan().notifyObservers();
                    }
                }
            }
        });       
        
        update();
    }
    
    /**
     *
     * @return vraci instanci listu objektov z batohu
     */
    public ListView<Object> getList() {
        return list;
    }
    /*
    * Aktualizace batohu na pociatocny stav.
    */

    /**
     *
     * @param novaHra nacita instanci nove hry
     */

    public void newGame(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);
        
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    
    
}
