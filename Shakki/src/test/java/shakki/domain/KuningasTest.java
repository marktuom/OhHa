/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shakki.domain;

import shakki.domain.Sijainti;
import shakki.domain.Lauta;
import shakki.domain.Vari;
import shakki.domain.Kuningas;
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
    Lauta l1;

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
        l1 = new Lauta();
        k1 = new Kuningas(l1, l1.getRuutu(2, 2), Vari.MUSTA);
        k2 = new Kuningas(l1, l1.getRuutu(0, 0), Vari.VALKOINEN);

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
    public void TestMahdollisetSiirrot() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!(i == 1 && j == 1)) {
                    assertEquals(true, k1.mahdollisetSiirrot().contains(l1.getRuutu(j + 1, i + 1)));
                }
            }
        }
        assertEquals(false, k1.mahdollisetSiirrot().contains(l1.getRuutu(2, 2)));
    }

    @Test
    public void TestLiiku() {
        assertEquals(true, k1.liiku(l1.getRuutu(3, 3)));
        assertEquals(true, l1.getRuutu(3, 3).getNappula() instanceof Kuningas);
    }

    @Test
    public void TestTornitusOikealle() {
        Nappula n1 = new Kuningas(l1, l1.getRuutu(4, 7), Vari.VALKOINEN);
        Nappula n2 = new Torni(l1, l1.getRuutu(7, 7), Vari.VALKOINEN);
        assertEquals(true, n1.mahdollisetSiirrot().contains(l1.getRuutu(6, 7)));
        assertEquals(true, n1.laillisetSiirrot().contains(l1.getRuutu(6, 7)));
        Nappula n3 = new Torni(l1, l1.getRuutu(5, 5), Vari.MUSTA);
        assertEquals(false, n1.laillisetSiirrot().contains(l1.getRuutu(6, 7)));
        assertEquals(false, n1.liiku(l1.getRuutu(6, 7)));
        l1.getRuutu(5, 5).poistaNappula();
        assertEquals(true, n1.liiku(l1.getRuutu(6, 7)));
        assertEquals(true, l1.getRuutu(5, 7).getNappula() instanceof Torni);
        assertEquals(null, l1.getRuutu(7, 7).getNappula());
    }

    @Test
    public void TestTornitusVasemmalle() {
        Nappula n1 = new Kuningas(l1, l1.getRuutu(4, 7), Vari.VALKOINEN);
        Nappula n2 = new Torni(l1, l1.getRuutu(0, 7), Vari.VALKOINEN);
        assertEquals(true, n1.mahdollisetSiirrot().contains(l1.getRuutu(2, 7)));
        assertEquals(true, n1.laillisetSiirrot().contains(l1.getRuutu(2, 7)));
        Nappula n3 = new Torni(l1, l1.getRuutu(3, 5), Vari.MUSTA);
        assertEquals(false, n1.laillisetSiirrot().contains(l1.getRuutu(2, 7)));
        assertEquals(false, n1.liiku(l1.getRuutu(2, 7)));
        l1.getRuutu(3, 5).poistaNappula();
        assertEquals(true, n1.liiku(l1.getRuutu(2, 7)));
        assertEquals(true, l1.getRuutu(3, 7).getNappula() instanceof Torni);
        assertEquals(null, l1.getRuutu(0, 7).getNappula());
    }
}
