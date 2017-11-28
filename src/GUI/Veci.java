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
import javax.swing.border.TitledBorder;
import logika.HerniPlan;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 * Třída Veci - třída používa návrhový vzor Observer a implementuje grafickú
 * spravu veci v prostoru.
 * 
 * Tato třída vytváří instanci třídy Veci, 
 * která inicializuje listview pro zobrazení veci v prostoru a  vytvára metody, ktere
 * s nimi můžou pracovat.
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */
public class Veci implements Observer{
    private IHra hra;
    ListView<Object> list;
    ObservableList<Object> data;    
    private TextArea centralText;
    
    /*
    * Kontruktor tridy.
    */     
    public Veci(IHra hra, TextArea text) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        centralText = text;
        
        init();        
    }
    @Override
    public void update() {
        Map<String, Vec> veci;
        veci = hra.getHerniPlan().getAktualniProstor().getZoznamVeci();
        data.clear();
        
        for (Vec vec : hra.getHerniPlan().getAktualniProstor().getZoznamVeci().values())
        {
            ImageView obrazok = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/"+vec.getObrazok()), 80,80, false, false));   
            data.add(obrazok);             
        }
        
        }

    /*
    * Inicializace predmetov v priestore. Vytvori novy listview a nacita veci z aktualniho
    * prostoru. Na kliknuti mysou na nazev veci se dana vec prida do batohu. 
    */
    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
       // list.setPrefWidth(200);       
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
                        veci = hra.getHerniPlan().getAktualniProstor().getZoznamVeci();
                        Integer index = list.getSelectionModel().getSelectedIndex();
                        
                        Integer poradie = 0;
                        String nazovVeci = new String();
                        for (String i : veci.keySet() )
                        {
                            if (index.equals(poradie) )
                            {
                                String vstupnyPrikaz = "vezmi "+ i;
                                String odpovedHry = hra.zpracujPrikaz("vezmi "+i);               
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
    public ListView<Object> getList() {
        return list;
    }    
    
    public void newGame(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);
        
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }    
}
