package com.marktuom.shakki.domain;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.marktuom.shakki.domain.Vari;
import com.marktuom.shakki.domain.Ratsu;
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
public class RatsuTest {
    Ratsu r1;
    Sijainti s1;
    Sijainti s2;
    
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
        r1 = new Ratsu(Vari.MUSTA);
        s1= new Sijainti(3, 3);
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
        s2 = new Sijainti(5, 4);
        assertEquals(true, r1.voiLiikkua(s1, s2));
        
         s2 = new Sijainti(4, 5);
        assertEquals(true, r1.voiLiikkua(s1, s2));
        
        s2 = new Sijainti(5, 5);
        assertEquals(false, r1.voiLiikkua(s1, s2));
        
        s2 = new Sijainti(6, 3);
        assertEquals(false, r1.voiLiikkua(s1, s2));
        
         s2 = new Sijainti(4, 4);
        assertEquals(false, r1.voiLiikkua(s1, s2));
    }
}
