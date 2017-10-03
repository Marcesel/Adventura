package logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Riha, Marcel Češelka
 * @version   0.00.000
 */
class PrikazNapoveda implements IPrikaz {
    
    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;
    
    /**
     *  Konstruktor třídy
     *  
     *  @param platnePrikazy seznam příkazů,
     *                       které je možné ve hře použít,
     *                       aby je nápověda mohla zobrazit uživateli. 
     */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    
    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *  
     *  @return napoveda ke hre
     */
    @Override
    public String proved(String... parametry) {
        return "Cieľom hry je zabiť hrozného drakulu v jeho pevnosti\n"
        + "a priniesť tak pokoj celému údoliu.\n"
        + "\n"
        + "Môžes zadávať tieto príkazy:\n"
        + platnePrikazy.vratNazvyPrikazu()
        + "\n\n" + "Syntax:\n pouzi 'arg1' 'arg2' \n zahod 'arg1'\n chod 'arg1'\n vezmi 'arg1'\n"
        + " prezri 'arg1'\n rozpravaj 'arg1'\n prezri_batoh\n pomoc\n konec\n";
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
