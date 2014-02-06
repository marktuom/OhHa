package com.marktuom.shakki.domain;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.marktuom.shakki.domain.Vari;
import com.marktuom.shakki.domain.Ratsu;
import com.marktuom.shakki.domain.Sijainti;
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
public class RatsuTest {
    Lauta l1;
    Ratsu rat1;
    Ruutu r1;
    
    public RatsuTest() {
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
        r1 = l1.getRuutu(2, 2);
        rat1 = new Ratsu(l1, r1, Vari.MUSTA);
     
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
    public void TestVoiLiikkua(){
        r1 = l1.getRuutu(4, 3);
        Nappula n1 = new Sotilas(l1, r1, Vari.VALKOINEN);
        
        ArrayList<Ruutu> kohteet = new ArrayList<>();
        kohteet.add(l1.getRuutu(0, 1));
        kohteet.add(l1.getRuutu(0, 3));
        kohteet.add(l1.getRuutu(4, 1));
        kohteet.add(l1.getRuutu(4, 3));
        kohteet.add(l1.getRuutu(1, 0));
        kohteet.add(l1.getRuutu(1, 4));
        kohteet.add(l1.getRuutu(3, 0));
        kohteet.add(l1.getRuutu(3, 4));
        
        assertEquals(true, rat1.mahdollisetSiirrot().containsAll(kohteet));
       
        n1 = new Sotilas(l1, r1, Vari.MUSTA);
        assertEquals(false, rat1.mahdollisetSiirrot().contains(r1));
    }
}
