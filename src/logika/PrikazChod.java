package logika;

/*******************************************************************************
 *  Trieda PrikazChod implementuje pre hru prikaz chod "arg1".
 *  Slúži na pohybovanie sa medzi priestormi a výpisom vecí a postáv v priestoroch.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */

class PrikazChod implements IPrikaz {
    private static final String NAZEV = "chod";
    private HerniPlan hPlan;
    private Batoh batoh;

    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazChod(HerniPlan hPlan, Batoh batoh) {
        this.hPlan = hPlan;
        this.batoh = batoh;
    }
    
    /*
     * Príkaz choď slúži na pohybovanie sa medzi priestormi hry. 
     * Ak prejdeme do lokality, na ktorú niesme pripravený tak automaticke prehráme.
     * 
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám ísť? ";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = hPlan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam sa odtiaľ ísť nedá!";
        }
        // Zabije nas drakula
        if (sousedniProstor.getNazev().equals("prekliata_pevnost") && (! batoh.nazvyVeciVBatohu().contains("cesnak")) ){ 
            hPlan.setPrehra(true);
        }
        // Zabije nas vlk
        if (sousedniProstor.getNazev().equals("vlcia_nora") && (! batoh.nazvyVeciVBatohu().contains("luk"))
            && (! batoh.nazvyVeciVBatohu().contains("sipy"))){ 
            hPlan.setPrehra(true);
        }
        hPlan.setAktualniProstor(sousedniProstor);
        
        return sousedniProstor.dlouhyPopis();
        }

    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    
}
