/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy. 
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */
public class SeznamPrikazuTest
{
    private Hra hra;
    private PrikazKonec prKonec;
    private PrikazChod prChod;
    private PrikazPouzi prPouzi;
    private PrikazRozpravaj prRozpravaj;
    private Batoh batoh;
    
    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */    
    @Before
    public void setUp() {
        hra = new Hra();
        batoh = new Batoh();
        prKonec = new PrikazKonec(hra);
        prChod = new PrikazChod(hra.getHerniPlan(),batoh);
        prPouzi= new PrikazPouzi(hra.getHerniPlan(),batoh);
        prRozpravaj= new PrikazRozpravaj(hra.getHerniPlan(),batoh);
    }
    
    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
                // neinicializujeme
    }
        /***************************************************************************
     * Test vkládania a odoberania príkazov zo zoznamu pouziteľných príkazov.
     */
    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prChod);
        seznPrikazu.vlozPrikaz(prRozpravaj);         
        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prChod, seznPrikazu.vratPrikaz("chod"));
        assertEquals(prRozpravaj, seznPrikazu.vratPrikaz("rozpravaj"));        
        assertEquals(null, seznPrikazu.vratPrikaz("napoveda"));
    }
    
    /***************************************************************************
     * Test vyhodnocovania príkazov, s tým, či je zadaný príkaz platný alebo nie.
     */    
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prChod);
        seznPrikazu.vlozPrikaz(prRozpravaj);        
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("konec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("chod"));
        assertTrue(seznPrikazu.jePlatnyPrikaz("rozpravaj"));        
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("napoveda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
    }

    /***************************************************************************
     * Test uchovávania platných príkazov v zozname.
     */ 
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prChod);
        seznPrikazu.vlozPrikaz(prRozpravaj);
        seznPrikazu.vlozPrikaz(prPouzi);        
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("chod"));
        assertEquals(true, nazvy.contains("pouzi"));
        assertEquals(true, nazvy.contains("rozpravaj"));        
        assertEquals(false, nazvy.contains("napoveda"));
        assertEquals(false, nazvy.contains("Konec"));
    }
    
}

