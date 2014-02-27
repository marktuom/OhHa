/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shakki.domain;

import shakki.domain.Sotilas;
import shakki.domain.Nappula;
import shakki.domain.Torni;
import shakki.domain.Lauta;
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
public class TorniTest {
    Lauta l1;
    Torni t1;
     Ruutu r1;
    
    public TorniTest() {
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
        t1 = new Torni(l1, r1, Vari.MUSTA);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void TestVoiliikkua(){
        ArrayList<Ruutu> kohteet = new ArrayList<>();
        kohteet.add(l1.getRuutu(4, 0));
        kohteet.add(l1.getRuutu(0, 4));
        kohteet.add(l1.getRuutu(4, 7));
        kohteet.add(l1.getRuutu(7, 4));
       
       
        assertEquals(true, t1.mahdollisetSiirrot().containsAll(kohteet));
        
        r1 = l1.getRuutu(4, 2);
        Nappula n1 = new Sotilas(l1, r1, Vari.MUSTA);
        r1 = l1.getRuutu(6, 2);
        assertEquals(false, t1.mahdollisetSiirrot().contains(r1));
        r1 = l1.getRuutu(4, 2);
        assertEquals(false, t1.mahdollisetSiirrot().contains(r1));
        n1 = new Sotilas(l1, r1, Vari.VALKOINEN);
        assertEquals(true, t1.mahdollisetSiirrot().contains(r1));
        
        
    }
}
