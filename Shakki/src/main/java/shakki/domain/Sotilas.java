
package shakki.domain;

/**
 *
 * @author Markus
 */
public class Sotilas extends Nappula{
    
    private int siirtoja;

    public Sotilas(Vari vari) {
        super(vari);
        this.siirtoja = 0;
    }
    
    public void liiku(){
        siirtoja++;
    }
    
    
    @Override
    public boolean voiLiikkua(Sijainti lahto, Sijainti kohde) {
        int yMuutos = lahto.yErotus(kohde);
        int xMuutos = lahto.xErotus(kohde);
        if (xMuutos == 0){
            if(yMuutos == this.getVari().getArvo()){
                return true;
            }
            if(yMuutos == 2*this.getVari().getArvo() && siirtoja == 0){
                return true;
            }
        }
        return lahto.xMuutos(kohde) == 1 && yMuutos == this.getVari().getArvo();
    }
    
}
