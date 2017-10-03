/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída VecTest slouží ke komplexnímu otestování třídy.
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */
public class VecTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    { 
        // neinicializujeme
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
     * Test metody prenositelnosti
     */
    @Test
    public void testPrenositelnost()
    {
        Vec vec1 = new Vec("vec1","", true);
        Vec vec2 = new Vec("vec2","", false);
        assertEquals(true, vec1.isPrenositelna());
        assertEquals(false, vec2.isPrenositelna());
    }
}
