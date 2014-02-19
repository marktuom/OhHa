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

    public shakkilautaUI(Lauta lauta) {
        this.lauta = lauta;
        lauta.generoiNappulat();

        Dimension laudanKoko = new Dimension(640, 640);

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
            shakkilauta.add(ruutu);

        }
        varitaRuudukko();

        piirraNappulat();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setResizable(true);
        this.setTitle("Shakki");
        this.setVisible(true);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        shakkinappula = null;
        Component siirronLahto = shakkilauta.findComponentAt(e.getX(), e.getY());
        if (siirronLahto instanceof JPanel) {
            return;
        }
        
        lahto = lauta.getRuutu(e.getX() / 80, e.getY() / 80);       
        if (lahto.getNappula().getVari() == lauta.getVuorossa()) {
            korostaRuudut(lahto.getNappula().laillisetSiirrot(), Color.green);
        }
        
        Point lahtoSijainti = siirronLahto.getParent().getLocation();
        xKorjaus = lahtoSijainti.x - e.getX();
        yKorjaus = lahtoSijainti.y - e.getY();
        shakkinappula = (JLabel) siirronLahto;
        shakkinappula.setLocation(e.getX() + xKorjaus, e.getY() + yKorjaus);
        shakkinappula.setSize(shakkinappula.getWidth(), shakkinappula.getHeight());
        layeredPane.add(shakkinappula, JLayeredPane.DRAG_LAYER);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (shakkinappula == null) {
            return;
        }

        shakkinappula.setVisible(false);
        Component siirronKohde;
        Ruutu maali = lauta.getRuutu(e.getX() / 80, e.getY() / 80);
        boolean voiliikkua = lauta.siirra(lahto, maali);
        if (voiliikkua) {
            siirronKohde = shakkilauta.findComponentAt(e.getX(), e.getY());
            if (siirronKohde instanceof JLabel) {
                Container parent = siirronKohde.getParent();
                parent.remove(0);
                parent.add(shakkinappula);
            } else {
                Container parent = (Container) siirronKohde;
                parent.add(shakkinappula);
            }
        } else {
            siirronKohde = shakkilauta.getComponent(lahto.getSijainti().getY() * 8 + lahto.getSijainti().getX());
            Container parent = (Container) siirronKohde;
            parent.add(shakkinappula);
        }
        lahto = null;
        varitaRuudukko();
        shakkinappula.setVisible(true);
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
     * Värittää ruudukon shakille tyypillisellä ruutukuviolla. Vasen yläkulma on "valkoinen" ruutu.
     */
    public void varitaRuudukko() {
        for (int i = 0; i < 64; i++) {
            JPanel ruutu = (JPanel) shakkilauta.getComponent(i);

            int rivi = (i / 8) % 2;
            if (rivi == 1) {
                ruutu.setBackground(i % 2 == 0 ? Color.black : Color.white);
            } else {
                ruutu.setBackground(i % 2 == 0 ? Color.white : Color.black);
            }
        }
    }

    /**
     * Piirtää laudan nappulasijoittelua vastaavat nappulat laudalle
     */
    public void piirraNappulat() {

        JPanel panel;
        Ruutu[][] ruudukko = lauta.getRuudukko();
        Nappula piirrettavaNappula;

        for (int i = 0; i < 64; i++) {
            piirrettavaNappula = ruudukko[i / 8][i % 8].getNappula();
            if (piirrettavaNappula != null) {
                panel = (JPanel) shakkilauta.getComponent(i);
                luoNappula(piirrettavaNappula);
                panel.add(shakkinappula);
            }
        }
    }

    /**
     * Asettaa shakkinappula muuttujan arvoksi parametrin ruudussa olevaa
     * nappulaa vastaavaksi JLabeliksi
     *
     * @param nappula nappula joka halutaan piirtää.
     */
    public void luoNappula(Nappula nappula) {
                //Nappuloiden lisääminen laudalle JLabelien otsikoina
//                        "\u2654"    white king
//                        "\u2655"    white queen
//                        "\u2656"    white rook
//                        "\u2657"    white bishop
//                        "\u2658"    white knight
//                        "\u2659"    white pawn
//        
//                        "\u265A"    black king
//                        "\u265B"    black queen
//                        "\u265C"    black rook
//                        "\u265D"    black bishop
//                        "\u265E"    black knight
//                        "\u265F"    black pawn
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
    
    public void korostaRuudut(Collection<Ruutu> ruudut, Color vari){
        for (Ruutu ruutu : ruudut) {
            korostaRuudut(ruutu, vari);
        }
    }

    public void korostaRuudut(Ruutu ruutu, Color vari){
        JPanel PiirrettavaRuutu = (JPanel) shakkilauta.getComponent(ruutu.getSijainti().getY() * 8 + ruutu.getSijainti().getX());
        PiirrettavaRuutu.setBackground(vari);
    }
}
