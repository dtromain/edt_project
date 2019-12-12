package edtproject.CodePrincipal;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test de la classe Salle.
 */
public class TypeSalleTest {
    
    /**
     * Constructeur de la classe TypeSalleTest.
     */
    public TypeSalleTest() {
    }
    
    /**
     * Test de la méthode values.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        TypeSalle[] expResult = {TypeSalle.RESEAU,TypeSalle.AMPHI,TypeSalle.TD,TypeSalle.TP};
        TypeSalle[] result = TypeSalle.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test de la méthode valueOf.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "RESEAU";
        TypeSalle expResult = TypeSalle.RESEAU;
        TypeSalle result = TypeSalle.valueOf(name);
        assertEquals(expResult, result);
    }
    
}
