/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marktuom.shakki.domain;

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
public class LahettiTest {
    Lauta l1;
    Lahetti lah1;
    Ruutu r1;

    public LahettiTest() {
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
        lah1 = new Lahetti(l1,r1, Vari.MUSTA);
        
    }

    @After
    public void tearDown() {
    }

  
    @Test
    public void TestMahdollisetSiirrot() {
        ArrayList<Ruutu> kohteet = new ArrayList<>();
        kohteet.add( l1.getRuutu(7, 7));
        kohteet.add(l1.getRuutu(0, 0));
        kohteet.add( l1.getRuutu(1, 7));
        kohteet.add( l1.getRuutu(7, 1));
       
        assertEquals(true, lah1.mahdollisetSiirrot().containsAll(kohteet));
        
        r1 = l1.getRuutu(5, 5);
        Nappula n1 = new Sotilas(l1, r1, Vari.MUSTA);
        r1 = l1.getRuutu(6, 6);
        assertEquals(false, lah1.mahdollisetSiirrot().contains(r1));
        r1 = l1.getRuutu(5, 5);
        assertEquals(false, lah1.mahdollisetSiirrot().contains(r1));
        n1 = new Sotilas(l1, r1, Vari.VALKOINEN);
        assertEquals(true, lah1.mahdollisetSiirrot().contains(r1));
        
    }
}
