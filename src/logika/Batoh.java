/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.Map;
import java.util.HashMap;


/*******************************************************************************
 * Instance třídy Batoh popisuje objekt Batoh, ktorý postava používa na prenášanie vecí v rámci 
 * priestorov hry.
 * 
 * @author    Marcel Češelka
 * @version   0.00.000
 */
public class Batoh
{
    //== Datové atributy (statické i instancí)======================================
    private static final int KAPACITA = 8;
    private Map<String, Vec> zoznamVeci;
    
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Batoh()
    {
        zoznamVeci = new HashMap<>();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public boolean pridaj(Vec vec){
        if (zoznamVeci.size() < KAPACITA ){
            zoznamVeci.put(vec.getNazev(), vec);
            return true;
        }
        return false;
    }
  
    public Vec vyber(String vecNazov){
        Vec najdenaVec;
        if( zoznamVeci.containsKey(vecNazov)){
            najdenaVec = zoznamVeci.get(vecNazov);
            zoznamVeci.remove(vecNazov);
            return najdenaVec;
        }
     
        return null;
    }
    
    public String nazvyVeciVBatohu(){
        String vypis = "veci v batohu: ";
        for (String vecNazov : zoznamVeci.keySet() ){
            vypis += vecNazov + " ";        
        }
        return vypis;
    }
    // Getr na kapacitu
    public int getKapacita(){
        return KAPACITA;
    }
    
    public int getPocetVeciVBatohu(){
        return zoznamVeci.size();
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
