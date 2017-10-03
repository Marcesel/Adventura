/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 *  Trieda PrikazVezmi implementuje pre hru prikaz vezmi "arg1".
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    
    private HerniPlan herniPlan;
    private Batoh batoh;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazVezmi(HerniPlan hPlan, Batoh batoh)
    {
        this.herniPlan = hPlan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    @Override    
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "neviem, čo mám zobrať";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = herniPlan.getAktualniProstor().odeberVec(nazevVeci);
        if (vec == null) {
            return "vec '" + nazevVeci + "' tu nie je";
        }
        // Overenie, či vec môžeme so sebou zobrať
        if (!vec.isPrenositelna()) {
            herniPlan.getAktualniProstor().vlozVec(vec);
            return "vec '" + nazevVeci + "' so sebou brať nechceš";
        }
        
        // Ak nie je batoh plný, vec sa doňho vloží        
        if (batoh.getKapacita() == batoh.getPocetVeciVBatohu()) {
           herniPlan.getAktualniProstor().vlozVec(vec);
           return "batoh je plný, možno by som mal niečo zahodiť";
        }
        else {
            batoh.pridaj(vec);
        }
           
        return "vec '" + nazevVeci + "' si vložil do batohu";
    }

    @Override    
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
