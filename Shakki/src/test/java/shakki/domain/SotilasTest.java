/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shakki.domain;

import shakki.domain.Sotilas;
import shakki.domain.Lauta;
import shakki.domain.Vari;
import shakki.domain.Ruutu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sun.management.counter.Variability;

/**
 *
 * @author Markus
 */
public class SotilasTest {

    Sotilas sot1;
    Sotilas sot2;
    Ruutu r1;
    Lauta l1;

    public SotilasTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        l1 = new Lauta();
        r1 = l1.getRuutu(2, 1);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void TestMahdollisetSiirrotLiiku1tai2() {
        sot1 = new Sotilas(l1, l1.getRuutu(2, 1), Vari.MUSTA);
        sot2 = new Sotilas(l1, l1.getRuutu(2, 4), Vari.VALKOINEN);

        r1 = l1.getRuutu(2, 3);
        assertEquals(true, sot1.mahdollisetSiirrot().contains(r1));
        assertEquals(true, sot2.mahdollisetSiirrot().contains(r1));

        sot1.KasvataSiirtoja();
        assertEquals(false, sot1.mahdollisetSiirrot().contains(r1));

        sot2 = new Sotilas(l1, l1.getRuutu(2, 2), Vari.VALKOINEN);
        assertEquals(false, sot2.mahdollisetSiirrot().contains(l1.getRuutu(2, 1)));
        assertEquals(false, sot2.mahdollisetSiirrot().contains(l1.getRuutu(2, 0)));

        sot2 = new Sotilas(l1, l1.getRuutu(3, 0), Vari.VALKOINEN);
        assertEquals(false, sot2.mahdollisetSiirrot().contains(l1.getRuutu(3, -1)));
        assertEquals(false, sot2.mahdollisetSiirrot().contains(l1.getRuutu(2, -2)));
    }

    @Test
    public void TestMahdollisetSiirrotSyo() {

        sot2 = new Sotilas(l1, l1.getRuutu(2, 4), Vari.VALKOINEN);

        assertEquals(false, sot2.mahdollisetSiirrot().contains(l1.getRuutu(3, 3)));
        assertEquals(false, sot2.mahdollisetSiirrot().contains(l1.getRuutu(1, 3)));

        Sotilas Syotava1 = new Sotilas(l1, l1.getRuutu(3, 3), Vari.MUSTA);
        Sotilas Syotava2 = new Sotilas(l1, l1.getRuutu(1, 3), Vari.MUSTA);

        assertEquals(true, sot2.mahdollisetSiirrot().contains(l1.getRuutu(3, 3)));
        assertEquals(true, sot2.mahdollisetSiirrot().contains(l1.getRuutu(1, 3)));

        Syotava1 = new Sotilas(l1, l1.getRuutu(3, 3), Vari.VALKOINEN);
        Syotava2 = new Sotilas(l1, l1.getRuutu(1, 3), Vari.VALKOINEN);

        assertEquals(false, sot2.mahdollisetSiirrot().contains(l1.getRuutu(3, 3)));
        assertEquals(false, sot2.mahdollisetSiirrot().contains(l1.getRuutu(1, 3)));

    }

    @Test
    public void TestMahdollisetSiirrotOhestalyönti() {
        l1.generoiNappulat();
        l1.siirra(l1.getRuutu(4, 6), l1.getRuutu(4, 4));
        l1.siirra(l1.getRuutu(3, 1), l1.getRuutu(3, 3));
        l1.siirra(l1.getRuutu(4, 4), l1.getRuutu(4, 3));
        l1.siirra(l1.getRuutu(5, 1), l1.getRuutu(5, 3));
        sot1 = (Sotilas) l1.getRuutu(4, 3).getNappula();
        assertEquals(5, ((Sotilas) l1.getRuutu(5, 3).getNappula()).getOhestalyotavaVuorolla());
        assertEquals(false, sot1.mahdollisetSiirrot().contains(l1.getRuutu(3, 2)));
        assertEquals(true, sot1.mahdollisetSiirrot().contains(l1.getRuutu(5, 2)));
        
        assertEquals(false, sot1.liiku(l1.getRuutu(3, 2)));
        assertEquals(true, sot1.liiku(l1.getRuutu(5, 2)));
        assertEquals(null, l1.getRuutu(5, 3).getNappula());
        
        l1 = new Lauta();
        l1.generoiNappulat();
        l1.siirra(l1.getRuutu(4, 6), l1.getRuutu(4, 4));
        l1.siirra(l1.getRuutu(5, 1), l1.getRuutu(5, 3));
        l1.siirra(l1.getRuutu(4, 4), l1.getRuutu(4, 3));
        l1.siirra(l1.getRuutu(3, 1), l1.getRuutu(3, 3));
        sot1 = (Sotilas) l1.getRuutu(4, 3).getNappula();
        
        assertEquals(true, sot1.mahdollisetSiirrot().contains(l1.getRuutu(3, 2)));
        assertEquals(false, sot1.mahdollisetSiirrot().contains(l1.getRuutu(5, 2)));
    }
    
    @Test
    public void TestYleneminen(){
        sot1 = new Sotilas(l1, l1.getRuutu(0, 1), Vari.VALKOINEN);
        sot1.liiku(l1.getRuutu(0, 0));
        assertEquals(true, l1.getRuutu(0, 0).getNappula() instanceof Kuningatar);
        
        sot2 = new Sotilas(l1, l1.getRuutu(0, 6), Vari.MUSTA);
        sot2.liiku(l1.getRuutu(0, 7));
        assertEquals(true, l1.getRuutu(0, 7).getNappula() instanceof Kuningatar);
    }
}
