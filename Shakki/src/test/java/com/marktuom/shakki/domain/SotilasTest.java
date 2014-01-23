/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marktuom.shakki.domain;

import com.marktuom.shakki.domain.Sotilas;
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
public class SotilasTest {

    Sotilas sot1;
    Sotilas sot2;
    Sijainti s1;
    Sijainti s2;

    public SotilasTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        sot1 = new Sotilas(Vari.MUSTA);
        sot2 = new Sotilas(Vari.VALKOINEN);
        s1 = new Sijainti(2, 2);

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
    public void TestLiiku() {
        assertEquals(0, sot1.getSiirtoja());
        sot1.liiku();
        assertEquals(1, sot1.getSiirtoja());
    }

    @Test
    public void TestVoiLiikkua() {
        s2 = new Sijainti(2, 3);
        assertEquals(true, sot1.voiLiikkua(s1, s2));
        assertEquals(false, sot2.voiLiikkua(s1, s2));

        s2 = new Sijainti(2, 1);
        assertEquals(false, sot1.voiLiikkua(s1, s2));
        assertEquals(true, sot2.voiLiikkua(s1, s2));

        s2 = new Sijainti(2, 4);
        assertEquals(true, sot1.voiLiikkua(s1, s2));
        sot1.liiku();
        assertEquals(false, sot1.voiLiikkua(s1, s2));
        
        s2 = new Sijainti(3, 3);
        assertEquals(true, sot1.voiLiikkua(s1, s2));
        
        s2 = new Sijainti(1, 1);
        assertEquals(false, sot1.voiLiikkua(s1, s2));
    }
}
