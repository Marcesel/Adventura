/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování třídy.
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */
public class ProstorTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------
    Vec vec1;
    Vec vec2;
    Postava postava1;
    Postava postava2;
    Prostor priestor1;
    Prostor priestor2;
    final static String NAZOV1 = "vec1";
    final static String NAZOV2 = "zena";
    
    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        vec1 = new Vec(NAZOV1,"popis1",true);
        vec2 = new Vec("vec2","popis2",true);
        postava1 = new Postava("muz","Ahoj");
        postava2 = new Postava(NAZOV2,"Ahoooj");
        priestor1 = new Prostor("farma","krasny domov");
        priestor2 = new Prostor("mesto","druhy domov");        
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
     * Ten vloženia veci do priestoru.
     */
    @Test
    public void testVlozVec()
    {
        assertNull(priestor1.odeberVec(NAZOV1));        
        priestor1.vlozVec(vec1);
        assertSame(vec1,priestor1.odeberVec(NAZOV1));
    }
    
    /***************************************************************************
     * Test odobratia veci z priestoru.
     */
    @Test
    public void testOdeberVec()
    {
        assertNull(priestor1.odeberVec(NAZOV1));        
        priestor1.vlozVec(vec1);
        assertSame(vec1,priestor1.odeberVec(NAZOV1));
        assertNull(priestor1.odeberVec(NAZOV1));         
    }
    
    /***************************************************************************
     * Ten vloženia postavy do priestoru.
     */
    @Test
    public void testVlozPostavu()
    {
        assertNull(priestor1.odoberPostavu("muz"));        
        priestor1.vlozPostavu(postava1);
        assertEquals(postava1,priestor1.odoberPostavu("muz"));
    }
    
    /***************************************************************************
     * Test odobratia postavy z priestoru.
     */    
    @Test
    public void testOdoberPostavu()
    {
        assertNull(priestor1.odoberPostavu(NAZOV2));        
        priestor1.vlozPostavu(postava2);
        assertEquals(postava2,priestor1.odoberPostavu(NAZOV2));
        assertNull(priestor1.odoberPostavu(NAZOV2));         
    }    
    
    /***************************************************************************
     * Test nastavenia východov z priestoru.
     */
    @Test
    public void testSetVychody()
    {
        priestor1.setVychod(priestor2);
        assertEquals(priestor2,priestor1.vratSousedniProstor("mesto"));
        assertNull(priestor1.vratSousedniProstor("krajinka"));
    }
}
