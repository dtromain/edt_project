package edtproject.CodePrincipal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test de la classe NomJour.
 */
public class NomJourTest {
    
    /**
     * Constructeur de la classe NomJourTest.
     */
    public NomJourTest() {
    }

    /**
     * Test de la méthode values.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        NomJour[] expResult = {NomJour.LUNDI,NomJour.MARDI,NomJour.MERCREDI,NomJour.JEUDI,NomJour.VENDREDI};
        NomJour[] result = NomJour.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test de la méthode valueOf.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "LUNDI";
        NomJour expResult = null;
        NomJour result = NomJour.valueOf(name);
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode getHeureDebutEcole.
     */
    @Test
    public void testGetHeureDebutEcole() {
        System.out.println("getHeureDebutEcole");
        NomJour instance = NomJour.LUNDI;
        int expResult = 8*60;
        int result = instance.getHeureDebutEcole();
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode getHeureFinEcole.
     */
    @Test
    public void testGetHeureFinEcole() {
        System.out.println("getHeureFinEcole");
        NomJour instance = NomJour.LUNDI;
        int expResult = 18*60;
        int result = instance.getHeureFinEcole();
        assertEquals(expResult, result);
    }
    
}
