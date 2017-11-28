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
    private String obrazok;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     * @param nazev Nazev veci
     * @param popis Strucny popis veci
     * @param prenositelna Vlastnost jestli je vec prenositelna
     * @param obrazok Nazov suboru v ktorom je obrazok pre danu vec
     */
    public Vec(String nazev, String popis, boolean prenositelna, String obrazok)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
        this.obrazok = obrazok;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     *
     * @return Vrati nazev veci
     */
    public String getNazev() {
        return nazev;
    }
    
    /**
     *
     * @return Vrati popis veci
     */
    public String getPopis() {
        return popis;
    }
    
    /**
     *
     * @return Vrati vlastnost veci ci je vec prenositelna
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }


    //== Soukromé metody (instancí i třídy) ========================================

    /**
     *
     * @return vrati nazov suboru v ktorom je obrazok pre danu vec
     */

    public String getObrazok() {
        return obrazok;
    }

}
