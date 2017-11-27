/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 *  Trieda PrikazRozpravaj implementuje pre hru prikaz rozpravaj "arg1".
 *  Vypíše hlášku, ktorá je postave priradená pri jej inicializácii.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */

class PrikazRozpravaj implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "rozpravaj";
    private HerniPlan hPlan;
    private Batoh batoh;

    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazRozpravaj(HerniPlan hPlan,Batoh batoh)
    {
        this.hPlan = hPlan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    @Override
    /*
     * Skontroluje sa syntax príkazu, ak je v poriadku postava vráti text z jej premennej hlaska
     * Ak sa rozprávame s krčmárom a nemáme v batohu luk, tak nám dá úlohu, luk a šípy na skolenie vlka
     * 
     */
    public String proved(String... parametre) {
        if (parametre.length < 1) {
            return "neviem, s kým mám rozprávať";
        }
        
        String menoPostavy = parametre[0];
        
        Postava postava = hPlan.getAktualniProstor().najdiPostavu(menoPostavy);
        if (postava == null) {
            return "postava '" + menoPostavy + "' tu nie je";
        }
        if (postava.getMeno().contains("krcmar") && (! batoh.nazvyVeciVBatohu().contains("luk")) ){
            if ( (batoh.getKapacita()-1) > batoh.getPocetVeciVBatohu() ) {
                Vec luk = new Vec("luk","úplne obyčajný luk",true,"luk.jpg");
                Vec sipy = new Vec("sipy","niesom si istý, či tie hroty sú dosť ostré na to aby mohli ublížiť",true,"sipy.jpg");
                batoh.pridaj(luk);
                batoh.pridaj(sipy);
                hPlan.notifyObservers();
                return "Vyzeráš ako schopný človek, nemal by si záujem o úlohu?\n"
                    + "platím dobre .. Ak mi donesieš telo vlka ktorý terorizuje okolie získaš tento meč a knihu o tom ako bojovať\n"
                    + "Dohodneme sa?\n" + "Do batohu bol pridany: " + luk.getNazev() + ": " + luk.getPopis() + "\n"
                    + "Do batohu boli pridane: " + sipy.getNazev();
                }
            else{
                return "Nedostatok miesta v batohu, mal by si niečo vyhodiť.";
            }
        }
        if (postava.getMeno().contains(menoPostavy)) {
            return postava.getHlaska();
        }
        return "Nikoho s týmto menom v priestore nevidím.";
    }
    @Override    
    public String getNazev() {
        return NAZEV;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
