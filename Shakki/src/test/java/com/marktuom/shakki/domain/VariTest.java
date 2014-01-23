
package com.marktuom.shakki.domain;

import com.marktuom.shakki.domain.Vari;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class VariTest {
    
    public VariTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

   @Test
    public void TestGetArvo(){
        assertEquals(-1, Vari.VALKOINEN.getArvo());
        assertEquals(1, Vari.MUSTA.getArvo());
    }
}
