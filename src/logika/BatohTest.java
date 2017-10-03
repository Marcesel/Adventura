/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování třídy . 
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */
public class BatohTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------
    private Vec vec1;
    private Vec vec2;
    private Vec vec4;
    private Batoh batoh;
    final static String NAZOV1 = "vec1";
    final static String NAZOV2 = "vec2";
    
    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        vec1 = new Vec(NAZOV1, "Cesnakové strúčiky aj s vňaťkou", true);
        vec2 = new Vec(NAZOV2, "Vidím dve rany na krku po kusnutí", true);
        vec4 = new Vec("vec4", "Mesto - západ 10 km, Farma - východ 5 km", true);
        batoh = new Batoh();
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
     * Test pridania veci do batohu.
     */
    @Test
    public void testPridaj()
    {     
      assertTrue(batoh.pridaj(vec1));
      assertTrue(batoh.nazvyVeciVBatohu().contains(NAZOV1));
      assertFalse(batoh.nazvyVeciVBatohu().contains(NAZOV2));
      assertTrue(batoh.pridaj(vec4));    
      assertTrue(batoh.nazvyVeciVBatohu().contains("vec4"));

    }
    
    /***************************************************************************
     * Test odobrania veci z batohu.
     */    
    @Test
    public void testVyber()
    {   
        assertNull(batoh.vyber(vec1.getNazev()));  
        batoh.pridaj(vec1);
        assertEquals(vec1,batoh.vyber(vec1.getNazev()));
        assertFalse(batoh.nazvyVeciVBatohu().contains(NAZOV1));        
        batoh.pridaj(vec2);
        assertTrue(batoh.nazvyVeciVBatohu().contains(NAZOV2));        
        assertEquals(null,batoh.vyber(vec1.getNazev()));        
        assertEquals(vec2,batoh.vyber(vec2.getNazev()));  
    }
   
    /***************************************************************************
     * Test výpisu vecí v batohu.
     */    
    @Test
    public void testNazvyVeciVBatohu()
    {
        batoh.pridaj(vec1);
        batoh.pridaj(vec2);        
        assertTrue(batoh.nazvyVeciVBatohu().contains(NAZOV1));
        assertFalse(batoh.nazvyVeciVBatohu().contains("vec3"));
        batoh.vyber(vec2.getNazev());     
        assertFalse(batoh.nazvyVeciVBatohu().contains(NAZOV2));        
    }
}
