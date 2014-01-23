
package shakki.domain;

/**
 *
 * @author Markus
 */
public class Lauta {
    private Ruutu[][] ruudukko;

    public Lauta() {
        ruudukko = new Ruutu[8][8];
    }
    
    public void generoiNappulat(){
        
    }

    public Ruutu[][] getRuudukko() {
        return ruudukko;
    }
    
    public Ruutu getRuutu(int x, int y){
        try{
            return ruudukko[y][x];
        }catch(Exception e){
            return null;
        }
        
    }
    
    public boolean siirra(Ruutu lahto, Ruutu kohde){
        return false;
    }
    
    public boolean nappuloitaTiella(Ruutu lahto, Ruutu kohde){
        return false;
    }
    
}
