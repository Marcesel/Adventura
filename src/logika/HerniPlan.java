package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;


/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Marcel Češelka
 * @version   0.00.000
 */
public class HerniPlan implements Subject {

    private Prostor aktualniProstor;
    private boolean prehra = false;
    private boolean vyhra = false;
    
    private List<Observer> listObserveru = new ArrayList<Observer>();
    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor farma = new Prostor("farma","Farma, na ktorej si vyrastal",300,30);
        Prostor mastal = new Prostor("mastal", "Mastal, kedysi tu bol dobytok, ako sa mi to mohlo stať ..",450,30);
        Prostor luka = new Prostor("luka","Luka, rozsiahla lúka pri farme",300,140);
        Prostor temny_les = new Prostor("temny_les","Temny les, v ktorom nie je skoro žiadne svetlo",165,135);
        Prostor prekliata_pevnost = new Prostor("prekliata_pevnost","Prekliata pevnost, na svete nie je nebezpecnejsieho miesta",30,135);
        Prostor mesto = new Prostor("mesto","Mesto, obkolesené vysokými hradbami",300,260);
        Prostor kostol = new Prostor("kostol","Kostol, nádherný kostol plný obrazov",440,260);
        Prostor krcma = new Prostor("krcma","Krcma, je tu plno špiny a podivný zápach",155,260);
        Prostor smrekovy_les = new Prostor("smrekovy_les","Smrekovy les, ihličnatý les plný smrekov a borovíc",300,365);
        Prostor vlcia_nora = new Prostor("vlcia_nora","Vĺčia nora, možno tu niečo býva",300,490);


        // přiřazují se průchody mezi prostory (sousedící prostory)
        farma.setVychod(mastal);
        farma.setVychod(luka);        
        mastal.setVychod(farma);
        luka.setVychod(farma);
        luka.setVychod(mesto);
        luka.setVychod(temny_les);
        temny_les.setVychod(luka);
        temny_les.setVychod(prekliata_pevnost);
        prekliata_pevnost.setVychod(temny_les);
        mesto.setVychod(luka);
        mesto.setVychod(kostol);
        mesto.setVychod(krcma);
        mesto.setVychod(smrekovy_les);
        krcma.setVychod(mesto);
        kostol.setVychod(mesto);
        smrekovy_les.setVychod(mesto);
        smrekovy_les.setVychod(vlcia_nora);
        vlcia_nora.setVychod(smrekovy_les);

        // vytvoříme několik věcí
        Vec kriz = new Vec("kriz", "Drevený kresťanský kríž", true,"kriz.jpg");
        Vec strom = new Vec("strom", "Vyzerá, že by to mohol byť dub", false,"strom.jpg");
        Vec cibula = new Vec("cibula", "Úplne obyčajná cibuľa", true,"cibula.jpg");
        Vec cesnak = new Vec("cesnak", "Cesnakové strúčiky aj s vňaťkou", true,"cesnak.jpg");
        Vec mrtva_krava = new Vec("mrtva_krava", "Vidím dve rany na krku po kusnutí", false,"mrtva_krava.jpg");
        Vec slama = new Vec("slama", "Obyčajná vysušená tráva", true,"slama.jpg");
        Vec cedula = new Vec("cedula", "Mesto - západ 10 km, Farma - východ 5 km", false,"cedula.jpg");
        Vec vyschnuty_konar = new Vec("vyschnuty_konar", "Možno sa mi na niečo bude hodiť", false,"vyschnuty_konar.jpg");
        Vec pavucina = new Vec("pavucina", "Muselo to pavúkovi trvať veľmi dlho", false,"pavucina.jpg");
        Vec hromada_kosti = new Vec("hromada_kosti","Sú to ľudské a zvieracie kosti",false,"hromada_kosti.jpg");
        Vec metla = new Vec("metla","Niekto si po ňu pravdepodobne každú chvíľu príde",true,"metla.jpg");
        Vec vysoky_smrek = new Vec("vysoky_smrek","Tento sa zdá byť zo všetkých najvyšší",false,"vysoky_smrek.jpg");
        Vec vystrazna_cedula = new Vec("vystrazna_cedula","Dávajte si pozor na vlka!!",false,"vystrazna_cedula.jpg");
        Vec ovcie_kosti = new Vec("ovcie_kosti","Ten vlk musel skoliť aspoň tucet oviec",false,"ovcie_kosti.jpg");
        Vec oltar = new Vec("oltar","Kamenný oltár na ktorom horia sviečky",false,"oltar.jpg");
        
        // umístíme věci do prostorů
        farma.vlozVec(kriz);
        farma.vlozVec(strom);
        farma.vlozVec(cibula);
        farma.vlozVec(cesnak);
        mastal.vlozVec(mrtva_krava);
        mastal.vlozVec(slama);
        luka.vlozVec(cedula);
        temny_les.vlozVec(vyschnuty_konar);
        temny_les.vlozVec(pavucina);
        prekliata_pevnost.vlozVec(hromada_kosti);
        mesto.vlozVec(metla);
        kostol.vlozVec(oltar);
        smrekovy_les.vlozVec(vysoky_smrek);
        smrekovy_les.vlozVec(vystrazna_cedula);
        vlcia_nora.vlozVec(ovcie_kosti);

        //vytvorime niekolko postav
        Postava knaz = new Postava("knaz","Čo ti toľko trvá? Rýchlo utekaj do mesta, to monštrum sa ešte môže vrátiť.\n" + 
                                          "Ak máte na farme nejaký kríž a cesnak tak si ho vezmi, možno ti to zachráni život.\n" + 
                                          "Ja tu ostanem a budem sa snažiť odšpiniť túto pôdu od zla.");
        Postava drakula = new Postava("drakula", "Ja som tvoja smrť");
        Postava strazca = new Postava("strazca","Nerob žiadne problémy. Ja ťa vidím");
        Postava krcmar = new Postava("krcmar","Co si das? Toho pijana si nevšímaj");
        Postava pijan = new Postava("pijan","EEE, čo chceš? daj mi pokoj");
        Postava farar = new Postava("farar","Počul som, čo sa ti stalo, niesi jediný, ak chceš poraziť zlo musíš sa na to pripraviť\n"
                                +   "Choď za krčmárom, možno ti bude vedieť pomôcť.\n"
                                +   "Odporúčam ti ale posvetiť si kríž na našom oltáry, môže sa to hodiť.");
        Postava vlk = new Postava("vlk","RRRRRRRRR");
        
        // vlozime postavy do priestorov
        farma.vlozPostavu(knaz);
        prekliata_pevnost.vlozPostavu(drakula);
        mesto.vlozPostavu(strazca);
        kostol.vlozPostavu(farar);
        krcma.vlozPostavu(pijan);
        krcma.vlozPostavu(krcmar);
        vlcia_nora.vlozPostavu(vlk);
        
        aktualniProstor = farma;  // hra začíná na farme
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       notifyObservers();
    }
    // Zistí, aká je hodnota premennej vyhra
    public boolean isVyhra() {
        return vyhra;
    }   
    // Nastaví hodnotu premenne vyhra na hodnotu z parametra
    public void setVyhra(boolean vyhra){
        this.vyhra = vyhra;
    }
    // Zistí, aká je hodnota premennej prehra
    public boolean isPrehra(){
        return prehra;
    }
    // Nastaví hodnotu premenne prehra na hodnotu z parametra    
    public void setPrehra(boolean prehra){
        this.prehra = prehra;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru){
            listObserveruItem.update();
        }
        
    }
    
}

