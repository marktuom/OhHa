/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.marktuom.shakki.domain;

import com.marktuom.shakki.domain.Lauta;
import com.marktuom.shakki.domain.Ruutu;
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
public class LautaTest {
    
    Lauta l1;
    
    public LautaTest() {
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
    public void TestGenerointiJaGetterit(){
        Ruutu[][] r1 = l1.getRuudukko();
        assertEquals(true, r1!=null);
        assertEquals(true, r1[0][0]==l1.getRuutu(0, 0));
        
    } 
}
