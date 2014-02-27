/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marktuom.shakki.ui;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Luo ikkunan, joka ilmoittaa pelin loppuneen, kertoo voittajan ja tarjoaa jatkotoimenpiteitä
 * @author Markus
 */
public class PeliOhiIkkunaUI extends JFrame implements ActionListener {
    /**
     * Poistu Painike joka sulkee ohjelman
     */
    private final JButton poistu;
    
    /**
     * Uusi peli painike joka aloittaa uuden pelin
     */
    private final JButton uusi;
    
    /**
     * PelililautaUI jota suoritettavat toiminnot koskevat
     */
    private final JFrame peliIkkuna;

    
    /**
     * 
     * @param teksti Teksti joka näkyy voittoikkunan tekstikentässä
     * @param peliIkkuna ShakkilautaUI jota voittoikkuna koskee
     */
    public PeliOhiIkkunaUI(String teksti, JFrame peliIkkuna) {
        this.peliIkkuna = peliIkkuna;
        getContentPane().setLayout(new GridLayout(2, 1));

        JTextField voittoilmoitus = new JTextField("Peli loppui, " + teksti);
        voittoilmoitus.setHorizontalAlignment(JTextField.CENTER);
        voittoilmoitus.setEditable(false);

        JPanel nappulakentta = new JPanel(new GridLayout());

        uusi = new JButton("Uusi peli");
        uusi.addActionListener(this);
        nappulakentta.add(uusi);

        poistu = new JButton("Poistu");
        poistu.addActionListener(this);
        nappulakentta.add(poistu);

        getContentPane().add(voittoilmoitus);
        getContentPane().add(nappulakentta);

        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(peliIkkuna);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    
    /**
     * Suorittaa asianmukaisen toiminnon sen mukaan kumpaa nappia painetaan. Poistu -nappi sulkee pelin, uusi -nappi aloittaa uuden pelin.
     * @param e Ikkunan jomman kumman napin painallus
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == poistu) {
            peliIkkuna.dispose();
            this.dispose();
        }

        if (e.getSource() == uusi) {
            ((shakkilautaUI) peliIkkuna).uusiPeli();
            this.dispose();
        }
    }

}
