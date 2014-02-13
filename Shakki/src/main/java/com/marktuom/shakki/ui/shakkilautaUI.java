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

    private JLayeredPane layeredPane;
    private JPanel shakkilauta;
    private JLabel shakkinappula;
    private Lauta lauta;
    private Ruutu lahto;
    

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

            int rivi = (i / 8) % 2;
            if (rivi == 0) {
                ruutu.setBackground(i % 2 == 0 ? Color.black : Color.white);
            } else {
                ruutu.setBackground(i % 2 == 0 ? Color.white : Color.black);
            }
            
            //Nappuloiden lisääminen laudalle JLabelien kuvina tulee tähän
       
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.pack();
            this.setResizable(false);
            this.setTitle("Shakki");
            this.setVisible(true);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
