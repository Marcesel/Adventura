/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování třídy. 
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */
public class HraTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------
    private Hra hra1;
    final static String HLASKA1 = "chod mesto";
    final static String HLASKA2 = "chod luka";
    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        hra1 = new Hra();
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
     * Test priebehu hry s tým, že sa hra ukončí príkazom koniec
     */
    @Test
    public void testPriebehHry()
    {
        assertEquals("farma",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz(HLASKA2);
        assertFalse(hra1.konecHry());
        assertEquals("luka",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("konec");
        assertTrue(hra1.konecHry());        
    }
    
    /***************************************************************************
     * Test priebehu hry s tým, že hráč prehrá
     */
    @Test
    public void testPriebehHryPrehra()
    {
        assertEquals("farma",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz(HLASKA2);
        assertFalse(hra1.konecHry());
        assertEquals("luka",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("chod temny_les");
        hra1.zpracujPrikaz("chod prekliata_pevnost");
        assertTrue(hra1.konecHry());        
    }
    /***************************************************************************
     * Test priebehu hry s tým, že hráč vyhrá
     */
    @Test
    public void testPriebehHryVyhra()
    {
        assertEquals("farma",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("vezmi kriz");        
        hra1.zpracujPrikaz("vezmi cesnak");        
        hra1.zpracujPrikaz(HLASKA2);
        hra1.zpracujPrikaz(HLASKA1);
        hra1.zpracujPrikaz("chod kostol");
        hra1.zpracujPrikaz("pouzi kriz oltar");
        hra1.zpracujPrikaz(HLASKA1);
        hra1.zpracujPrikaz("chod krcma");
        hra1.zpracujPrikaz("rozpravaj krcmar");        
        hra1.zpracujPrikaz(HLASKA1);        
        hra1.zpracujPrikaz("chod smrekovy_les");
        hra1.zpracujPrikaz("chod vlcia_nora");        
        hra1.zpracujPrikaz("pouzi luk vlk");        
        hra1.zpracujPrikaz("chod smrekovy_les");        
        hra1.zpracujPrikaz(HLASKA1);
        hra1.zpracujPrikaz("chod krcma");
        hra1.zpracujPrikaz("pouzi mrtvy_vlk krcmar");
        hra1.zpracujPrikaz(HLASKA1);
        hra1.zpracujPrikaz(HLASKA2);
        hra1.zpracujPrikaz("chod temny_les");
        hra1.zpracujPrikaz("pouzi mec vyschnuty_konar");
        hra1.zpracujPrikaz("chod prekliata_pevnost");
        hra1.zpracujPrikaz("pouzi mec drakula");        
        assertTrue(hra1.konecHry());        
    }    
}
