/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 *  Trieda Pouzi implementuje pre hru prikaz pouzi "arg1" "arg2".
 *  arg1 predstavuje vec, ktorú chceme použiť a arg2 objekt, na ktorý chceme danú vec použiť
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */

public class PrikazPouzi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "pouzi";    
    private HerniPlan hPlan;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazPouzi(HerniPlan hPlan, Batoh batoh)
    {
        this.hPlan = hPlan;      
        this.batoh = batoh;        
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /*
     * Overí sa syntax príkazu, ak je správna, tak sa podľa toho čo používame a na čo to používame
     * splní jedna z daných IF príkazov.
     */
    @Override    
    public String proved(String... parametre) {
        if (parametre.length != 2) {
            return "neviem, čo mám ako použiť";
        }
        
        String nazov1 = parametre[0];
        String nazov2 = parametre[1];
        final String HLASKA_PRID_BATOH = "Do batohu bol pridany: ";
        final String HLASKA_MEC = "mec";
        
        Vec vec = batoh.vyber(nazov1);
        Prostor aktualnyPriestor = hPlan.getAktualniProstor(); 
        if (vec == null) {
            return "vec '" + nazov1 + "' tu nie je";
        }
        // Posvetíme si kríž tým, že ho použijeme na oltár
        else if (vec.getNazev().equals("kriz") && nazov2.equals("oltar") ) {
            Vec posveteny_kriz = new Vec("posveteny_kriz","Žiary z neho pozitívna energia.",true);
            batoh.pridaj(posveteny_kriz);
            return HLASKA_PRID_BATOH + posveteny_kriz.getNazev() + ": " + posveteny_kriz.getPopis();
        }
        /*
         * Vlka skolíme tak, že použijeme luk na vlka v batohu máme šípy, postava vlka zmizne a nám sa do
         * batohu pridá mŕtvy vlk
        */ 
        else if (vec.getNazev().equals("luk") && hPlan.getAktualniProstor().najdiPostavu(nazov2) != null
                    && nazov2.equals("vlk") && batoh.nazvyVeciVBatohu().contains("sipy")) {
            if( batoh.getKapacita() == batoh.getPocetVeciVBatohu() ){
                return "Nemám miesto v batohu, pred bojom by som mal niečo zahodiť.";
            }
            aktualnyPriestor.odoberPostavu("vlk");
            batoh.pridaj(vec);
            Vec mrtvy_vlk = new Vec("mrtvy_vlk","ten už nikomu neuškodí",true);
            batoh.pridaj(mrtvy_vlk);
            return  "Tvoj šíp zasiahol vlka priamo do krku .. Vlk padá na zem a je v momente mŕtvy\n"
                    + HLASKA_PRID_BATOH + mrtvy_vlk.getNazev() + ": " + mrtvy_vlk.getPopis();
        }
        /*
         * Úlohu, ktorú nám krčmár zadal splníme tým, že naňho použijeme mŕtveho vlka. Ak máme miesto v
         * batohu tak nám za odmenu dá meč a knihu o boji.
         */
        else if (vec.getNazev().equals("mrtvy_vlk") && hPlan.getAktualniProstor().najdiPostavu(nazov2) != null
                    && nazov2.equals("krcmar") ) {
            if( (batoh.getKapacita()-1) <= batoh.getPocetVeciVBatohu() ){
                return "Nemám miesto v batohu, pred splnením úlohy by som si mal nejaké spraviť.";
            }
            Vec mec = new Vec(HLASKA_MEC,"tento meč je predurčený veľkým veciam",true);                        
            Vec kniha_o_boji = new Vec("kniha_o_boji","základom úspechu je obrana, obrana, útok",true);           
            batoh.pridaj(mec);
            batoh.pridaj(kniha_o_boji);
            return "Dobrá práca!! Konečne sa našiel niekto schopný.\n" + "Vezmi si tento meč a knihu o boji, myslím že ti v budúcnosti pomôže\n"
                    + HLASKA_PRID_BATOH + mec.getNazev() + ": " + mec.getPopis() + "\n"
                    + HLASKA_PRID_BATOH + kniha_o_boji.getNazev() + ": " + kniha_o_boji.getPopis();
            }
        /*
         * Posledným predmetom na skolenie drakulu je kolík, ktorý vytesáme zo suchého konára pomocou meča
         */
        else if (vec.getNazev().equals(HLASKA_MEC) && hPlan.getAktualniProstor().odeberVec("vyschnuty_konar") != null ) {
            if( batoh.getKapacita() == batoh.getPocetVeciVBatohu() ){
                return "Nemám miesto v batohu, ak chcem vytesať kolík, musím niečo zahodiť.";
            }            
            Vec kolik = new Vec("kolik","drevený kolík, ktorý poslúži svojmu účelu",true);
            batoh.pridaj(vec);
            batoh.pridaj(kolik);
            return HLASKA_PRID_BATOH + kolik.getNazev() + ": " + kolik.getPopis();
            }
        /*
         * Ak máme všetky predmety so sebou a použijeme na drakulu meč vyhráme
         */
        else if (vec.getNazev().equals(HLASKA_MEC) && hPlan.getAktualniProstor().najdiPostavu(nazov2) != null
                    && nazov2.equals("drakula") && batoh.nazvyVeciVBatohu().contains("cesnak") 
                     && batoh.nazvyVeciVBatohu().contains("kniha_o_boji") && batoh.nazvyVeciVBatohu().contains("posveteny_kriz") 
                    && batoh.nazvyVeciVBatohu().contains("kolik")) { 
            hPlan.setVyhra(true);
            return null;                      
            }
        /*
         * Ak nám nejaký predmet chýba alebo používame na drakulu luk, vráti sa hláška, že ho takto neporazíme
         */
        else if ( (vec.getNazev().equals(HLASKA_MEC) || vec.getNazev().equals("luk") ) && hPlan.getAktualniProstor().najdiPostavu(nazov2) != null
                    && nazov2.equals("drakula") ) { 
            batoh.pridaj(vec);
            return "Môžem sa snažiť koľko chcem, neviem ho takto zabiť";
            }
        batoh.pridaj(vec);
        return "neviem, čo mám robiť, možno by som si mal skúsiť pozrieť nápovedu";
    }
    
    @Override    
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
