/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shakki.domain;

import shakki.domain.Nappula;
import shakki.domain.Torni;
import shakki.domain.Lauta;
import shakki.domain.Kuningatar;
import shakki.domain.Vari;
import shakki.domain.Ruutu;
import shakki.domain.Kuningas;
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
public class NappulaTest {
    
    Lauta l1;
    Ruutu r1;
    Nappula n1;
    
    public NappulaTest() {
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
        r1 = l1.getRuutu(4, 4);
        n1 = new Kuningatar(l1, r1, Vari.MUSTA);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void TestLiiku(){
        
        r1 = l1.getRuutu(6, 6);
        assertEquals(true, n1.liiku(r1));
        assertEquals(null, l1.getRuutu(4, 4).getNappula());
        
        r1 = l1.getRuutu(6, 5);
        assertEquals(true, n1.liiku(r1));
        assertEquals(n1, r1.getNappula());
    }
    
    @Test
    public void TestLaillisetSiirrot(){
        Nappula n2 = new Kuningas(l1, l1.getRuutu(0, 3), Vari.VALKOINEN);
        assertEquals(true, n2.mahdollisetSiirrot().contains(l1.getRuutu(0, 4)));
        assertEquals(false, n2.laillisetSiirrot().contains(l1.getRuutu(0, 4)));
        
        Nappula n3 = new Kuningas(l1, l1.getRuutu(4, 7), Vari.MUSTA);
        Nappula n4 = new Torni(l1, l1.getRuutu(4, 0), Vari.VALKOINEN);
        assertEquals(true, n1.mahdollisetSiirrot().contains(l1.getRuutu(5, 4)));
        assertEquals(false, n1.laillisetSiirrot().contains(l1.getRuutu(5, 4)));
    }
}
