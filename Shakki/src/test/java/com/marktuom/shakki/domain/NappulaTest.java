/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.marktuom.shakki.domain;

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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void TestLiiku(){
        
        r1 = l1.getRuutu(6, 6);
        assertEquals(true, n1.liiku(r1));
        assertEquals(null, l1.getRuutu(4, 4).getNappula());
        
        r1 = l1.getRuutu(6, 5);
        assertEquals(true, n1.liiku(r1));
        assertEquals(n1, r1.getNappula());
    }
}
