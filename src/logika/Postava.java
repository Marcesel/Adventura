/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Postava popisuje jednotlivé postavy, ktoré sa nachádzajú v hre.
 * Trieda sa skladá len z jednoduchých setrov a getrov.
 * 
 *
 * @author    Marcel Češelka
 * @version   0.00.000
 */
public class Postava
{
    //== Datové atributy (statické i instancí)======================================
    private String meno;
    private String hlaska;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Postava(String meno, String hlaska)
    {
        this.meno = meno;
        this.hlaska = hlaska;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getMeno(){
        return meno;
    }

    public String getHlaska(){
        return hlaska;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
