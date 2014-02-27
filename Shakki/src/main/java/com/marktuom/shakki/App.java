package com.marktuom.shakki;

import com.marktuom.shakki.domain.*;
import com.marktuom.shakki.ui.shakkilautaUI;
import javax.swing.JFrame;

/**
 * Luo uuden shakkilautaUIn joka aloittaa ohjelman toiminnan.
 *
 */
public class App {

    public static void main(String[] args) {
        JFrame frame = new shakkilautaUI(new Lauta());      
    }
}
