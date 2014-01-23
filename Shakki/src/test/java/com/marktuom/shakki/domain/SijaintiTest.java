
package com.marktuom.shakki.domain;

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
public class SijaintiTest {

    public SijaintiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriJaGetterit() {
        Sijainti sijainti = new Sijainti(0, 1);
        assertEquals(0, sijainti.getX());
        assertEquals(1, sijainti.getY());
    }

    @Test
    public void TestXErotus() {
        Sijainti s1 = new Sijainti(3, 7);
        Sijainti s2 = new Sijainti(10, 10);
        assertEquals(7, s1.xErotus(s2));
        assertEquals(-7, s2.xErotus(s1));
    }

    @Test
    public void TestYErotus() {
        Sijainti s1 = new Sijainti(3, 7);
        Sijainti s2 = new Sijainti(10, 10);
        assertEquals(3, s1.yErotus(s2));
        assertEquals(-3, s2.yErotus(s1));
    }
    
    @Test
    public void TestXMuutos() {
        Sijainti s1 = new Sijainti(3, 7);
        Sijainti s2 = new Sijainti(10, 10);
        assertEquals(7, s1.xMuutos(s2));
        assertEquals(7, s2.xMuutos(s1));
    }
    
    @Test
    public void TestYMuutos() {
        Sijainti s1 = new Sijainti(3, 7);
        Sijainti s2 = new Sijainti(10, 10);
        assertEquals(3, s1.yMuutos(s2));
        assertEquals(3, s2.yMuutos(s1));
    }
    
    @Test
    public void TestErotus() {
        Sijainti s1 = new Sijainti(3, 7);
        Sijainti s2 = new Sijainti(10, 10);
        Sijainti s3 = s1.erotus(s2);
        Sijainti s4 = s2.erotus(s1);
        
        assertEquals(7, s3.getX());
        assertEquals(3, s3.getY());
        
        assertEquals(-7, s4.getX());
        assertEquals(-3, s4.getY());
    }
}
