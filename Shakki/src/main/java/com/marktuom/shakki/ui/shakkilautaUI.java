package com.marktuom.shakki.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.marktuom.shakki.domain.*;

/**
 * Luo shakkilaudan ruudukon ja nappulat. Toimii lisäksi mouselistenerinä ja
 * välittää siirrot logiikalle.
 *
 * @author Markus
 */
public class shakkilautaUI extends JFrame implements MouseListener, MouseMotionListener {

    JLayeredPane layeredPane;
    JPanel shakkilauta;
    JLabel shakkinappula;

    Lauta lauta;
    Ruutu lahto;

    int xKorjaus;
    int yKorjaus;

    JFrame voittoikkuna;

    public shakkilautaUI(Lauta lauta, int koko) {
        
        Dimension laudanKoko = new Dimension(koko, koko);
        
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(laudanKoko);
        
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);
        
        shakkilauta = new JPanel();
        layeredPane.add(shakkilauta, JLayeredPane.DEFAULT_LAYER);
        shakkilauta.setLayout(new GridLayout(8, 8));
        shakkilauta.setPreferredSize(laudanKoko);
        shakkilauta.setBounds(0, 0, laudanKoko.width, laudanKoko.height);

        for (int i = 0; i < 64; i++) {
            JPanel ruutu = new JPanel(new BorderLayout());
            ruutu.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            shakkilauta.add(ruutu);

        }

        uusiPeli();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setTitle("Shakki");
        int kehyksenkorkeus = getHeight() - getContentPane().getHeight() - 13;
        int kehyksenleveys = getWidth() - getContentPane().getWidth() - 13;
        this.setSize(koko + kehyksenleveys, koko + kehyksenkorkeus);
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(voittoikkuna != null){
            return;
        }
        
        shakkinappula = null;
        Component mahdollinenNappula = shakkilauta.findComponentAt(e.getX(), e.getY());
        if (mahdollinenNappula instanceof JPanel || mahdollinenNappula == null) {
            return;
        }

        lahto = lauta.getRuutu(e.getX() / 80, e.getY() / 80);
        if (lahto.getNappula().getVari() == lauta.getVuorossa()) {
            korostaRuutu(lahto.getNappula().laillisetSiirrot(), Color.green);
        }

        Point lahtoSijainti = mahdollinenNappula.getParent().getLocation();
        xKorjaus = lahtoSijainti.x - e.getX();
        yKorjaus = lahtoSijainti.y - e.getY();
        shakkinappula = (JLabel) mahdollinenNappula;
        shakkinappula.setLocation(e.getX() + xKorjaus, e.getY() + yKorjaus);
        shakkinappula.setSize(shakkinappula.getWidth(), shakkinappula.getHeight());
        layeredPane.add(shakkinappula, JLayeredPane.DRAG_LAYER);
    }

    public shakkilautaUI(Lauta lauta) {
        this(lauta, 640);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (shakkinappula == null || voittoikkuna != null) {
            return;
        }

        //Siirron tekeminen
        shakkinappula.setVisible(false);
        Component siirronKohde;
        Ruutu maali = lauta.getRuutu(e.getX() / 80, e.getY() / 80);
        boolean voiliikkua = lauta.siirra(lahto, maali);
        if (voiliikkua) {
            piirraNappulat();
        } else {
            siirronKohde = shakkilauta.getComponent(lahto.getSijainti().getY() * 8 + lahto.getSijainti().getX());
            Container parent = (Container) siirronKohde;
            parent.add(shakkinappula);
        }
        lahto = null;
        varitaRuudukko();
        shakkinappula.setVisible(true);

        if (lauta.shakissa(lauta.getVuorossa())) {
            korostaRuutu(lauta.kuninkaanRuutu(lauta.getVuorossa()), Color.red);
        }
        if (lauta.matissa(lauta.getVuorossa())) {
            if (lauta.getVuorossa() == Vari.VALKOINEN) {
                voittoikkuna = new PeliOhiIkkunaUI("musta voitti!", this);
            } else {
                voittoikkuna = new PeliOhiIkkunaUI("valkoinen voitti!", this);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (shakkinappula == null) {
            return;
        }
        shakkinappula.setLocation(e.getX() + xKorjaus, e.getY() + yKorjaus);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Värittää ruudukon shakille tyypillisellä ruutukuviolla. Vasen yläkulma on
     * "valkoinen" ruutu.
     */
    public void varitaRuudukko() {
        for (int i = 0; i < 64; i++) {
            JPanel ruutu = (JPanel) shakkilauta.getComponent(i);

            int rivi = (i / 8) % 2;
            if (rivi == 1) {
                ruutu.setBackground(i % 2 == 0 ? Color.GRAY : Color.white);
            } else {
                ruutu.setBackground(i % 2 == 0 ? Color.white : Color.GRAY);
            }
        }
    }

    /**
     * Piirtää laudan nappulasijoittelua vastaavat nappulat laudalle. Mikäli
     * laudalla on vanha asetelma, se tuhotaan.
     */
    public void piirraNappulat() {

        Ruutu[][] ruudukko = lauta.getRuudukko();
        Nappula piirrettavaNappula;
        JPanel panel;
        for (int i = 0; i < 64; i++) {
            panel = (JPanel) shakkilauta.getComponent(i);
            if (panel.getComponentCount() != 0) {
                panel.remove(0);
            }
            piirrettavaNappula = ruudukko[i / 8][i % 8].getNappula();
            if (piirrettavaNappula != null) {
                luoNappula(piirrettavaNappula);
                panel.add(shakkinappula);
            }
        }
    }

    /**
     * Asettaa "shakkinappula" muuttujan arvoksi parametrin ruudussa olevaa
     * nappulaa vastaavan JLabelin
     *
     * @param nappula nappula joka halutaan piirtää.
     */
    public void luoNappula(Nappula nappula) {

        char merkki;
        if (nappula.getVari() == Vari.VALKOINEN) {
            merkki = '\u2654';
        } else {
            merkki = '\u265A';
        }
        if (nappula instanceof Kuningas) {
        } else if (nappula instanceof Kuningatar) {
            merkki += 1;
        } else if (nappula instanceof Torni) {
            merkki += 2;
        } else if (nappula instanceof Lahetti) {
            merkki += 3;
        } else if (nappula instanceof Ratsu) {
            merkki += 4;
        } else {
            merkki += 5;
        }
        shakkinappula = new JLabel("" + merkki, SwingConstants.CENTER);
        shakkinappula.setFont(new Font(null, Font.PLAIN, 62));
    }

    /**
     * Muuttaa parametrina saadun Collectionin ruutujen tastavärit
     *
     * @param ruudut Collection ruutja joiden taustaväri halutaan muuttaa
     * @param vari Haluttu taustaväri
     */
    public void korostaRuutu(Collection<Ruutu> ruudut, Color vari) {
        for (Ruutu ruutu : ruudut) {
            korostaRuutu(ruutu, vari);
        }
    }

    /**
     * Muuttaa ruudun taustavärin
     *
     * @param ruutu Ruutu jonka taustaväri halutaan muuttaa
     * @param vari Haluttu taustaväri
     */
    public void korostaRuutu(Ruutu ruutu, Color vari) {
        JPanel PiirrettavaRuutu = (JPanel) shakkilauta.getComponent(ruutu.getSijainti().getY() * 8 + ruutu.getSijainti().getX());
        PiirrettavaRuutu.setBackground(vari);
    }

    public void uusiPeli() {
        Lauta uusiLauta = new Lauta();
        uusiLauta.generoiNappulat();
        lauta = uusiLauta;
        varitaRuudukko();
        piirraNappulat();
        getContentPane().validate();
        getContentPane().repaint();
        voittoikkuna = null;
    }

}
