/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.marktuom.shakki.domain;

import com.marktuom.shakki.domain.Torni;
import com.marktuom.shakki.domain.Vari;
import com.marktuom.shakki.domain.Sijainti;
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
    Torni t1;
    Sijainti s1;
    Sijainti s2;
    
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
        s1 = new Sijainti(2, 2);
        t1 = new Torni(Vari.MUSTA);
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
        s2 = new Sijainti(4, 2);
        assertEquals(true, t1.voiLiikkua(s1, s2));
        
        s2 = new Sijainti(2, 4);
        assertEquals(true, t1.voiLiikkua(s1, s2));
        
         
        s2 = new Sijainti(3, 3);
        assertEquals(false, t1.voiLiikkua(s1, s2));
        
        
    }
}
