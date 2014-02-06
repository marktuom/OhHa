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
    
    @Test
    public void TestNappuloidenGenerointi(){
        l1.generoiNappulat();
        Ruutu[][] r1 = l1.getRuudukko();
        assertEquals(true, r1[1][3].getNappula() instanceof Sotilas);
        assertEquals(true, r1[6][7].getNappula() instanceof Sotilas);
        assertEquals(true, r1[0][0].getNappula() instanceof Torni);
        assertEquals(true, r1[7][4].getNappula() instanceof Kuningas);
        assertEquals(true, r1[7][6].getNappula() instanceof Ratsu);
    } 
    
    
     @Test
    public void TestNappuloitaTiella(){
        
        Ruutu[][] r1 = l1.getRuudukko();
        r1[0][0].setNappula(new Kuningatar(l1,  r1[0][0], Vari.MUSTA));
        r1[0][1].setNappula(new Kuningatar(l1, r1[0][1], Vari.MUSTA));
        r1[3][3].setNappula(new Kuningatar(l1, r1[3][3], Vari.MUSTA));
      
        assertEquals(false, l1.nappuloitaTiella(r1[0][0], r1[4][3]));
        
        assertEquals(false, l1.nappuloitaTiella(r1[0][0], r1[4][0]));
        assertEquals(true, l1.nappuloitaTiella(r1[0][0], r1[4][4]));
        assertEquals(false, l1.nappuloitaTiella(r1[0][4], r1[4][0]));
        
        
        assertEquals(false, l1.nappuloitaTiella(r1[4][0], r1[0][0]));
        assertEquals(true, l1.nappuloitaTiella(r1[4][4], r1[0][0]));
        assertEquals(false, l1.nappuloitaTiella(r1[4][0], r1[0][4]));
        
        assertEquals(false, l1.nappuloitaTiella(r1[0][0], r1[0][0]));
        assertEquals(true, l1.nappuloitaTiella(r1[0][4], r1[0][0]));
        assertEquals(true, l1.nappuloitaTiella(r1[0][0], r1[0][4]));
    } 
    
    @Test
    public void TestSiirra(){
        
        Ruutu[][] r1 = l1.getRuudukko();
        r1[3][3].setNappula(new Kuningatar(l1,r1[3][3], Vari.MUSTA));
        r1[4][4].setNappula(new Ratsu(l1, r1[4][4], Vari.MUSTA));
        r1[5][2].setNappula(new Ratsu(l1, r1[5][2],Vari.VALKOINEN));
      
        assertEquals(false, l1.siirra(r1[3][3], r1[7][7]));
        assertEquals(false, l1.siirra(r1[3][3], r1[5][6]));
        assertEquals(false, l1.siirra(r1[3][3], r1[4][4]));
        assertEquals(true, l1.siirra(r1[5][2], r1[4][4]));
        assertEquals(true, l1.siirra(r1[3][3], r1[4][4]));
        assertEquals(true, r1[4][4].getNappula() instanceof Kuningatar);
    } 
}
