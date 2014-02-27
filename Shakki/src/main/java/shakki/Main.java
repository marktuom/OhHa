package shakki;

import shakki.domain.Lauta;
import shakki.ui.shakkilautaUI;
import javax.swing.JFrame;

/**
 * Luo uuden shakkilautaUIn joka aloittaa ohjelman toiminnan.
 *
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new shakkilautaUI(new Lauta());      
    }
}
