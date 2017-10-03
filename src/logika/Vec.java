/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Vec popisujúca objekty/veci, ktoré sa nachádzajú v priestoroch hry.
 * Trieda obsahuje len jednoduché getry a setry.
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    private boolean prenositelna;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, String popis, boolean prenositelna)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getNazev() {
        return nazev;
    }
    
    public String getPopis() {
        return popis;
    }
    
    public boolean isPrenositelna() {
        return prenositelna;
    }


    //== Soukromé metody (instancí i třídy) ========================================

}
