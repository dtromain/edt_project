package edtproject.CodePrincipal;

import edtproject.EDTproject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test de la classe Salle.
 */
public class SalleTest {
    
    /**
     * Constructeur de la classe SalleTest.
     */
    public SalleTest() {
    }
    
    /**
     * Cette méthode permet de vider la liste des objets instanciés dans le 
     * programme avant le début de chaque test.
     */
    @Before
    public void setUp() {
        EDTproject.resetListDependances();
    }
    
    /**
     * Cette méthode permet de vider la liste des objets instanciés dans le 
     * programme après la fin de chaque test.
     */
    @After
    public void tearDown() {
        EDTproject.resetListDependances();
    }

    /**
     * Test de la méthode getNom.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        Salle instance = new Salle("D204",TypeSalle.TP);
        String expResult = "D204";
        String result = instance.getNom();
        assertEquals(expResult, result);
    }
    
    public void testGetType() {
        System.out.println("getType");
        Salle instance = new Salle("D204",TypeSalle.TP);
        TypeSalle expResult = TypeSalle.TP;
        TypeSalle result = instance.getTypeSalle();
        assertEquals(expResult, result);
    }
}
