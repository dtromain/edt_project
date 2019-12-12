package edtproject.CodePrincipal;

import edtproject.EDTproject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test de la classe Professeur.
 */
public class ProfesseurTest {
    
    /**
     * Constructeur de la classe ProfesseurTest.
     */
    public ProfesseurTest() {
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
     * Test de la méthode getPrenom method.
     */
    @Test
    public void testGetPrenom() {
        System.out.println("getPrenom");
        Professeur instance = null;
        String expResult = "";
        String result = instance.getPrenom();
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode getNom
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        Professeur instance = null;
        String expResult = "";
        String result = instance.getNom();
        assertEquals(expResult, result);
    }
    
    /**
     * Test de la méthode getInitiales.
     */
    @Test
    public void testGetInitiales() {
        System.out.println("getInitiales");
        Professeur instance = new Professeur("Regis", "Nohe", "rnohe");
        String expResult = "RNo";
        String result = instance.getInitiales();
        assertEquals(expResult, result);
    }
    
    /**
     * Test de la méthode setInitiales method, of class Professeur.
     */
    @Test
    public void testSetInitiales() {
        System.out.println("setInitiales");
        String initiales = "RNo";
        Professeur instance = new Professeur("Regis", "Nohe", "rnohe");
        instance.setInitiales(initiales);
        String expResult = initiales;
        String result = instance.getIdentifiant();
        assertEquals(expResult,result);
    }

    /**
     * Test de la méthode getIdentifiant.
     */
    @Test
    public void testGetIdentifiant() {
        System.out.println("getIdentifiant");
        Professeur instance = new Professeur("Regis", "Nohe", "rnohe");
        String expResult = "rnohe";
        String result = instance.getIdentifiant();
        assertEquals(expResult, result);
    }
    
}
