package edtproject.CodePrincipal;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test de la classe TypeCours.
 */
public class TypeCoursTest {
    
    /**
     * Constructeur de la classe TypeCoursTest.
     */
    public TypeCoursTest() {
    }
    
    /**
     * Test de la méthode values.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        TypeCours[] expResult = {TypeCours.COURS,TypeCours.TD,TypeCours.TP};
        TypeCours[] result = TypeCours.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test de la méthode valueOf.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "COURS";
        TypeCours expResult = TypeCours.COURS;
        TypeCours result = TypeCours.valueOf(name);
        assertEquals(expResult, result);
    }
    
}
