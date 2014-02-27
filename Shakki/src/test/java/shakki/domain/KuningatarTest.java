/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shakki.domain;

import shakki.domain.Sotilas;
import shakki.domain.Lauta;
import shakki.domain.Kuningatar;
import shakki.domain.Vari;
import shakki.domain.Ruutu;
import java.util.ArrayList;
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
public class KuningatarTest {
    public Lauta l1;
    public Kuningatar k1;
    public Ruutu r1;
    
    public KuningatarTest() {
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
         k1 = new Kuningatar(l1,l1.getRuutu(4, 4), Vari.MUSTA);
         l1.getRuutu(4, 4).setNappula(k1);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void TestMahdollisetSiirrot(){
        ArrayList<Ruutu> kohteet = new ArrayList<>();
        kohteet.add(l1.getRuutu(4, 0));
        kohteet.add(l1.getRuutu(0, 4));
        kohteet.add(l1.getRuutu(4, 7));
        kohteet.add(l1.getRuutu(7, 4));
        kohteet.add( l1.getRuutu(7, 7));
        kohteet.add(l1.getRuutu(0, 0));
        kohteet.add( l1.getRuutu(1, 7));
        kohteet.add( l1.getRuutu(7, 1));
       
        assertEquals(true, k1.mahdollisetSiirrot().containsAll(kohteet));
        
        for (Ruutu ruutu : kohteet) {
            ruutu.setNappula(new Sotilas(l1, ruutu, Vari.MUSTA));
            assertEquals(false, k1.mahdollisetSiirrot().contains(ruutu));
        }
        
        for (Ruutu ruutu : kohteet) {
            ruutu.setNappula(new Sotilas(l1, ruutu, Vari.VALKOINEN));
            assertEquals(true, k1.mahdollisetSiirrot().contains(ruutu));
        }
        
    }
}
