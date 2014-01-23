/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marktuom.shakki.domain;

import com.marktuom.shakki.domain.Kuningas;
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
public class KuningasTest {

    public Kuningas k1;
    public Kuningas k2;
    public Sijainti s1;
    public Sijainti s2;

    public KuningasTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        k1 = new Kuningas(Vari.MUSTA);
        k2 = new Kuningas(Vari.VALKOINEN);

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
    public void konstruktoriJaVari() {
        assertEquals(true, k1 != null);
        assertEquals(1, k1.getVari().getArvo());
        assertEquals(-1, k2.getVari().getArvo());
    }

    @Test
    public void TestVoiLiikkua() {
        s1 = new Sijainti(1, 1);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                s2 = new Sijainti(i, j);
                assertEquals(true, k1.voiLiikkua(s1, s2));
            }

        }
        s2 = new Sijainti(10, 10);
        assertEquals(false, k1.voiLiikkua(s1, s2));

        s2 = new Sijainti(10, 0);
        assertEquals(false, k1.voiLiikkua(s1, s2));

        s2 = new Sijainti(0, 10);
        assertEquals(false, k1.voiLiikkua(s1, s2));

        s2 = new Sijainti(3, 1);
        assertEquals(false, k1.voiLiikkua(s1, s2));

    }
}
