package logika;


/**
 * Třída Hra - třída představující logiku adventury.
 * 
 * Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan,
 * která inicializuje místnosti hry a vytváří seznam platných příkazů a instance tříd
 * provádějící jednotlivé příkazy. Vypisuje uvítací a ukončovací text hry. Také
 * vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Marcel Češelka
 * @version   0.00.000
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;


    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        Batoh batoh = new Batoh();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazPouzi(herniPlan,batoh));
        platnePrikazy.vlozPrikaz(new PrikazZahod(herniPlan,batoh));
        platnePrikazy.vlozPrikaz(new PrikazChod(herniPlan,batoh));
        platnePrikazy.vlozPrikaz(new PrikazVezmi(herniPlan,batoh));
        platnePrikazy.vlozPrikaz(new PrikazPrezri(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazRozpravaj(herniPlan,batoh));
        platnePrikazy.vlozPrikaz(new PrikazPrezriBatoh(batoh));
        platnePrikazy.vlozPrikaz(new PrikazPomoc(batoh));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));        
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vitajte!\n" +
               "Toto je príbeh statočného farmára ktorému jeden večer zmenil celý život. \n" +
               "Na malej farme kde vyrastal od svojho detstva sa odohralo veľké nešťastie. \n" +
               "Jedného večera priletela neznáma bytosť a bolo počuť hrozný zvuk z maštale ktorá patrila rodičom. \n" +
               "Keď to išli rodičia skontrolovať, ani jeden z nich sa už nevrátil.\n" +
               "\n" +
               "Napíš 'napoveda', ak nevieš aké príkazy používať.\n" +
               "Napíš 'pomoc', ak chceš zistiť, čo ti treba na to aby si vyhral hru.\n" +
               "Napíš 'konec' ak už nechceš ďalej hrať." +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Dúfam, že sa ti hra páčila a diky, že si si zahral.  Ahoj.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
        }
        else {
            textKVypsani="Tento príkaz nepoznám. ";
        }
        // Hra sa ukončí ako vyhraná keď je drakula porazeny
        if (herniPlan.isVyhra()) {
                konecHry = true;
                textKVypsani = "Po veľmi náročnom boji sa ti podarilo drakulu premôcť vďaka manévru ktorý si sa naučil\n"
                               + "keď ležal na zemi zabodol si mu kôl do srdca a strčil cesnak do úst "
                               + "v tom momente sa premenil na prach a ty si si uvedomil, že tvoja úloha je skončená.";
        }
        // Hra sa ukončí ako prehraná keď vojdeme do oblasti, na ktorú niesme vybavení       
        if (herniPlan.isPrehra()){
                konecHry = true;
                textKVypsani = "Podcenil si situáciu, ani si nestihol zareagovať a padáš mŕtvy k zemi." ;           
        }
        
        
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní IPrikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}
