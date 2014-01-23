/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.marktuom.shakki.domain;

import com.marktuom.shakki.domain.Kuningatar;
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
public class KuningatarTest {
    public Kuningatar k1;
    public Sijainti s1;
    public Sijainti s2;
    
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
         k1 = new Kuningatar(Vari.MUSTA);
         s1 = new Sijainti(4, 4);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void TestVoiLiikkua(){
        s2= new Sijainti(0, 4);
        assertEquals(true, k1.voiLiikkua(s1, s2));
        
        s2= new Sijainti(4, 0);
        assertEquals(true, k1.voiLiikkua(s1, s2));
        
        s2= new Sijainti(7, 7);
        assertEquals(true, k1.voiLiikkua(s1, s2));
        
        s2= new Sijainti(6, 3);
        assertEquals(false, k1.voiLiikkua(s1, s2));
    }
}
