/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import logika.HerniPlan;
import logika.IHra;
import logika.Prostor;
import utils.Observer;


/**
 *
 * @author Marcel
 */
public class Vychody extends JPanel implements Observer{

    private IHra hra;
    private TextArea centralText;
    ListView<String> list;
    ObservableList<String> data;    
    private DefaultListModel model;  
    private Prostor prostor;
    private TextField zadejPrikazTextArea;
    
    public Vychody(IHra hra, TextArea text,TextField field) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        centralText = text;
        prostor = hra.getHerniPlan().getAktualniProstor();
        zadejPrikazTextArea = field;
        
        init();
    }
    @Override
    public void update() {
        
        String vychody = hra.getHerniPlan().getAktualniProstor().popisVychodu();
        data.clear();
        
        String[] oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
        }
    }

    public void newGame(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);
        
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    
    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
     //   list.setPrefWidth(100);
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {                
                String zvolenyPriestor = new String();
                if (event.getClickCount() == 1) 
                {
                    if (! hra.konecHry() )
                    {
                   zvolenyPriestor = list.getSelectionModel().getSelectedItem();
                    if ( ! zvolenyPriestor.isEmpty() ) 
                    {
                    String odpovedHry = hra.zpracujPrikaz("chod "+ zvolenyPriestor);
                
                    centralText.appendText("\n" + "chod "+ zvolenyPriestor);
                    centralText.appendText("\n" + odpovedHry + "\n");            
                    
                    hra.getHerniPlan().notifyObservers();
                    }
                    }
                }
            }
        });
        
        update();
    }
  
    public ListView<String> getList()
      {
        return list;
      }
    
    
}
