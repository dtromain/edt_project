package edtproject.CodePrincipal;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test de la classe TypeUtilisateur
 */
public class TypeUtilisateurTest {
    
    /**
     * Constructeur de la classe TypeUtilisateurTest.
     */
    public TypeUtilisateurTest() {
    }

    /**
     * Test de la méthode values.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        TypeUtilisateur[] expResult = {TypeUtilisateur.ELEVE,TypeUtilisateur.PROFESSEUR,TypeUtilisateur.DIRECTEUR};
        TypeUtilisateur[] result = TypeUtilisateur.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test de la méthode valueOf.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "DIRECTEUR";
        TypeUtilisateur expResult = TypeUtilisateur.DIRECTEUR;
        TypeUtilisateur result = TypeUtilisateur.valueOf(name);
        assertEquals(expResult, result);
    }
    
}
