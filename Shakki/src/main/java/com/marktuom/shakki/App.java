package com.marktuom.shakki;

import com.marktuom.shakki.domain.*;
import com.marktuom.shakki.ui.shakkilautaUI;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        JFrame frame = new shakkilautaUI(new Lauta());
       
    }
}
