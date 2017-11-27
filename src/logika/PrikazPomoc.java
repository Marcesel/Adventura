package logika;

/**
 *  Trieda PrikazPomoc implementuje pre hru prikaz pomoc.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 * @author     Marcel Češelka
 * @version   0.00.000 * 
 */
class PrikazPomoc implements IPrikaz {
    
    private static final String NAZEV = "pomoc";
    private Batoh batoh;
    
    
    public PrikazPomoc(Batoh batoh) {
        this.batoh = batoh;
    }
    
    /**
     *  Vracia výpis, čo je cieľom hry, aké predmety potrebujem mať so sebou aby som mohol hru
     *  vyhrať a tipy, kde tieto predmety môžem získať.
     *  
     *  @return pomoc s tým, čo mi chýba na to aby som vyhral
     */
    @Override
    public String proved(String... parametry) {
        String hlaska = "na jeho skolenie budeš potrebovať tieto veci: ";
        String pomocneHlasky = "Hints:\n";
        if (!batoh.nazvyVeciVBatohu().contains("cesnak")){
            hlaska += "cesnak, ";
            pomocneHlasky += "Cesnak zvykol rásť u nás na farme.\n";
        }
        if (!batoh.nazvyVeciVBatohu().contains("posveteny_kriz")) {
            hlaska += "posveteny_kriz, ";
            pomocneHlasky += "Kríž, ktorý mi vysel nad posteľou treba posvetit na oltari v kostole.\n";
        }       
        if (!batoh.nazvyVeciVBatohu().contains("mec")){
            hlaska += "mec, ";
            pomocneHlasky += "Na to aby som dostal meč potrebujem splniť úlohu v krčme.\n";           
        }        
        if (!batoh.nazvyVeciVBatohu().contains("kolik")) {
            hlaska += "kolik, ";
            pomocneHlasky += "Mečom by som z konára vedel niečo také vytesať.\n";            
        }       
        if (!batoh.nazvyVeciVBatohu().contains("kniha_o_boji")){
            hlaska += "kniha_o_boji, ";
            pomocneHlasky += "Táto kniha sa dá obvykle získať s mečom.\n";            
        }
        
        return "Cieľom hry je zabiť hrozného drakulu v jeho pevnosti\n"
        + hlaska + "\n"
        + "\n"
        + pomocneHlasky;
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
