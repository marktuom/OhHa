/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.marktuom.shakki.domain;

import com.marktuom.shakki.domain.Sotilas;
import com.marktuom.shakki.domain.Ruutu;
import com.marktuom.shakki.domain.Nappula;
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
public class RuutuTest {
    
    Ruutu r1;
    Nappula n1;
    Sijainti s1;
    
    public RuutuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        n1 = new Sotilas(Vari.MUSTA);
        s1 = new Sijainti(1, 1);
        r1 = new Ruutu(s1, n1);
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
    public void TestiOikeatArvotJaGetterit(){
        assertEquals(s1, r1.getSijainti());
        assertEquals(n1, r1.getNappula());
    }
    
    @Test
    public void TestiNappulanMuuttaminen(){
        Nappula n2 = new Sotilas(Vari.MUSTA);
        r1.setNappula(n2);
        assertEquals(n2, r1.getNappula());
        
        r1.poistaNappula();
        assertEquals(null, r1.getNappula());
    }
}
