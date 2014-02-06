
package com.marktuom.shakki.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Nappulan aliluokka joka kuvaa shakin sotilasta
 * @author Markus
 */
public class Sotilas extends Nappula{
    
    private int siirtoja;

    public Sotilas(Lauta lauta, Ruutu ruutu, Vari vari) {
        super(lauta, ruutu, vari);
        this.siirtoja = 0;
    }
    
    public void kasvataSiirtoja(){
        siirtoja++;
    }

    public int getSiirtoja() {
        return siirtoja;
    }
    
    
    @Override
    public boolean liiku(Ruutu kohde) {
        boolean liikkui = super.liiku(kohde);
        if(liikkui){
            siirtoja++;
        }
        return liikkui;
    }

    @Override
    public Collection<Ruutu> mahdollisetSiirrot() {
        Sijainti omaSijainti = this.ruutu.getSijainti();
        ArrayList<Ruutu> mahdollisetKohteet = new ArrayList<>();
        
        Ruutu mahdollinenKohde = lauta.getRuutu(omaSijainti.getX(), omaSijainti.getY() + this.getVari().getArvo());
        if(mahdollinenKohde != null && mahdollinenKohde.getNappula() == null){
            mahdollisetKohteet.add(mahdollinenKohde);
        }
        
        mahdollinenKohde = lauta.getRuutu(omaSijainti.getX(), omaSijainti.getY() + (this.getVari().getArvo()*2));
        if(this.siirtoja == 0 && mahdollinenKohde.getNappula() == null && !lauta.nappuloitaTiella(this.ruutu, mahdollinenKohde)){
            mahdollisetKohteet.add(mahdollinenKohde);
        }
        
         mahdollinenKohde = lauta.getRuutu(omaSijainti.getX() + 1, omaSijainti.getY() + this.getVari().getArvo());
        if(mahdollinenKohde != null && (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() != this.getVari())){
            mahdollisetKohteet.add(mahdollinenKohde);
        }
          mahdollinenKohde = lauta.getRuutu(omaSijainti.getX() - 1, omaSijainti.getY() + this.getVari().getArvo());
        if(mahdollinenKohde != null && (mahdollinenKohde.getNappula() != null && mahdollinenKohde.getNappula().getVari() != this.getVari())){
            mahdollisetKohteet.add(mahdollinenKohde);
        }
        return mahdollisetKohteet;
    }
    
}
