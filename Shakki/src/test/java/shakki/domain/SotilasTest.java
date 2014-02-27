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
        sot1 = new Sotilas(l1, l1.getRuutu(2, 1), Vari.MUSTA);

        sot2 = new Sotilas(l1, l1.getRuutu(2, 4), Vari.VALKOINEN);

    }

    @After
    public void tearDown() {
    }



    @Test
    public void TestMahdollisetSiirrotLiiku1tai2() {
        r1 = l1.getRuutu(2, 3);
        assertEquals(true, sot1.mahdollisetSiirrot().contains(r1));
        assertEquals(true, sot2.mahdollisetSiirrot().contains(r1));

        sot1.KasvataSiirtoja();
        assertEquals(false, sot1.mahdollisetSiirrot().contains(r1));
        
    }
    
    @Test
    public void TestMahdollisetSiirrotSyo(){
        
        
        
        Sotilas Syotava1 = new Sotilas(l1, l1.getRuutu(3, 3), Vari.MUSTA);
        Sotilas Syotava2 = new Sotilas(l1, l1.getRuutu(1, 3), Vari.MUSTA);
        
        assertEquals(true, sot2.mahdollisetSiirrot().contains(l1.getRuutu(3, 3)));
        assertEquals(true, sot2.mahdollisetSiirrot().contains(l1.getRuutu(1, 3)));
        
       
    }
}
