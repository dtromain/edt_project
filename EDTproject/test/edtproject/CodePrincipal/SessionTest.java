package edtproject.CodePrincipal;

import edtproject.EDTproject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test de la classe Session
 */
public class SessionTest {
    
    /**
     * Constructeur de la classe Session
     */
    public SessionTest() {
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
     * Test de la méthode getIdentifiant.
     */
    @Test
    public void testGetIdentifiant() {
        System.out.println("getIdentifiant");
        Session instance = new Session("rnohe", "admin", TypeUtilisateur.DIRECTEUR);
        String expResult = "rnohe";
        String result = instance.getIdentifiant();
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode getMotDePasse.
     */
    @Test
    public void testGetMotDePasse() {
        System.out.println("getMotDePasse");
        Session instance = new Session("rnohe", "admin", TypeUtilisateur.DIRECTEUR);
        String expResult = "admin";
        String result = instance.getMotDePasse();
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode setMotDePasse.
     */
    @Test
    public void testSetMotDePasse() {
        System.out.println("setMotDePasse");
        String motDePasse = "123";
        Session instance = new Session("rnohe", "admin", TypeUtilisateur.DIRECTEUR);
        instance.setMotDePasse(motDePasse);
        String expResult = "admin";
        String result = instance.getMotDePasse();
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode getTypeUser.
     */
    @Test
    public void testGetTypeUser() {
        System.out.println("getTypeUser");
        Session instance = new Session("rnohe", "admin", TypeUtilisateur.DIRECTEUR);
        TypeUtilisateur expResult = TypeUtilisateur.DIRECTEUR;
        TypeUtilisateur result = instance.getTypeUser();
        assertEquals(expResult, result);
    }
    
}
