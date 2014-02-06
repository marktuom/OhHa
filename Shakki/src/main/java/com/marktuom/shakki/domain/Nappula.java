
package com.marktuom.shakki.domain;

import java.util.Collection;

/**
 *  Shakkinappuloiden yliluokka.
 *  Määrittää kaikille nappuloille yhteiset liikkumiseen liittyvät toiminnot sekä nappuloille yhteiset muuttujat.
 * @author Markus
 * 
 */
public abstract class Nappula {
    private final Vari vari;
    protected final Lauta lauta;
    protected Ruutu ruutu;

    public void setRuutu(Ruutu ruutu) {
        this.ruutu = ruutu;
    }
    
    public Nappula(Lauta lauta, Ruutu ruutu, Vari vari){
        this.vari = vari;
        this.lauta = lauta;
        this.ruutu = ruutu;
        if(this.ruutu != null){
            this.ruutu.setNappula(this);
        }
    }
    
    /**
 * Siirtää nappulan parametrin määrittämään ruutuun
 * @param kohde Ruutu johon nappula halutaan siirtää
 * @return Palauttaa true mikäli siirto onnistuu, muulloin false
 * 
 */
    public boolean liiku(Ruutu kohde){
        if(!this.mahdollisetSiirrot().contains(kohde)){
            return false;
        }
        
        this.ruutu.poistaNappula();
        this.ruutu = kohde;
        this.ruutu.setNappula(this);
        return true;
    }
    
     /**
 * Tutkii mihin ruutuihin nappula voi liikkua
 * 
 * @return Palauttaa Collectionin ruuduista joihin nappulan on mahdollista siirtyä
 * 
 */
    public abstract Collection<Ruutu> mahdollisetSiirrot();

    public Vari getVari() {
        return vari;
    }
        
    
}
