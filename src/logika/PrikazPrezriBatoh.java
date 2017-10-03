/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 *  Trieda PrikazBatoh implementuje pre hru prikaz prezri_batoh.
 *  Vypíše názvy vecí, ktoré sa v baťohu nachádzajú.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */
class PrikazPrezriBatoh implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prezri_batoh";
    private Batoh batoh;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazPrezriBatoh(Batoh batoh)
    {
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    // Jednoduchý výpis vecí, ktoré máme v batohu
    @Override
    public String proved(String... parametry) {
        if ( batoh.getPocetVeciVBatohu() == 0 ){
            return "V batohu niesu žiadne veci. ";
        }
        return batoh.nazvyVeciVBatohu();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
