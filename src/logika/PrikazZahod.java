/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 *  Trieda PrikazZahod implementuje pre hru prikaz zahod "arg1".
 *  Zahodí z batohu vec, ktorej názov uvedemie namiesto "arg1"
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */
public class PrikazZahod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "zahod";
    private HerniPlan hPlan;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazZahod(HerniPlan hPlan, Batoh batoh)
    {
        this.hPlan = hPlan;
        this.batoh = batoh;        
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /*
     * Ak je syntax správna a máme čo z batohu zahodiť tak to vyberieme z batohu a vložíme do priestoru
     */
    @Override    
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "neviem, čo mám zahodiť";
        }
        
        if (batoh.getPocetVeciVBatohu() == 0) {
               return "v batohu nič nie je";
           }        
        String nazovVeci = parametry[0];        
        Prostor aktualnyPriestor = hPlan.getAktualniProstor();        
        Vec vec = batoh.vyber(nazovVeci);       
        if (vec == null) {
            return "vec '" + nazovVeci + "' v batohu nie je";
        }
        
        


        aktualnyPriestor.vlozVec(vec);
           
        return "vec '" + nazovVeci + "' si vyhodil do batohu";
    }

    @Override    
    public String getNazev() {
        return NAZEV;
    }


    //== Soukromé metody (instancí i třídy) ========================================

}
