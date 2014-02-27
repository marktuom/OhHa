/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shakki.domain;

import shakki.domain.*;
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

    @Test
    public void TestGenerointiJaGetterit() {
        Ruutu[][] r1 = l1.getRuudukko();
        assertEquals(true, r1 != null);
        assertEquals(true, r1[0][0] == l1.getRuutu(0, 0));

    }

    @Test
    public void TestNappuloidenGenerointi() {
        l1.generoiNappulat();
        Ruutu[][] r1 = l1.getRuudukko();
        assertEquals(true, r1[1][3].getNappula() instanceof Sotilas);
        assertEquals(true, r1[6][7].getNappula() instanceof Sotilas);
        assertEquals(true, r1[0][0].getNappula() instanceof Torni);
        assertEquals(true, r1[7][4].getNappula() instanceof Kuningas);
        assertEquals(true, r1[7][6].getNappula() instanceof Ratsu);
    }

    @Test
    public void TestSiirra() {

        Ruutu[][] r1 = l1.getRuudukko();

        assertEquals(false, l1.siirra(null, r1[7][7]));
        assertEquals(false, l1.siirra(r1[6][6], null));
        assertEquals(false, l1.siirra(r1[3][3], r1[7][7]));

        r1[3][3].setNappula(new Kuningatar(l1, r1[3][3], Vari.MUSTA));
        r1[4][4].setNappula(new Ratsu(l1, r1[4][4], Vari.MUSTA));
        r1[5][2].setNappula(new Ratsu(l1, r1[5][2], Vari.VALKOINEN));

        assertEquals(false, l1.siirra(r1[3][3], r1[7][7]));
        assertEquals(false, l1.siirra(r1[3][3], r1[1][1]));
        assertEquals(true, l1.siirra(r1[5][2], r1[4][4]));
        assertEquals(false, l1.siirra(r1[4][4], r1[5][6]));
        assertEquals(true, l1.siirra(r1[3][3], r1[4][4]));
        assertEquals(true, r1[4][4].getNappula() instanceof Kuningatar);
    }

    @Test
    public void TestShakissa() {
        Ruutu r1 = l1.getRuutu(4, 4);
        Ruutu r2 = l1.getRuutu(0, 4);
        Nappula n1 = new Kuningas(l1, r2, Vari.MUSTA);
        Nappula n2 = new Torni(l1, r1, Vari.VALKOINEN);
        assertEquals(true, l1.shakissa(Vari.MUSTA));
        r2.poistaNappula();
        assertEquals(false, l1.shakissa(Vari.MUSTA));
    }

    @Test
    public void TestMatissa() {
        Ruutu r1 = l1.getRuutu(4, 4);
        Ruutu r2 = l1.getRuutu(0, 4);
        Nappula n1 = new Kuningas(l1, r2, Vari.MUSTA);
        Nappula n2 = new Torni(l1, r1, Vari.VALKOINEN);

        assertEquals(false, l1.matissa(Vari.MUSTA));

        Nappula n3 = new Torni(l1, l1.getRuutu(4, 3), Vari.VALKOINEN);
        Nappula n4 = new Torni(l1, l1.getRuutu(4, 5), Vari.VALKOINEN);

        assertEquals(true, l1.matissa(Vari.MUSTA));

        Nappula n5 = new Sotilas(l1, l1.getRuutu(1, 4), Vari.VALKOINEN);
        assertEquals(false, l1.matissa(Vari.MUSTA));

    }

    @Test
    public void TestgetVuorossa() {
        assertEquals(Vari.VALKOINEN, l1.getVuorossa());
    }

    @Test
    public void TestgetVuoro() {
        assertEquals(1, l1.getVuoro());
    }

    @Test
    public void TestTasapeli() {
        Nappula n1 = new Kuningas(l1, l1.getRuutu(0, 0), Vari.VALKOINEN);
        Nappula n2 = new Torni(l1, l1.getRuutu(2, 1), Vari.MUSTA);
        Nappula n3 = new Torni(l1, l1.getRuutu(1, 2), Vari.MUSTA);
        assertEquals(true, l1.tasapeli());
        Nappula n4 = new Lahetti(l1, l1.getRuutu(3, 3), Vari.MUSTA);
        assertEquals(false, l1.tasapeli());
    }

}
