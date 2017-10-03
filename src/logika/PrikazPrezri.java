/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 *  Trieda PrikazPrezri implementuje pre hru prikaz prezri "arg1".
 *  Vypíše popis veci, ktorá sa v priestore nachádza.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */
public class PrikazPrezri implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prezri";
    
    private HerniPlan hPlan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazPrezri(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /*
    *   Ak máme zadaný parameter, ktorý nie je null a zároveň sa nachádza v aktuálnom priestore
    *   vypíše sa popis, ktorý je zadaný pri v inicializácii danej veci.
    */
    @Override   
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "neviem, čo mám preskúmať";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = hPlan.getAktualniProstor().odeberVec(nazevVeci);
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        
        hPlan.getAktualniProstor().vlozVec(vec);
        
        return nazevVeci + ": " + vec.getPopis();
    }
    
    @Override    
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
