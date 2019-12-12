package edtproject.CodePrincipal;

import edtproject.EDTproject;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test de la classe Module.
 */
public class ModuleTest {
    
    /**
     * Constructeur de la classe ModuleTest.
     */
    public ModuleTest() {
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
     * Test de la méthode getNom method.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        Module instance = new Module("AP", Color.AZURE);
        String expResult = "AP";
        String result = instance.getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode getCouleur.
     */
    @Test
    public void testGetCouleur() {
        System.out.println("getCouleur");
        Module instance = new Module("AP", Color.AZURE);
        Color expResult = Color.AZURE;
        Color result = instance.getCouleur();
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode setCouleur.
     */
    @Test
    public void testSetCouleur() {
        System.out.println("setCouleur");
        Color couleur = Color.CHARTREUSE;
        Module instance = new Module("AP", Color.AZURE);
        instance.setCouleur(couleur);
        Color expResult = couleur;
        Color result = instance.getCouleur();
        assertEquals(expResult,result);
    }

    /**
     * Test de la méthode equals.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        String nom = "AP";
        Module instance = new Module("AP", Color.AZURE);
        boolean expResult = false;
        boolean result = instance.equals(nom);
        assertEquals(expResult, result);
    }
    
}
